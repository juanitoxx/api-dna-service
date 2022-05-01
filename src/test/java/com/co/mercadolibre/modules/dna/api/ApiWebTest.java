package com.co.mercadolibre.modules.dna.api;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;
import com.co.mercadolibre.crosscutting.domain.dto.ResponseStatus;
import com.co.mercadolibre.crosscutting.domain.enums.ResponseStatusCode;
import com.co.mercadolibre.crosscutting.domain.exceptions.InvalidRequestException;
import com.co.mercadolibre.crosscutting.domain.handler.ResponseExceptionHandler;
import com.co.mercadolibre.modules.dna.usecase.DnaService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ApiWebTest {

	@InjectMocks
	private ApiWeb apiWeb;

	@Mock
	private DnaService dnaService;

	@Mock
	private BindingResult bindingResult;
	
	@Mock private ResponseExceptionHandler exceptionHandler;

	@Resource
	private Validator validator;

	private DnaDTO dnaDTO;
	private DnaDTO dnaDTOError;

	@Before
	public void processInformation() {
		List<String> dnaList = new ArrayList<>();
		dnaList.add("ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTAG\",\"ACACTG\"");
		dnaDTO = DnaDTO.builder().dnaField(dnaList).build();
		dnaDTOError = DnaDTO.builder().dnaField(null).build();
		
	}

	@Test
	public void processMutant() {
		when(dnaService.processData(dnaDTO))
				.thenReturn(ResponseStatus.builder().statusCode(ResponseStatusCode.OK).build());
		assertNotNull(apiWeb.processMutant(dnaDTO, bindingResult));
	}

	@Test
	public void processMutantError() {
		when(bindingResult.hasErrors()).thenReturn(true);
		assertNotNull(apiWeb.processMutant(DnaDTO.builder().transactionId("").build(), null));
	}

	@Test(expected = InvalidRequestException.class)
	public void processMutantHasErrorList() {
		when(bindingResult.getFieldErrors())
		.thenReturn(Arrays.asList(new FieldError("dnaField", "NotNull", "no debe ser nulo")));
		apiWeb.processMutant(dnaDTOError, bindingResult);
	}
}
