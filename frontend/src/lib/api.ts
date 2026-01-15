/**
 * API client for backend communication
 * Handles SSE streaming for real-time search updates
 */

const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL || 'http://localhost:8080'

export interface SearchEvent {
    type: string
    data: any
}

/**
 * Execute property search with Server-Sent Events streaming
 */
export async function* searchPropertiesStream(
    query: string
): AsyncGenerator<SearchEvent, void, unknown> {
    const response = await fetch(`${BACKEND_URL}/api/search`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ query }),
    })

    if (!response.ok) {
        throw new Error(`Search failed: ${response.statusText}`)
    }

    const reader = response.body?.getReader()
    const decoder = new TextDecoder()

    if (!reader) {
        throw new Error('No response body')
    }

    let buffer = ''
    let currentEventType: string | null = null

    try {
        while (true) {
            const { done, value } = await reader.read()

            if (done) break

            buffer += decoder.decode(value, { stream: true })
            const lines = buffer.split('\n')
            buffer = lines.pop() || ''

            for (const line of lines) {
                if (!line.trim()) continue

                if (line.startsWith('event:')) {
                    currentEventType = line.substring(6).trim()
                    continue
                }

                if (line.startsWith('data:')) {
                    const data = line.substring(5).trim()

                    if (data) {
                        let parsedData: any = data
                        try {
                            parsedData = JSON.parse(data)
                        } catch (e) {
                            // Data is plain text, keep as string
                        }

                        // Determine event type
                        let eventType = currentEventType || 'update'

                        // Fallback type detection from data structure (legacy support)
                        if (!currentEventType && typeof parsedData === 'object') {
                            if (parsedData.intent) {
                                eventType = 'intent_extracted'
                            } else if (parsedData.top_recommendations) {
                                eventType = 'recommendations_ready'
                            }
                        } else if (!currentEventType && typeof parsedData === 'string') {
                            if (parsedData.includes('Analyzing')) eventType = 'intent_extraction_started'
                            else if (parsedData.includes('Found')) eventType = 'candidates_found'
                            else if (parsedData.includes('analyzing')) eventType = 'agent_analysis_started'
                        }

                        yield {
                            type: eventType,
                            data: parsedData,
                        }

                        // Reset event type for next message
                        currentEventType = null
                    }
                }
            }
        }
    } finally {
        reader.releaseLock()
    }
}

/**
 * Get property by ID
 */
export async function getPropertyById(propertyId: string) {
    const response = await fetch(`${BACKEND_URL}/api/properties/${propertyId}`)

    if (!response.ok) {
        throw new Error(`Failed to fetch property: ${response.statusText}`)
    }

    return response.json()
}

/**
 * Health check
 */
export async function checkHealth() {
    const response = await fetch(`${BACKEND_URL}/api/health`)

    if (!response.ok) {
        throw new Error('Backend is unhealthy')
    }

    return response.json()
}
