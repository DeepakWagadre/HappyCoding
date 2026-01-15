package com.propertyai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Property entity representing real estate properties in Bangalore
 */
@Entity
@Table(name = "properties", indexes = {
        @Index(name = "idx_location", columnList = "location"),
        @Index(name = "idx_property_type", columnList = "propertyType"),
        @Index(name = "idx_price", columnList = "price"),
        @Index(name = "idx_transaction_type", columnList = "transactionType")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String propertyId;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(length = 100)
    private String subLocation;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType transactionType;

    @Column(nullable = false)
    private Integer bedrooms;

    @Column(nullable = false)
    private Integer bathrooms;

    @Column(nullable = false)
    private Integer areaSqft;

    @Column(length = 100)
    private String builder;

    @Column(length = 50)
    private String builderRating; // e.g., "4.5/5", "A-Grade"

    @Column(length = 20)
    private String possessionStatus; // Ready, Under Construction, Upcoming

    private Integer possessionYear;

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "property_amenities", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "amenity")
    private List<String> amenities;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "property_nearby_places", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "place")
    private List<String> nearbyPlaces; // Schools, Hospitals, Malls, etc.

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 50)
    private String reraId;

    private Boolean reraApproved;

    @Column(length = 50)
    private String legalStatus; // Clear, Disputed, Under Verification

    // Market metrics
    @Column(precision = 10, scale = 2)
    private BigDecimal pricePerSqft;

    @Column(precision = 5, scale = 2)
    private BigDecimal expectedAppreciation; // % per year

    @Column(precision = 5, scale = 2)
    private BigDecimal rentalYield; // % per year

    @Column(precision = 12, scale = 2)
    private BigDecimal marketValue;

    // Connectivity metrics
    private Integer nearestMetroDistanceKm;

    @Column(length = 100)
    private String nearestMetroStation;

    private Integer nearestAirportDistanceKm;

    // Scoring (computed by AI agents)
    @Transient
    private PropertyScore scores;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum PropertyType {
        APARTMENT,
        VILLA,
        PLOT,
        INDEPENDENT_HOUSE,
        COMMERCIAL,
        PENTHOUSE,
        STUDIO
    }

    public enum TransactionType {
        BUY,
        RENT
    }
}
