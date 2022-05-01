package com.co.mercadolibre.modules.dna.dataprovider.jpa;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.co.mercadolibre.crosscutting.domain.constants.ErrorConstants;
import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;
import com.co.mercadolibre.crosscutting.patterns.Translator;
import com.co.mercadolibre.crosscutting.persistence.entity.Dna;
import com.co.mercadolibre.crosscutting.persistence.repository.IDnaRepository;
import com.co.mercadolibre.modules.dna.dataprovider.IjpaDnaDataProvider;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class IjpaDnaDataProviderImpl implements IjpaDnaDataProvider {

	@Autowired
	IDnaRepository dnaRepository;

	@Autowired
	@Qualifier("dnaDTOToEntityTranslate")
	private Translator<DnaDTO, Dna> dnaDTOToEntityTranslate;

	@Autowired
	@Qualifier("dnaSaveResponseTranslate")
	private Translator<Dna, DnaDTO> dnaSaveResponseTranslate;

	@Override
	public DnaDTO createDnaTransaction(final DnaDTO dnaDto) {
		try {
			DnaDTO response = findByDnaChain(dnaDto.getDnaField());
			if(Objects.isNull(response.getTransactionId())) {
				return dnaSaveResponseTranslate.translate(dnaRepository.save(dnaDTOToEntityTranslate.translate(dnaDto)));
			}
		} catch (Exception e) {
			log.error(ErrorConstants.ERROR_CREATE_SEQUENCE, e.getMessage());
			throw e;
		}
		return new DnaDTO();
	}

	@Override
	public DnaDTO findByDnaChain(final List<String> dnaChain) {
		try {
			Dna dna = dnaRepository.findByDnaChain(dnaChain);
			if (Objects.nonNull(dna)) {
				return dnaSaveResponseTranslate.translate(dna);
			}
		} catch (Exception e) {
			log.error(ErrorConstants.ERROR_QUERY_SEQUENCE, e.getMessage());
			throw e;
		}
		return new DnaDTO();
	}
	
	

}
