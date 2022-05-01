package com.co.mercadolibre.modules.dna.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;
import com.co.mercadolibre.crosscutting.domain.dto.ResponseStatus;
import com.co.mercadolibre.crosscutting.util.UUIDUtils;
import com.co.mercadolibre.crosscutting.util.ValidationUtil;
import com.co.mercadolibre.modules.dna.usecase.DnaService;

import lombok.extern.log4j.Log4j2;


@RestController
@Log4j2
@RequestMapping(value = "/mutant/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ApiWeb {

	@Autowired
	private DnaService dnaService;

	@PostMapping
	public ResponseEntity<ResponseStatus> processMutant(@Valid @RequestBody DnaDTO dna, 
			final BindingResult bindingResult) {
		
		ValidationUtil.validateBindingResult(bindingResult);
		String uUid = UUIDUtils.randomUUIDWithoutDash();
		dna.setTransactionId(uUid);
		log.info("Verify mutant with transaction_id {}", uUid);
		return ResponseEntity.ok(dnaService.processData(dna));
	}
}
