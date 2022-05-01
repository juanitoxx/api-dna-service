package com.co.mercadolibre.crosscutting.util;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDUtils {

	public static String randomUUIDWithoutDash() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
