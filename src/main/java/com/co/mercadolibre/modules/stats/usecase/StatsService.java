package com.co.mercadolibre.modules.stats.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.mercadolibre.crosscutting.domain.dto.StatsDTO;
import com.co.mercadolibre.modules.stats.dataprovider.IjpaStatsDataProvider;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class StatsService {
	
	@Autowired private IjpaStatsDataProvider dataProvider;
	
	public StatsDTO getStats() {
		try {
			return dataProvider.getStats();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
