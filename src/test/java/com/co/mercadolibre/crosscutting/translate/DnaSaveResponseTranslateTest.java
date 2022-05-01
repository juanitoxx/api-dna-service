package com.co.mercadolibre.crosscutting.translate;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.co.mercadolibre.crosscutting.persistence.entity.Dna;

@RunWith(MockitoJUnitRunner.class)
public class DnaSaveResponseTranslateTest {

	@InjectMocks private DnaSaveResponseTranslate dnaSaveResponseTranslate;
	private Dna dna;
	
	@Before
	public void processInformation() {
		dna = Dna.builder().mutant(true).dnaChain(new ArrayList<>()).creationDate(new Date()).build();
	}
	
	@Test
	public void translateTest() {
		assertNotNull(dnaSaveResponseTranslate.translate(dna));
	}
}
