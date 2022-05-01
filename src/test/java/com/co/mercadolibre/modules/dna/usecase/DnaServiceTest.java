package com.co.mercadolibre.modules.dna.usecase;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;
import com.co.mercadolibre.crosscutting.domain.exceptions.DnaException;
import com.co.mercadolibre.modules.dna.dataprovider.jpa.IjpaDnaDataProviderImpl;

@RunWith(MockitoJUnitRunner.class)
public class DnaServiceTest {
	
	@InjectMocks private DnaService dnaService;
	@Mock private IjpaDnaDataProviderImpl dataProviderImpl;
	
	private DnaDTO dnaDTO;
	private DnaDTO dnaDTOError;
	private DnaDTO dto;
	private List<String>differentCharacter;
	private List<String> dnaList;
	private List<String> dnaErrorList;
	

	@Before
	public void processInformation() {
		dnaList = new ArrayList<>();
		dnaErrorList = new ArrayList<>();
		differentCharacter = new ArrayList<>();
		dnaList.add("ATGCGA");
		dnaList.add("CAGTGC");
		dnaList.add("TTATGT");
		dnaList.add("AGAAGG");
		dnaList.add("CCCCTA");
		dnaList.add("TCACTG");
		
		dnaErrorList.add("ATGCGA");
		dnaErrorList.add("CAGTGC");
		dnaErrorList.add("TTATTT");
		dnaErrorList.add("AGACGG");
		dnaErrorList.add("GCGTAG");
		dnaErrorList.add("ACACTG");
		
		differentCharacter.add("kjllop");
		differentCharacter.add("CAGTGC");
		differentCharacter.add("TdATTT");
		differentCharacter.add("AGACGG");
		differentCharacter.add("GCGTAG");
		differentCharacter.add("ACACTG");
		
		dto =  DnaDTO.builder().dnaField(differentCharacter).transactionId("ffcb5b07dc674c5db575d10adfedd899").build();
		dnaDTOError =  DnaDTO.builder().dnaField(dnaErrorList).transactionId("ffcb5b07dc674c5db575d10adfedd899").build();
		dnaDTO = DnaDTO.builder().dnaField(dnaList).transactionId("ffcb5b07dc674c5db575d10adfedd899").build();
	}
	
	@Test
	public void processDataTest() {
		when(dataProviderImpl.createDnaTransaction(dnaDTO)).thenReturn(dnaDTO);
		assertNotNull(dnaService.processData(dnaDTO));
	}
	
	@Test(expected = DnaException.class)
	public void processDataTestFalse() {
		when(dataProviderImpl.createDnaTransaction(dnaDTOError)).thenReturn(dnaDTOError);
		dnaService.processData(dnaDTOError);
	}
	
	@Test(expected = DnaException.class)
	public void processDataTestError() {
		dnaService.processData(dto);
		
	}
}
