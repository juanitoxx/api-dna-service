package com.co.mercadolibre.crosscutting.util;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.co.mercadolibre.crosscutting.domain.dto.ValidationError;

@RunWith(MockitoJUnitRunner.class)
public class ResponseEntityUtilTest {
	
	@InjectMocks private ResponseEntityUtil entityUtil;
	private ValidationError error;
	private List<ValidationError>errors;
	
	@Before
	public void processInformation() {
		errors = new ArrayList<>();
		error = ValidationError.builder().code("NotNull").field("dnaField").defaultMessage("no debe ser nulo").build();
		errors.add(error);
	}
	
	@Test
	public void createResponseValidationError() {
		assertNotNull(entityUtil.createResponseValidationError(errors));
	}
}
