package com.example.demo.response;

import java.util.Date;

import lombok.Data;

@Data
public class CompteResponse {
	private String numCompte;
	private Date dateCreation;
	private double solde;

}
