"""
Pydantic models for AI Agent Service
Strict schema matching the requirements
"""

from typing import List, Optional
from enum import Enum
from pydantic import BaseModel, Field


class TransactionType(str, Enum):
    BUY = "BUY"
    RENT = "RENT"


class PropertyType(str, Enum):
    APARTMENT = "APARTMENT"
    VILLA = "VILLA"
    PLOT = "PLOT"
    INDEPENDENT_HOUSE = "INDEPENDENT_HOUSE"
    COMMERCIAL = "COMMERCIAL"
    PENTHOUSE = "PENTHOUSE"
    STUDIO = "STUDIO"


class InvestmentGoal(str, Enum):
    END_USE = "END_USE"
    RENTAL_YIELD = "RENTAL_YIELD"
    APPRECIATION = "APPRECIATION"
    MIXED = "MIXED"


class ConfidenceLevel(str, Enum):
    HIGH = "HIGH"
    MEDIUM = "MEDIUM"
    LOW = "LOW"


class Budget(BaseModel):
    min: Optional[float] = None
    max: Optional[float] = None


class Amenities(BaseModel):
    must_have: List[str] = Field(default_factory=list)
    nice_to_have: List[str] = Field(default_factory=list)


class Commute(BaseModel):
    office_location: Optional[str] = None
    max_travel_time_minutes: Optional[int] = None


class FamilyContext(BaseModel):
    kids: Optional[bool] = None
    school_priority: Optional[bool] = None
    senior_citizens: Optional[bool] = None


class UserIntent(BaseModel):
    """
    Structured intent extracted from natural language query
    STRICT JSON format as per requirements
    """
    transaction_type: Optional[TransactionType] = None
    property_type: Optional[PropertyType] = None
    budget: Optional[Budget] = None
    preferred_locations: List[str] = Field(default_factory=list)
    amenities: Optional[Amenities] = None
    commute: Optional[Commute] = None
    family_context: Optional[FamilyContext] = None
    investment_goal: Optional[InvestmentGoal] = InvestmentGoal.END_USE
    confidence_level: ConfidenceLevel = ConfidenceLevel.MEDIUM


class PropertyScore(BaseModel):
    """Property scoring from AI agents"""
    location_score: float = Field(ge=0, le=10, default=5.0)
    price_fairness_score: float = Field(ge=0, le=10, default=5.0)
    amenities_match_score: float = Field(ge=0, le=10, default=5.0)
    commute_score: float = Field(ge=0, le=10, default=5.0)
    growth_potential_score: float = Field(ge=0, le=10, default=5.0)
    livability_score: float = Field(ge=0, le=10, default=5.0)
    risk_penalty: float = Field(ge=-5, le=0, default=0.0)
    final_score: float = Field(ge=0, le=10, default=5.0)
    confidence_level: ConfidenceLevel = ConfidenceLevel.MEDIUM


class Property(BaseModel):
    """Property data from backend"""
    id: Optional[int] = None
    propertyId: str
    name: str
    location: str
    subLocation: Optional[str] = None
    price: float
    propertyType: PropertyType
    transactionType: TransactionType
    bedrooms: int
    bathrooms: int
    areaSqft: int
    builder: Optional[str] = None
    builderRating: Optional[str] = None
    possessionStatus: Optional[str] = None
    possessionYear: Optional[int] = None
    latitude: Optional[float] = None
    longitude: Optional[float] = None
    amenities: List[str] = Field(default_factory=list)
    nearbyPlaces: List[str] = Field(default_factory=list)
    description: Optional[str] = None
    reraId: Optional[str] = None
    reraApproved: Optional[bool] = None
    legalStatus: Optional[str] = None
    pricePerSqft: Optional[float] = None
    expectedAppreciation: Optional[float] = None
    rentalYield: Optional[float] = None
    marketValue: Optional[float] = None
    nearestMetroDistanceKm: Optional[int] = None
    nearestMetroStation: Optional[str] = None
    nearestAirportDistanceKm: Optional[int] = None


class PropertyAnalysisResult(BaseModel):
    """Result from multi-agent analysis"""
    property_id: str = Field(alias="propertyId")
    scores: PropertyScore
    why_recommended: List[str] = Field(default_factory=list)
    key_risks: List[str] = Field(default_factory=list)

    class Config:
        populate_by_name = True


# API Request/Response Models

class IntentExtractionRequest(BaseModel):
    query: str


class IntentExtractionResponse(BaseModel):
    intent: UserIntent
    confidence: str
    clarifications_needed: List[str] = Field(default_factory=list)


class PropertyAnalysisRequest(BaseModel):
    intent: UserIntent
    properties: List[Property]


class AgentAnalysisResponse(BaseModel):
    results: List[PropertyAnalysisResult]
    assumptions: List[str] = Field(default_factory=list)
    areas_to_avoid: List[str] = Field(default_factory=list)
    follow_up_questions: List[str] = Field(default_factory=list)
