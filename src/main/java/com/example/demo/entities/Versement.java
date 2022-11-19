package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("V")
@NoArgsConstructor
public class Versement extends Operation{

	
	public Versement(Date dateOperation, double montant, Comptes compte) {
		super(null,dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

	
}
