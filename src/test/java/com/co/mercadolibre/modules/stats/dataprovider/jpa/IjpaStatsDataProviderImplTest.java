package com.co.mercadolibre.modules.stats.dataprovider.jpa;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.co.mercadolibre.crosscutting.domain.exceptions.DnaException;
import com.co.mercadolibre.crosscutting.persistence.entity.Dna;
import com.co.mercadolibre.crosscutting.persistence.repository.IDnaRepository;

@RunWith(MockitoJUnitRunner.class)
public class IjpaStatsDataProviderImplTest {

	@InjectMocks private IjpaStatsDataProviderImpl dataProviderImpl;
	@Mock private IDnaRepository dnaRepository;
	
	private Dna dnaTrue;
	private Dna dnaFalse;
	private List<Dna>listDna;
	
	@Before
	public void processInformation() {
		listDna = new ArrayList<>();
		dnaTrue = Dna.builder().mutant(true).build();
		dnaFalse = Dna.builder().mutant(false).build();
		listDna.add(dnaFalse);
		listDna.add(dnaTrue);
		
	}
	
	@Test
	public void getStats() {
		when(dnaRepository.findAll()).thenReturn(listDna);
		assertNotNull(dataProviderImpl.getStats());
	}
	
	@Test
	public void getStatusEmpty() {
		when(dnaRepository.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(dataProviderImpl.getStats());
	}
	
	@Test(expected = DnaException.class)
	public void getStatsError() {
		doThrow(DnaException.class).when(dnaRepository).findAll();
		dataProviderImpl.getStats();
	}
}
