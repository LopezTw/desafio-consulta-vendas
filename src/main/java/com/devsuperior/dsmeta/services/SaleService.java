package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	
	
	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleMinDTO> search1(String minDate, String maxDate, String name, Pageable pageable) {
        LocalDate parsedMinDate = parseDate(minDate);
        LocalDate parsedMaxDate = parseDate(maxDate);

        return repository.findSalesByFilters(parsedMinDate, parsedMaxDate, name, pageable);
    }

	   public Page<SaleSummaryDTO> findSummaryTotalAmountBySellerName(String minDate, String maxDate, Pageable page) {
		   	LocalDate parsedMinDate = parseDate(minDate);
	        LocalDate parsedMaxDate = parseDate(maxDate);
	        
	        if (parsedMinDate == null && parsedMaxDate != null) {
	            // Se minDate não foi fornecido, calcula a data de um ano atrás a partir de maxDate
	            parsedMinDate = parsedMaxDate.minus(1, ChronoUnit.YEARS);
	        }
		    if (parsedMaxDate == null) {
		        parsedMaxDate = LocalDate.now();
		    }
		    return repository.findSummaryTotalAmountBySellerName(parsedMinDate, parsedMaxDate, page);
	   }
	   
		private LocalDate parseDate(String dateString) {
			try {
				return dateString != null ? LocalDate.parse(dateString) : null;
			} catch (DateTimeParseException e) {
				// Adicionar exceçao personalizada se for necessário
				return null;
			}
		}
}
