package com.co.mercadolibre.crosscutting.translate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;
import com.co.mercadolibre.crosscutting.patterns.Translator;
import com.co.mercadolibre.crosscutting.persistence.entity.Dna;

@Component
@Qualifier("dnaSaveResponseTranslate")
public class DnaSaveResponseTranslate implements Translator<Dna, DnaDTO> {

	@Override
	public DnaDTO translate(Dna input) {
		return DnaDTO.builder()
				.mutant(input.isMutant())
				.transactionId(input.getTransactionId())
				.dnaField(input.getDnaChain())
				.creationDate(input.getCreationDate())
				.build();
	}
	

}
