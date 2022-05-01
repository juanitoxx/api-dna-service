package com.co.mercadolibre.crosscutting.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "dna_persons")
public class Dna implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @DynamoDBHashKey(attributeName = "transactionId")
    private String transactionId;

    @DynamoDBTyped(DynamoDBAttributeType.L)
    @DynamoDBAttribute(attributeName = "dna_chain")
    private List<String> dnaChain;
    
    @DynamoDBTyped(DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "mutant")
    private boolean mutant;
    
    @DynamoDBTyped(DynamoDBAttributeType.S)
    @DynamoDBAttribute(attributeName = "creation_date")
    private Date creationDate;
}
