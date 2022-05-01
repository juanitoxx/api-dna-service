package com.co.mercadolibre.crosscutting.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.co.mercadolibre.crosscutting.domain.dto.ResponseStatus;
import com.co.mercadolibre.crosscutting.domain.dto.ValidationError;
import com.co.mercadolibre.crosscutting.domain.enums.ResponseStatusCode;
import com.co.mercadolibre.crosscutting.patterns.IRestResponse;
import com.co.mercadolibre.crosscutting.patterns.impl.RestResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseEntityUtil {

	public static <T> ResponseEntity<IRestResponse<T>> createResponseEntity(final IRestResponse<T> response) {
		return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatusCode())).body(response);
	}

	public static ResponseEntity<IRestResponse<List<ValidationError>>> createResponseValidationError(
			final List<ValidationError> errors) {
		final RestResponse<List<ValidationError>> fullResponse = new RestResponse<>();

		if (errors != null && !errors.isEmpty()) {
			final ResponseStatus responseStatus = getErrorResponseStatus("Validation error");

			fullResponse.setResponse(errors);
			fullResponse.setResponseStatus(responseStatus);
			fullResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
		}

		return createResponseEntity(fullResponse);
	}

	private static ResponseStatus getErrorResponseStatus(final String message) {
		return ResponseStatus.builder().message(message).statusCode(ResponseStatusCode.ERROR).build();
	}
}
