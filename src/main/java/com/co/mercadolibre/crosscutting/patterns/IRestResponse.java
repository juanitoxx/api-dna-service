package com.co.mercadolibre.crosscutting.patterns;

import com.co.mercadolibre.crosscutting.domain.dto.ResponseStatus;

public interface IRestResponse<T> {

	ResponseStatus getResponseStatus();

	T getResponse();

	int getHttpStatusCode();

}
