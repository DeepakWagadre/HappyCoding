'use client'

import { useState } from 'react'
import { Search, X, Loader2 } from 'lucide-react'

interface SearchBarProps {
    onSearch: (query: string) => void
    isLoading?: boolean
    onClear?: () => void
}

export default function SearchBar({ onSearch, isLoading, onClear }: SearchBarProps) {
    const [query, setQuery] = useState('')

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault()
        if (query.trim()) {
            onSearch(query.trim())
        }
    }

    const handleClear = () => {
        setQuery('')
        onClear?.()
    }

    return (
        <form onSubmit={handleSubmit} className="w-full max-w-4xl mx-auto">
            <div className="relative group">
                <input
                    type="text"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    placeholder="Describe your ideal property... (e.g., '3 BHK under 1.5 crore near Sarjapur with good schools')"
                    className="w-full px-6 py-4 pr-32 text-lg rounded-xl border-2 border-slate-300 dark:border-slate-600 bg-white dark:bg-slate-800 text-slate-900 dark:text-white placeholder-slate-400 dark:placeholder-slate-500 focus:border-blue-500 dark:focus:border-blue-400 focus:outline-none focus:ring-4 focus:ring-blue-500/20 transition-all duration-200 shadow-lg"
                    disabled={isLoading}
                />

                <div className="absolute right-2 top-1/2 -translate-y-1/2 flex items-center space-x-2">
                    {query && (
                        <button
                            type="button"
                            onClick={handleClear}
                            className="p-2 rounded-lg hover:bg-slate-100 dark:hover:bg-slate-700 transition-colors"
                            disabled={isLoading}
                        >
                            <X className="w-5 h-5 text-slate-400" />
                        </button>
                    )}

                    <button
                        type="submit"
                        disabled={!query.trim() || isLoading}
                        className="px-6 py-2.5 rounded-lg bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-700 hover:to-indigo-700 text-white font-medium transition-all duration-200 disabled:opacity-50 disabled:cursor-not-allowed flex items-center space-x-2 shadow-lg shadow-blue-500/30"
                    >
                        {isLoading ? (
                            <>
                                <Loader2 className="w-5 h-5 animate-spin" />
                                <span>Searching...</span>
                            </>
                        ) : (
                            <>
                                <Search className="w-5 h-5" />
                                <span>Search</span>
                            </>
                        )}
                    </button>
                </div>
            </div>
        </form>
    )
}
