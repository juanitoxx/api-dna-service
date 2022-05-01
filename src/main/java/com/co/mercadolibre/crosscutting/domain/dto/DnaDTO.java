package com.co.mercadolibre.crosscutting.domain.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DnaDTO {
	
	@JsonProperty("transactionId")
	private String transactionId;
	
	@JsonProperty("mutant")
	private boolean mutant;
	
	@JsonProperty("creationDate")
	private Date creationDate;
	
	@JsonProperty("dna")
	@NotNull @NotEmpty private List<String> dnaField;
}
