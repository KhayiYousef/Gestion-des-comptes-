package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("R")
@NoArgsConstructor
public class Retrait extends Operation{

	
	public Retrait(Date dateOperation, double montant, Comptes compte) {
		super(null,dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

	
}
