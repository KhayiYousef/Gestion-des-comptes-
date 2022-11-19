package com.example.demo.dto;

import java.util.Date;

import lombok.Data;
@Data
public class CompteDto {
	private String numCompte;
	private Date dateCreation;
	private double solde;

}
