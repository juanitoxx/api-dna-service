package com.co.mercadolibre.crosscutting.domain.exceptions;

public class DnaException extends RuntimeException {
	private static final long serialVersionUID = -6328451219924915108L;
	
	public DnaException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	public DnaException(final String message) {
		super(message);
	}

}
