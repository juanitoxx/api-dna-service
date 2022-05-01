package com.co.mercadolibre.crosscutting.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError implements Serializable {

	private static final long serialVersionUID = 5083646043196822302L;
	private String field;
	private String code;
	private String defaultMessage;
}
