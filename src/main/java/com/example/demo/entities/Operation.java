package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_Opera",discriminatorType=DiscriminatorType.STRING,length=1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Operation {
	@Id @GeneratedValue
	private Long numOperation;
	private Date dateOperation;
	private double montant;
	@ManyToOne
	@JsonIgnore
	private Comptes compte;
	

}
