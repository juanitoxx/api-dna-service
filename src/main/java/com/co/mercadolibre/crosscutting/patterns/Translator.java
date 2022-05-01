package com.co.mercadolibre.crosscutting.patterns;

@FunctionalInterface
public interface Translator<I, O>  {

	O translate(final I input);
}
