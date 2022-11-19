package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_Comptes",discriminatorType = DiscriminatorType.STRING, length=2)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Comptes {
	@Id 
	private String numCompte;
	private Date dateCreation;
	private double solde;
	@OneToMany(mappedBy="compte",fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Operation> operation;
	@ManyToOne
	@JsonIgnore
	private Client client;
	
	

}
