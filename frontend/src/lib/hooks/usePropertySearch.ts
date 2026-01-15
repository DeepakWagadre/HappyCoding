/**
 * Custom hook for property search with streaming
 */

import { useState, useCallback } from 'react'
import { searchPropertiesStream, SearchEvent } from '../api'

interface SearchState {
    isSearching: boolean
    intent: any | null
    events: Array<{ type: string; message: string }>
    results: any | null
    error: string | null
}

export function usePropertySearch() {
    const [searchState, setSearchState] = useState<SearchState>({
        isSearching: false,
        intent: null,
        events: [],
        results: null,
        error: null,
    })

    const executeSearch = useCallback(async (query: string) => {
        // Reset state
        setSearchState({
            isSearching: true,
            intent: null,
            events: [],
            results: null,
            error: null,
        })

        try {
            const stream = searchPropertiesStream(query)

            for await (const event of stream) {
                // Update state based on event type
                switch (event.type) {
                    case 'intent_extracted':
                        setSearchState(prev => ({
                            ...prev,
                            intent: event.data,
                            events: [...prev.events, {
                                type: event.type,
                                message: 'Intent extracted successfully'
                            }],
                        }))
                        break

                    case 'candidates_found':
                        setSearchState(prev => ({
                            ...prev,
                            events: [...prev.events, {
                                type: event.type,
                                message: typeof event.data === 'string' ? event.data : 'Found matching properties'
                            }],
                        }))
                        break

                    case 'agent_analysis_started':
                        setSearchState(prev => ({
                            ...prev,
                            events: [...prev.events, {
                                type: event.type,
                                message: 'AI agents analyzing properties'
                            }],
                        }))
                        break

                    case 'recommendations_ready':
                        setSearchState(prev => ({
                            ...prev,
                            results: event.data,
                            events: [...prev.events, {
                                type: event.type,
                                message: 'Recommendations ready'
                            }],
                        }))
                        break

                    case 'complete':
                        setSearchState(prev => ({
                            ...prev,
                            isSearching: false,
                        }))
                        break

                    default:
                        // Handle other event types
                        setSearchState(prev => ({
                            ...prev,
                            events: [...prev.events, {
                                type: event.type,
                                message: typeof event.data === 'string' ? event.data : 'Processing...'
                            }],
                        }))
                }
            }

            // Ensure we mark as not searching when stream completes
            setSearchState(prev => ({ ...prev, isSearching: false }))

        } catch (error) {
            console.error('Search failed:', error)
            setSearchState(prev => ({
                ...prev,
                isSearching: false,
                error: error instanceof Error ? error.message : 'Search failed',
            }))
        }
    }, [])

    const clearSearch = useCallback(() => {
        setSearchState({
            isSearching: false,
            intent: null,
            events: [],
            results: null,
            error: null,
        })
    }, [])

    return {
        searchState,
        executeSearch,
        clearSearch,
    }
}
