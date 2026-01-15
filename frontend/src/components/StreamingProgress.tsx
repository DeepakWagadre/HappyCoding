'use client'

import { Loader2, CheckCircle2 } from 'lucide-react'

interface StreamingProgressProps {
    events: Array<{ type: string; message: string }>
}

export default function StreamingProgress({ events }: StreamingProgressProps) {
    const steps = [
        { type: 'intent_extraction_started', label: 'Analyzing your requirements' },
        { type: 'intent_extracted', label: 'Intent extracted successfully' },
        { type: 'candidates_found', label: 'Finding matching properties' },
        { type: 'agent_analysis_started', label: 'AI agents analyzing properties' },
        { type: 'recommendations_ready', label: 'Preparing recommendations' },
    ]

    const currentEventTypes = events.map(e => e.type)
    const currentStepIndex = steps.findIndex(step =>
        !currentEventTypes.includes(step.type)
    )

    return (
        <div className="max-w-4xl mx-auto p-6 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800 shadow-lg">
            <h3 className="text-lg font-semibold text-slate-900 dark:text-white mb-6 flex items-center">
                <Loader2 className="w-5 h-5 mr-2 animate-spin text-blue-600" />
                AI Analysis in Progress
            </h3>

            <div className="space-y-4">
                {steps.map((step, index) => {
                    const isComplete = currentEventTypes.includes(step.type)
                    const isActive = index === currentStepIndex

                    return (
                        <div key={step.type} className="flex items-start space-x-3">
                            <div className={`w-6 h-6 rounded-full flex items-center justify-center flex-shrink-0 transition-all duration-300 ${isComplete
                                    ? 'bg-green-100 dark:bg-green-900/30'
                                    : isActive
                                        ? 'bg-blue-100 dark:bg-blue-900/30 animate-pulse'
                                        : 'bg-slate-100 dark:bg-slate-700'
                                }`}>
                                {isComplete ? (
                                    <CheckCircle2 className="w-4 h-4 text-green-600 dark:text-green-400" />
                                ) : isActive ? (
                                    <Loader2 className="w-4 h-4 text-blue-600 dark:text-blue-400 animate-spin" />
                                ) : (
                                    <div className="w-2 h-2 rounded-full bg-slate-300 dark:bg-slate-600" />
                                )}
                            </div>

                            <div className="flex-1">
                                <p className={`text-sm font-medium transition-colors ${isComplete
                                        ? 'text-green-700 dark:text-green-300'
                                        : isActive
                                            ? 'text-blue-700 dark:text-blue-300'
                                            : 'text-slate-500 dark:text-slate-400'
                                    }`}>
                                    {step.label}
                                </p>
                            </div>
                        </div>
                    )
                })}
            </div>

            <div className="mt-6 pt-4 border-t border-slate-200 dark:border-slate-700">
                <p className="text-xs text-slate-500 dark:text-slate-400 text-center">
                    Multiple AI agents are working in parallel to analyze properties...
                </p>
            </div>
        </div>
    )
}
