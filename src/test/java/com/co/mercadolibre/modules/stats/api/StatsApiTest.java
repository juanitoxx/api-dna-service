package com.co.mercadolibre.modules.stats.api;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.co.mercadolibre.crosscutting.domain.dto.StatsDTO;
import com.co.mercadolibre.modules.stats.usecase.StatsService;

@RunWith(MockitoJUnitRunner.class)
public class StatsApiTest {
	
	@InjectMocks private StatsApi api;
	@Mock private StatsService service;
	private StatsDTO dto;
	
	public void processInformation() {
		dto = StatsDTO.builder().countHumanDna(4).countMutantDna(2).ratio(0.5).build();
	}
	
	@Test
	public void getStats() {
		when(service.getStats()).thenReturn(dto);
		assertNotNull(api.getStats());
	}

}
