package com.co.mercadolibre.modules.dna.usecase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.co.mercadolibre.crosscutting.domain.constants.ErrorConstants;
import com.co.mercadolibre.crosscutting.domain.constants.ResponseConstants;
import com.co.mercadolibre.crosscutting.domain.dto.DnaDTO;
import com.co.mercadolibre.crosscutting.domain.dto.ResponseStatus;
import com.co.mercadolibre.crosscutting.domain.enums.ResponseStatusCode;
import com.co.mercadolibre.crosscutting.domain.exceptions.DnaException;
import com.co.mercadolibre.modules.dna.dataprovider.IjpaDnaDataProvider;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DnaService {
	
	@Autowired private IjpaDnaDataProvider dataProvider;

	public ResponseStatus processData(DnaDTO dnaDTO)  {
		String[][] dnaChain = processDna(dnaDTO.getDnaField());
		try {
			dnaDTO = validateMutant(dnaChain, dnaDTO);
			return ResponseStatus.builder().statusCode(ResponseStatusCode.OK)
					.dateTime(LocalDateTime.now())
					.detail(String.valueOf(dnaDTO.isMutant()))
					.status(String.valueOf(HttpStatus.OK.value()))
					.message(ResponseConstants.MESSAGE_RESPONSE).build();

		} catch (Exception e) {
			log.error(ErrorConstants.ERROR_CREATE_SERVICE, e.getMessage());
			throw e; 
		}
	}

	private DnaDTO validateMutant(String[][] dnaChain, DnaDTO dnaDto) {
		var acum = 0;
		for (int i = 0; i < dnaChain.length; i++) {
			for (int j = 0; j < dnaChain[0].length - 3; j++) {
				if (Objects.equals(dnaChain[i][j], dnaChain[i][j + 1])
						&& Objects.equals(dnaChain[i][j], dnaChain[i][j + 2])
						&& Objects.equals(dnaChain[i][j], dnaChain[i][j + 3])) {
					acum++;
				}
			}
		}

		for (int i = 0; i < dnaChain.length; i++) {
			for (int j = 0; j < dnaChain[0].length - 3; j++) {
				if (Objects.equals(dnaChain[j][i], dnaChain[j + 1][i])
						&& Objects.equals(dnaChain[j][i], dnaChain[j + 2][i])
						&& Objects.equals(dnaChain[j][i], dnaChain[j + 3][i])) {
					acum++;
				}
			}
		}

		for (int i = 0; i < dnaChain[0].length - 3; i++) {
			for (int j = 0; j < dnaChain[0].length - 3; j++) {
				if (Objects.equals(dnaChain[i][j], dnaChain[i + 1][j + 1])
						&& Objects.equals(dnaChain[i][j], dnaChain[i + 2][j + 2])
						&& Objects.equals(dnaChain[i][j], dnaChain[i + 3][j + 3])) {
					acum++;
				}
			}
		}

		for (int i = 0; i < dnaChain[0].length - 3; i++) {
			for (int j = 0; j < dnaChain[0].length - 3; j++) {
				if (Objects.equals(dnaChain[j][i], dnaChain[j + 1][i + 1])
						&& Objects.equals(dnaChain[j][i], dnaChain[j + 2][i + 2])
						&& Objects.equals(dnaChain[j][i], dnaChain[j + 3][i + 3])) {
					acum++;
				}
			}
		}

		if (acum < 1) {
			dataProvider.createDnaTransaction(dnaDto);
			throw new DnaException(ResponseConstants.MESSAGE_EXCEPTION);
		}
		dnaDto.setMutant(true);
		return dataProvider.createDnaTransaction(dnaDto);
	}

	private String[][] processDna(List<String> list) {
		String[][] dna = new String[6][6];

		for (int i = 0; i < list.size(); i++) {
			String variable = list.get(i); 
			if (!variable.contains("A") && !variable.contains("C") && !variable.contains("G")
					&& !variable.contains("T")) {
				throw new DnaException(ErrorConstants.ERROR_SEQUENCE);
			}
			for (int j = 0; j < variable.length(); j++) {
				dna[i][j] = variable.substring(j, j + 1);
			}
		}
		return dna;
	}

}
