package com.co.mercadolibre.crosscutting.translate;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;
import com.co.mercadolibre.crosscutting.patterns.Translator;
import com.co.mercadolibre.crosscutting.persistence.entity.Dna;

@Component
@Qualifier("dnaDTOToEntityTranslate")
public class DnaDTOToEntityTranslate implements Translator<DnaDTO, Dna> {

	@Override
	public Dna translate(DnaDTO input) {
		return Dna.builder().dnaChain(input.getDnaField()).creationDate(new Date()).mutant(input.isMutant())
				.transactionId(input.getTransactionId()).build();
	}

}
