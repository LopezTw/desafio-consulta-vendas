package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
	        @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
	        @RequestParam(value = "name", defaultValue = "") String name, Pageable page) {
		
		Page<SaleMinDTO> list = service.search1(minDate, maxDate, name, page);
		return ResponseEntity.ok(list);
		
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSummaryDTO>> getTotalAmountPerSeller(
			@RequestParam(required = false) String minDate,
	        @RequestParam(required = false) String maxDate,
	        Pageable page) {
		
        
	    Page<SaleSummaryDTO> summary = service.findSummaryTotalAmountBySellerName(minDate, maxDate, page);
	    return ResponseEntity.ok(summary);
    }
}
