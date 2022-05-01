package com.co.mercadolibre.crosscutting.patterns.impl;

import com.co.mercadolibre.crosscutting.domain.dto.ResponseStatus;
import com.co.mercadolibre.crosscutting.patterns.IRestResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;

@Setter
public class RestResponse<T> implements IRestResponse<T> {

	private ResponseStatus responseStatus;
	private T response;

	@JsonIgnore
	private int httpStatusCode;

	@Override
	public ResponseStatus getResponseStatus() {
		return this.responseStatus;
	}

	@Override
	public T getResponse() {
		return this.response;
	}

	@Override
	public int getHttpStatusCode() {
		return this.httpStatusCode;
	}

}
