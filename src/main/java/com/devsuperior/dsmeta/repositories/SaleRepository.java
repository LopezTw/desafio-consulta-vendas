package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(value = "SELECT obj "
	        + "FROM Sale obj "
	        + "WHERE (:minDate IS NULL OR obj.date >= :minDate) "
	        + "AND (:maxDate IS NULL OR obj.date <= :maxDate) "
	        + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",
	        countQuery = "SELECT COUNT(obj) "
	                + "FROM Sale obj "
	                + "WHERE (:minDate IS NULL OR obj.date >= :minDate) "
	                + "AND (:maxDate IS NULL OR obj.date <= :maxDate) "
	                + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
			
	Page<SaleMinDTO> findSalesByFilters(LocalDate minDate, LocalDate maxDate, String name, Pageable page);

	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(s.seller.name, SUM(s.amount)) " +
	        "FROM Sale s " +
	        "WHERE (:minDate IS NULL OR s.date >= :minDate) " +
	        "AND (:maxDate IS NULL OR s.date <= :maxDate) " +
	        "GROUP BY s.seller.name")
	Page<SaleSummaryDTO> findSummaryTotalAmountBySellerName(
	        @Param("minDate") LocalDate minDate, 
	        @Param("maxDate") LocalDate maxDate,
	        Pageable page);

	  
}