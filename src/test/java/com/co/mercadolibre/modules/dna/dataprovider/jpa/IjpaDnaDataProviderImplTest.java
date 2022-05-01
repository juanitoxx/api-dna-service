package com.co.mercadolibre.modules.dna.dataprovider.jpa;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;

import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;
import com.co.mercadolibre.crosscutting.patterns.Translator;
import com.co.mercadolibre.crosscutting.persistence.entity.Dna;
import com.co.mercadolibre.crosscutting.persistence.repository.IDnaRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class IjpaDnaDataProviderImplTest {

	@InjectMocks
	private IjpaDnaDataProviderImpl dataProvider;
	@Mock
	private IDnaRepository dnaRepository;

	@Mock
	@Qualifier("dnaDTOToEntityTranslate")
	private Translator<DnaDTO, Dna> dnaDTOToEntityTranslate;

	@Mock
	@Qualifier("dnaSaveResponseTranslate")
	private Translator<Dna, DnaDTO> dnaSaveResponseTranslate;

	private DnaDTO dnaDTO;
	private Dna dna;
	private List<String> dnaList;
	private List<String> dnalistResponse;

	@Before
	public void processInformation() {
		dnaList = new ArrayList<>();
		dnalistResponse = new ArrayList<>();
		dnaList.add("ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTAG\",\"ACACTG\"");
		dnalistResponse.add(
				"[ { \"S\" : \"ATGCGA\" }, { \"S\" : \"CAGTGA\" }, " + "{ \"S\" : \"TTATGA\" }, { \"S\" : \"AGACGA\" },"
						+ " { \"S\" : \"GCGTCA\" }, { \"S\" : \"TCACTG\" } ]	");
		dnaDTO = DnaDTO.builder().dnaField(dnaList).transactionId("ffcb5b07dc674c5db575d10adfedd899").build();
		dna = Dna.builder().creationDate(new Date()).transactionId("ffcb5b07dc674c5db575d10adfedd899")
				.dnaChain(dnalistResponse).build();
	}

	@Test
	public void createDnaTransactionWithID() {
		when(dnaRepository.findByDnaChain(dnaList)).thenReturn(dna);
		when(dnaSaveResponseTranslate.translate(dna)).thenReturn(dnaDTO);
		assertNotNull(dataProvider.createDnaTransaction(dnaDTO));
	}
	@Test
	public void createDnaTransaction() {
		when(dnaRepository.findByDnaChain(dnaList)).thenReturn(null);
		when(dnaRepository.save(dna)).thenReturn(dna);
		when(dnaDTOToEntityTranslate.translate(dnaDTO)).thenReturn(dna);
		when(dnaSaveResponseTranslate.translate(dna)).thenReturn(dnaDTO);
		assertNotNull(dataProvider.createDnaTransaction(dnaDTO));
	}
	
	@Test(expected = Exception.class)
	public void createDnaTransactionException() {
		doThrow(Exception.class).when(dnaSaveResponseTranslate).translate(dna);
		when(dnaRepository.save(dna)).thenReturn(dna);
		when(dnaDTOToEntityTranslate.translate(dnaDTO)).thenReturn(dna);
		dataProvider.createDnaTransaction(dnaDTO);	
	}
	
	@Test(expected = Exception.class)
	public void findByDnaChain() {
		doThrow(Exception.class).when(dnaSaveResponseTranslate).translate(dna);
		when(dnaRepository.findByDnaChain(dnaList)).thenReturn(dna);
		dataProvider.createDnaTransaction(dnaDTO);	
	}
	
}
