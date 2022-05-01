package com.co.mercadolibre.crosscutting.domain.exceptions;

import java.util.List;

import com.co.mercadolibre.crosscutting.domain.dto.ValidationError;


public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 5986087872673515973L;
	  private final List<ValidationError> errors;

	  public InvalidRequestException(final List<ValidationError> errors) {
	    super("InvalidRequestException");
	    this.errors = errors;
	  }

	  public List<ValidationError> getErrors() {
	    return this.errors;
	  }
}
