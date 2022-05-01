package com.co.mercadolibre.crosscutting.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsDTO implements Serializable {
	private static final long serialVersionUID = 1020133704402949464L;

	@JsonProperty("count_mutant_dna")
	private Integer countMutantDna;
	@JsonProperty("count_human_dna")
	private Integer countHumanDna;
	@JsonProperty("ratio")
	private Double ratio;
}
