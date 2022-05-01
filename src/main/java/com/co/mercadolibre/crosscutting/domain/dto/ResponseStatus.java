package com.co.mercadolibre.crosscutting.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.co.mercadolibre.crosscutting.domain.enums.ResponseStatusCode;

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
public class ResponseStatus implements Serializable {

	private static final long serialVersionUID = -4749950651111934060L;
	private ResponseStatusCode statusCode;
	private String message;
	private String status;
	private String detail;
	private LocalDateTime dateTime;
}
