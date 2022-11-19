package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Client implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7548925615586567188L;
	@Id @GeneratedValue
	private Long id;
	private String nom;
	private String email;
	private String encryptedPassword;
	@OneToMany(mappedBy="client",fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<Comptes> comptes;
	
}
