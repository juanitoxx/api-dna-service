package com.co.mercadolibre.modules.dna.dataprovider;

import java.util.List;

import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;

public interface IjpaDnaDataProvider {

	DnaDTO createDnaTransaction(final DnaDTO dnaDto);
	
	DnaDTO findByDnaChain(final List<String> dnaChain);
}
