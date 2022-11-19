package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CC")
@Data
@NoArgsConstructor
public class CompteCourant extends Comptes{
	private double decouvert;

	

	public CompteCourant(String numCompte, Date dateCreation, double solde, Client client, double decouvert) {
		super(numCompte, dateCreation, solde,null, client);
		this.decouvert = decouvert;
	}
	

}
