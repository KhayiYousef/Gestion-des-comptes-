package com.example.demo.request;



import javax.validation.constraints.Positive;

import lombok.Data;
@Data
public class CompteRequest {
	
	@Positive
	private double solde;
	

}
