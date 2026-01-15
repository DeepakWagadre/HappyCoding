'use client'

import { Building2, Bed, Maximize, MapPin, TrendingUp, AlertTriangle, Star } from 'lucide-react'

interface PropertyResultsProps {
    response: any
}

export default function PropertyResults({ response }: PropertyResultsProps) {
    const { top_recommendations, alternatives } = response

    if (!top_recommendations || top_recommendations.length === 0) {
        return (
            <div className="max-w-4xl mx-auto text-center p-12 rounded-xl border border-slate-200 dark:border-slate-700 bg-white dark:bg-slate-800">
                <p className="text-lg text-slate-600 dark:text-slate-400">
                    No properties found matching your criteria. Try adjusting your search.
                </p>
            </div>
        )
    }

    const PropertyCard = ({ property, rank }: { property: any; rank?: number }) => {
        const score = property.scores?.final_score || 0
        const scoreColor = score >= 8 ? 'text-green-600' : score >= 6 ? 'text-blue-600' : 'text-orange-600'
        const scoreBg = score >= 8 ? 'bg-green-100 dark:bg-green-900/30' : score >= 6 ? 'bg-blue-100 dark:bg-blue-900/30' : 'bg-orange-100 dark:bg-orange-900/30'

        return (
            <div className="p-6 rounded-xl border border-slate-200 dark:border-slate-700 bg-white  dark:bg-slate-800 hover:shadow-xl hover:border-blue-300 dark:hover:border-blue-600 transition-all duration-300 group">
                {/* Header */}
                <div className="flex items-start justify-between mb-4">
                    <div className="flex-1">
                        {rank && (
                            <span className="inline-flex items-center justify-center w-8 h-8 rounded-full bg-gradient-to-br from-blue-500 to-indigo-600 text-white text-sm font-bold mb-2">
                                #{rank}
                            </span>
                        )}
                        <h3 className="text-xl font-bold text-slate-900 dark:text-white mb-1 group-hover:text-blue-600 dark:group-hover:text-blue-400 transition-colors">
                            {property.property_name}
                        </h3>
                        <p className="text-sm text-slate-600 dark:text-slate-400 flex items-center">
                            <MapPin className="w-4 h-4 mr-1" />
                            {property.location}
                        </p>
                    </div>

                    <div className={`px-4 py-2 rounded-lg ${scoreBg} flex flex-col items-center`}>
                        <span className={`text-2xl font-bold ${scoreColor}`}>{score.toFixed(1)}</span>
                        <span className="text-xs text-slate-600 dark:text-slate-400">Score</span>
                    </div>
                </div>

                {/* Property Details */}
                <div className="grid grid-cols-3 gap-4 mb-4 pb-4 border-b border-slate-200 dark:border-slate-700">
                    <div className="flex items-center space-x-2">
                        <Building2 className="w-4 h-4 text-slate-400" />
                        <div>
                            <p className="text-xs text-slate-500 dark:text-slate-400">Type</p>
                            <p className="text-sm font-semibold text-slate-900 dark:text-white">{property.property_type}</p>
                        </div>
                    </div>

                    <div className="flex items-center space-x-2">
                        <Bed className="w-4 h-4 text-slate-400" />
                        <div>
                            <p className="text-xs text-slate-500 dark:text-slate-400">Bedrooms</p>
                            <p className="text-sm font-semibold text-slate-900 dark:text-white">{property.bedrooms} BHK</p>
                        </div>
                    </div>

                    <div className="flex items-center space-x-2">
                        <Maximize className="w-4 h-4 text-slate-400" />
                        <div>
                            <p className="text-xs text-slate-500 dark:text-slate-400">Area</p>
                            <p className="text-sm font-semibold text-slate-900 dark:text-white">{property.area_sqft} sqft</p>
                        </div>
                    </div>
                </div>

                {/* Price */}
                <div className="mb-4">
                    <p className="text-2xl font-bold text-slate-900 dark:text-white">
                        {property.price_range}
                    </p>
                    <p className="text-sm text-slate-600 dark:text-slate-400">
                        {property.builder} • {property.possession_status}
                    </p>
                </div>

                {/* Why Recommended */}
                {property.why_recommended && property.why_recommended.length > 0 && (
                    <div className="mb-4">
                        <p className="text-sm font-semibold text-slate-700 dark:text-slate-300 mb-2 flex items-center">
                            <Star className="w-4 h-4 mr-1 text-green-600" />
                            Why Recommended
                        </p>
                        <ul className="space-y-1">
                            {property.why_recommended.slice(0, 3).map((reason: string, idx: number) => (
                                <li key={idx} className="text-sm text-slate-600 dark:text-slate-400 flex items-start">
                                    <span className="text-green-600 mr-2">✓</span>
                                    <span>{reason}</span>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}

                {/* Key Risks */}
                {property.key_risks && property.key_risks.length > 0 && (
                    <div className="mb-4">
                        <p className="text-sm font-semibold text-slate-700 dark:text-slate-300 mb-2 flex items-center">
                            <AlertTriangle className="w-4 h-4 mr-1 text-orange-600" />
                            Consider
                        </p>
                        <ul className="space-y-1">
                            {property.key_risks.slice(0, 2).map((risk: string, idx: number) => (
                                <li key={idx} className="text-sm text-slate-600 dark:text-slate-400 flex items-start">
                                    <span className="text-orange-600 mr-2">!</span>
                                    <span>{risk}</span>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}

                {/* Score Breakdown */}
                {property.scores && (
                    <div className="mt-4 pt-4 border-t border-slate-200 dark:border-slate-700">
                        <p className="text-xs font-semibold text-slate-500 dark:text-slate-400 mb-2">Score Breakdown</p>
                        <div className="grid grid-cols-3 gap-2">
                            <ScorePill label="Location" score={property.scores.location_score} />
                            <ScorePill label="Price" score={property.scores.price_fairness_score} />
                            <ScorePill label="Amenities" score={property.scores.amenities_match_score} />
                            <ScorePill label="Commute" score={property.scores.commute_score} />
                            <ScorePill label="Growth" score={property.scores.growth_potential_score} />
                            <ScorePill label="Livability" score={property.scores.livability_score} />
                        </div>
                    </div>
                )}
            </div>
        )
    }

    const ScorePill = ({ label, score }: { label: string; score: number }) => {
        const percentage = (score / 10) * 100
        return (
            <div className="text-center">
                <p className="text-xs text-slate-500 dark:text-slate-400 mb-1">{label}</p>
                <div className="w-full h-1.5 bg-slate-200 dark:bg-slate-700 rounded-full overflow-hidden">
                    <div
                        className="h-full bg-gradient-to-r from-blue-500 to-indigo-600 rounded-full transition-all duration-500"
                        style={{ width: `${percentage}%` }}
                    />
                </div>
                <p className="text-xs font-semibold text-slate-700 dark:text-slate-300 mt-1">{score.toFixed(1)}</p>
            </div>
        )
    }

    return (
        <div className="space-y-8">
            {/* Top Recommendations */}
            <div>
                <h2 className="text-2xl font-bold text-slate-900 dark:text-white mb-6 flex items-center">
                    <Star className="w-6 h-6 mr-2 text-yellow-500" />
                    Top Recommendations
                </h2>
                <div className="grid grid-cols-1 gap-6">
                    {top_recommendations.map((property: any, index: number) => (
                        <PropertyCard key={property.property_id} property={property} rank={index + 1} />
                    ))}
                </div>
            </div>

            {/* Alternatives */}
            {alternatives && alternatives.length > 0 && (
                <div>
                    <h2 className="text-2xl font-bold text-slate-900 dark:text-white mb-6">
                        Alternative Options
                    </h2>
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                        {alternatives.map((property: any) => (
                            <PropertyCard key={property.property_id} property={property} />
                        ))}
                    </div>
                </div>
            )}

            {/* Assumptions */}
            {response.assumptions && response.assumptions.length > 0 && (
                <div className="p-4 rounded-lg border border-blue-200 bg-blue-50 dark:bg-blue-900/20 dark:border-blue-800">
                    <p className="text-sm font-semibold text-blue-900 dark:text-blue-200 mb-2">Assumptions Made:</p>
                    <ul className="space-y-1">
                        {response.assumptions.map((assumption: string, idx: number) => (
                            <li key={idx} className="text-sm text-blue-700 dark:text-blue-300">• {assumption}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    )
}
