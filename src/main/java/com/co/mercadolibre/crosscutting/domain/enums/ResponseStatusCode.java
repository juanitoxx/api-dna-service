package com.co.mercadolibre.crosscutting.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatusCode {
	OK("OK"), ERROR("ERROR"), UNAUTHORIZED("UNAUTHORIZED"), FORBIDDEN("FORBIDDEN");

	private String status;
}
