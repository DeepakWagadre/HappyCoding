'use client'

import { CheckCircle, AlertCircle, Home, IndianRupee, MapPin } from 'lucide-react'

interface IntentPreviewProps {
    intent: any
}

export default function IntentPreview({ intent }: IntentPreviewProps) {
    if (!intent) return null

    const confidence = intent.confidence_level || 'MEDIUM'
    const confidenceColors = {
        HIGH: 'text-green-600 bg-green-100 dark:bg-green-900/30',
        MEDIUM: 'text-yellow-600 bg-yellow-100 dark:bg-yellow-900/30',
        LOW: 'text-orange-600 bg-orange-100 dark:bg-orange-900/30',
    }

    const formatBudget = (budget: any) => {
        if (!budget) return 'Not specified'
        const min = budget.min ? `₹${(budget.min / 10000000).toFixed(2)}Cr` : ''
        const max = budget.max ? `₹${(budget.max / 10000000).toFixed(2)}Cr` : ''
        if (min && max) return `${min} - ${max}`
        if (max) return `Up to ${max}`
        if (min) return `From ${min}`
        return 'Flexible'
    }

    return (
        <div className="max-w-4xl mx-auto p-6 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800 shadow-lg">
            <div className="flex items-center justify-between mb-4">
                <h3 className="text-lg font-semibold text-slate-900 dark:text-white flex items-center">
                    <CheckCircle className="w-5 h-5 mr-2 text-blue-600" />
                    What AI Understood
                </h3>
                <span className={`px-3 py-1 rounded-full text-xs font-medium ${confidenceColors[confidence as keyof typeof confidenceColors]}`}>
                    {confidence} Confidence
                </span>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                <div className="flex items-start space-x-3">
                    <div className="w-10 h-10 rounded-lg bg-blue-100 dark:bg-blue-900/30 flex items-center justify-center flex-shrink-0">
                        <Home className="w-5 h-5 text-blue-600 dark:text-blue-400" />
                    </div>
                    <div>
                        <p className="text-xs font-medium text-slate-500 dark:text-slate-400 mb-1">Property Type</p>
                        <p className="text-sm font-semibold text-slate-900 dark:text-white">
                            {intent.property_type || 'Any'}
                        </p>
                        {intent.transaction_type && (
                            <p className="text-xs text-slate-600 dark:text-slate-400">
                                For {intent.transaction_type.toLowerCase()}
                            </p>
                        )}
                    </div>
                </div>

                <div className="flex items-start space-x-3">
                    <div className="w-10 h-10 rounded-lg bg-green-100 dark:bg-green-900/30 flex items-center justify-center flex-shrink-0">
                        <IndianRupee className="w-5 h-5 text-green-600 dark:text-green-400" />
                    </div>
                    <div>
                        <p className="text-xs font-medium text-slate-500 dark:text-slate-400 mb-1">Budget</p>
                        <p className="text-sm font-semibold text-slate-900 dark:text-white">
                            {formatBudget(intent.budget)}
                        </p>
                    </div>
                </div>

                <div className="flex items-start space-x-3">
                    <div className="w-10 h-10 rounded-lg bg-purple-100 dark:bg-purple-900/30 flex items-center justify-center flex-shrink-0">
                        <MapPin className="w-5 h-5 text-purple-600 dark:text-purple-400" />
                    </div>
                    <div>
                        <p className="text-xs font-medium text-slate-500 dark:text-slate-400 mb-1">Locations</p>
                        <p className="text-sm font-semibold text-slate-900 dark:text-white">
                            {intent.preferred_locations?.join(', ') || 'All areas'}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    )
}
