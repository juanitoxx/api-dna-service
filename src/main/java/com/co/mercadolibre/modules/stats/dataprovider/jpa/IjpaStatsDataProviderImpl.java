package com.co.mercadolibre.modules.stats.dataprovider.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.co.mercadolibre.crosscutting.domain.constants.ErrorConstants;
import com.co.mercadolibre.crosscutting.domain.dto.StatsDTO;
import com.co.mercadolibre.crosscutting.persistence.entity.Dna;
import com.co.mercadolibre.crosscutting.persistence.repository.IDnaRepository;
import com.co.mercadolibre.modules.stats.dataprovider.IjpaStatsDataProvider;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class IjpaStatsDataProviderImpl implements IjpaStatsDataProvider {

	@Autowired private IDnaRepository dnaRepository;
	
	@Override
	public StatsDTO getStats() {
		try {
			List<Dna> list = (List<Dna>)dnaRepository.findAll();
			if(!list.isEmpty()) {
				List<Dna> listMutants = list.stream().filter(Dna::isMutant).collect(Collectors.toList());
				List<Dna> listHumans = list.stream().filter(obj->!obj.isMutant()).collect(Collectors.toList());

				return buildStats(listMutants, listHumans);
			}
			return new StatsDTO();
		} catch (Exception e) {
			log.error(ErrorConstants.ERROR_QUERY_STATS,e.getMessage());
			throw e;
		}
	}
	
	private StatsDTO buildStats(List<Dna> listMutants, List<Dna> listHumans) {
		var result = calculateRatio(listHumans.size(), listMutants.size());
		return StatsDTO.builder()
				.countHumanDna(listHumans.size())
				.countMutantDna(listMutants.size())
				.ratio(result)
				.build();
	}
	
	private Double calculateRatio(Integer countHumans, Integer countMutants) {
		return (double) (countMutants/(double)countHumans);
	}

}
