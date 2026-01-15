'use client'

import { useState } from 'react'
import { Search, Sparkles, MapPin, TrendingUp } from 'lucide-react'
import SearchBar from '@/components/SearchBar'
import IntentPreview from '@/components/IntentPreview'
import StreamingProgress from '@/components/StreamingProgress'
import PropertyResults from '@/components/PropertyResults'
import { usePropertySearch } from '@/lib/hooks/usePropertySearch'

export default function Home() {
    const [query, setQuery] = useState('')
    const { searchState, executeSearch, clearSearch } = usePropertySearch()

    const handleSearch = (searchQuery: string) => {
        setQuery(searchQuery)
        executeSearch(searchQuery)
    }

    const exampleQueries = [
        "3 BHK under 1.5 crore near Sarjapur with good schools",
        "Best areas to invest in Bangalore for rental yield",
        "Villa near Whitefield within 45 mins commute to Koramangala",
        "Budget apartment near Electronic City with metro connectivity"
    ]

    return (
        <main className="min-h-screen bg-gradient-to-br from-slate-50 via-blue-50 to-indigo-50 dark:from-slate-900 dark:via-slate-800 dark:to-indigo-950">
            {/* Header */}
            <header className="border-b border-slate-200 dark:border-slate-700 bg-white/50 dark:bg-slate-900/50 backdrop-blur-lg sticky top-0 z-50">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
                    <div className="flex items-center justify-between">
                        <div className="flex items-center space-x-3">
                            <div className="w-10 h-10 rounded-xl bg-gradient-to-br from-blue-500 to-indigo-600 flex items-center justify-center">
                                <Sparkles className="w-6 h-6 text-white" />
                            </div>
                            <div>
                                <h1 className="text-2xl font-bold bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
                                    Property AI
                                </h1>
                                <p className="text-xs text-slate-600 dark:text-slate-400">
                                    Intelligent Property Discovery for Bangalore
                                </p>
                            </div>
                        </div>

                        <div className="hidden md:flex items-center space-x-6 text-sm">
                            <div className="flex items-center space-x-2 text-slate-600 dark:text-slate-400">
                                <MapPin className="w-4 h-4" />
                                <span>Bangalore</span>
                            </div>
                            <div className="flex items-center space-x-2 text-slate-600 dark:text-slate-400">
                                <TrendingUp className="w-4 h-4" />
                                <span>20+ Properties</span>
                            </div>
                        </div>
                    </div>
                </div>
            </header>

            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
                {/* Hero Section */}
                {!searchState.isSearching && !searchState.results && (
                    <div className="text-center mb-12 animate-fade-in">
                        <div className="inline-flex items-center space-x-2 px-4 py-2 rounded-full bg-blue-100 dark:bg-blue-900/30 text-blue-700 dark:text-blue-300 text-sm font-medium mb-6">
                            <Sparkles className="w-4 h-4" />
                            <span>AI-Powered Property Discovery</span>
                        </div>

                        <h2 className="text-4xl md:text-5xl font-bold text-slate-900 dark:text-white mb-4">
                            Find Your Perfect Property
                            <br />
                            <span className="bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
                                In Minutes, Not Months
                            </span>
                        </h2>

                        <p className="text-lg text-slate-600 dark:text-slate-300 max-w-2xl mx-auto mb-8">
                            Just describe what you're looking for in plain English. Our AI agents will analyze properties and provide transparent, explainable recommendations.
                        </p>
                    </div>
                )}

                {/* Search Bar */}
                <div className="mb-8">
                    <SearchBar
                        onSearch={handleSearch}
                        isLoading={searchState.isSearching}
                        onClear={clearSearch}
                    />
                </div>

                {/* Example Queries */}
                {!searchState.isSearching && !searchState.results && (
                    <div className="mb-12 animate-slide-up">
                        <p className="text-sm font-medium text-slate-700 dark:text-slate-300 mb-3">
                            Try these examples:
                        </p>
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-3">
                            {exampleQueries.map((example, index) => (
                                <button
                                    key={index}
                                    onClick={() => handleSearch(example)}
                                    className="text-left px-4 py-3 rounded-lg border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800 hover:border-blue-500 dark:hover:border-blue-400 hover:shadow-md transition-all duration-200 text-sm text-slate-700 dark:text-slate-300 group"
                                >
                                    <Search className="w-4 h-4 inline mr-2 text-slate-400 group-hover:text-blue-500" />
                                    {example}
                                </button>
                            ))}
                        </div>
                    </div>
                )}

                {/* Intent Preview */}
                {searchState.intent && (
                    <div className="mb-8 animate-fade-in">
                        <IntentPreview intent={searchState.intent} />
                    </div>
                )}

                {/* Streaming Progress */}
                {searchState.isSearching && (
                    <div className="mb-8 animate-fade-in">
                        <StreamingProgress events={searchState.events} />
                    </div>
                )}

                {/* Results */}
                {searchState.results && !searchState.isSearching && (
                    <div className="animate-fade-in">
                        <PropertyResults response={searchState.results} />
                    </div>
                )}

                {/* Error State */}
                {searchState.error && (
                    <div className="max-w-2xl mx-auto p-6 rounded-lg border-2 border-red-200 bg-red-50 dark:bg-red-900/20 dark:border-red-800">
                        <h3 className="text-lg font-semibold text-red-900 dark:text-red-200 mb-2">
                            Search Error
                        </h3>
                        <p className="text-red-700 dark:text-red-300">
                            {searchState.error}
                        </p>
                        <button
                            onClick={clearSearch}
                            className="mt-4 px-4 py-2 rounded-lg bg-red-600 hover:bg-red-700 text-white font-medium transition-colors"
                        >
                            Try Again
                        </button>
                    </div>
                )}
            </div>

            {/* Footer */}
            <footer className="border-t border-slate-200 dark:border-slate-800 mt-20">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
                    <div className="text-center text-sm text-slate-600 dark:text-slate-400">
                        <p>© 2024 Property AI. Built with Spring Boot, LangGraph, and Next.js.</p>
                        <p className="mt-2">
                            <span className="font-medium text-green-600 dark:text-green-400">✓</span> Transparent AI •
                            <span className="font-medium text-green-600 dark:text-green-400 ml-1">✓</span> Explainable Scores •
                            <span className="font-medium text-green-600 dark:text-green-400 ml-1">✓</span> Real-time Analysis
                        </p>
                    </div>
                </div>
            </footer>
        </main>
    )
}
