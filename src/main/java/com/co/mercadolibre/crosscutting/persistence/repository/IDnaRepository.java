package com.co.mercadolibre.crosscutting.persistence.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.co.mercadolibre.crosscutting.persistence.entity.Dna;

@EnableScan
@Repository
public interface IDnaRepository extends CrudRepository<Dna, String> {

	Dna findByDnaChain(List<String> dnaChain);
	
}
