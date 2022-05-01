package com.co.mercadolibre.crosscutting.translate;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;

@RunWith(MockitoJUnitRunner.class)
public class DnaDTOToEntityTranslateTest {

	@InjectMocks private DnaDTOToEntityTranslate dnaDTOToEntityTranslate;
	private DnaDTO dnaDto;
	
	@Before
	public void processInformation() {
		dnaDto = DnaDTO.builder().creationDate(new Date()).mutant(true).dnaField(new ArrayList<>()).build();
	}
	
	@Test
	public void translateTest() {
		assertNotNull(dnaDTOToEntityTranslate.translate(dnaDto));
	}
}
