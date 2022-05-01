package com.co.mercadolibre.crosscutting.domain.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {

	public static final String ERROR_SEQUENCE= "Error en la secuencia de adn, letras no permitidas!!!";
	public static final String ERROR_CREATE_SEQUENCE = "ERROR INSERTANDO TRANSACCION {}: ";
	public static final String ERROR_QUERY_SEQUENCE = "ERROR EN CONSULTA {}: ";
	public static final String ERROR_CREATE_SERVICE = "ERROR EN SERVICIO DE CREACION: {}";
	public static final String ERROR_QUERY_STATS = "ERROR CONSULTANDO LOS STATS: {}";
}
