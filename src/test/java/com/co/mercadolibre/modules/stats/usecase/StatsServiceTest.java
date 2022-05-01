package com.co.mercadolibre.modules.stats.usecase;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.co.mercadolibre.crosscutting.domain.dto.StatsDTO;
import com.co.mercadolibre.crosscutting.domain.exceptions.DnaException;
import com.co.mercadolibre.modules.stats.dataprovider.jpa.IjpaStatsDataProviderImpl;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceTest {

	@InjectMocks private StatsService service;
	@Mock private IjpaStatsDataProviderImpl dataProviderImpl;
	private StatsDTO statsDTO;
	
	@Before
	public void processInformation() {
		statsDTO = StatsDTO.builder().countHumanDna(4).countMutantDna(2).ratio(0.5).build();
	}
	
	@Test
	public void getStats() {
		when(dataProviderImpl.getStats()).thenReturn(statsDTO);
		assertNotNull(service.getStats());
	}
	
	@Test(expected = DnaException.class)
	public void getStatsError() {
		doThrow(DnaException.class).when(dataProviderImpl).getStats();
		service.getStats();
	}
}
