package com.co.mercadolibre.modules.stats.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.mercadolibre.crosscutting.domain.dto.StatsDTO;
import com.co.mercadolibre.modules.stats.usecase.StatsService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping(value = "/stats/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class StatsApi {
	
	@Autowired private StatsService statsService;
	
	@GetMapping
	public ResponseEntity<StatsDTO> getStats(){
		log.info("Verify stats from DataBase");
		return ResponseEntity.ok(statsService.getStats());
	}

}
