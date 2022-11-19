package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CE")
@Data
@NoArgsConstructor
public class CompteEpargne extends Comptes {
	private double taux;

	

	public CompteEpargne(String numCompte, Date dateCreation, double solde, Client client, double taux) {
		super(numCompte, dateCreation, solde,null, client);
		this.taux = taux;
	}

	

}
