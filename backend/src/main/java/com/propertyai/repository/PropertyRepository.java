package com.propertyai.repository;

import com.propertyai.model.Property;
import com.propertyai.model.Property.PropertyType;
import com.propertyai.model.Property.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Property entity with custom queries
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByPropertyId(String propertyId);

    List<Property> findByLocation(String location);

    List<Property> findByPropertyTypeAndTransactionType(
            PropertyType propertyType,
            TransactionType transactionType);

    @Query("SELECT p FROM Property p WHERE p.transactionType = :transactionType " +
            "AND p.price BETWEEN :minPrice AND :maxPrice " +
            "ORDER BY p.price ASC")
    List<Property> findByTransactionTypeAndPriceRange(
            @Param("transactionType") TransactionType transactionType,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT p FROM Property p WHERE p.location IN :locations " +
            "AND p.transactionType = :transactionType " +
            "AND p.price <= :maxPrice " +
            "ORDER BY p.createdAt DESC")
    List<Property> findByLocationsAndBudget(
            @Param("locations") List<String> locations,
            @Param("transactionType") TransactionType transactionType,
            @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT p FROM Property p WHERE p.propertyType = :propertyType " +
            "AND p.transactionType = :transactionType " +
            "AND p.bedrooms >= :minBedrooms " +
            "AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Property> findByDetailedCriteria(
            @Param("propertyType") PropertyType propertyType,
            @Param("transactionType") TransactionType transactionType,
            @Param("minBedrooms") Integer minBedrooms,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT DISTINCT p.location FROM Property p WHERE p.transactionType = :transactionType")
    List<String> findAllLocations(@Param("transactionType") TransactionType transactionType);

    @Query("SELECT p FROM Property p WHERE p.reraApproved = true AND p.legalStatus = 'Clear'")
    List<Property> findLegallyVerifiedProperties();

    @Query(value = "SELECT p FROM Property p WHERE " +
            "p.transactionType = :transactionType AND " +
            "p.price BETWEEN :minPrice AND :maxPrice AND " +
            "(:propertyType IS NULL OR p.propertyType = :propertyType) AND " +
            "(:location IS NULL OR p.location = :location OR p.subLocation = :location)")
    List<Property> searchProperties(
            @Param("transactionType") TransactionType transactionType,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("propertyType") PropertyType propertyType,
            @Param("location") String location);
}
