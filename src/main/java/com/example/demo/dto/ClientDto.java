package com.example.demo.dto;

import lombok.Data;

@Data
public class ClientDto {
	private String nom;
	private String email;
	private String password;
	private String crypterPassword;
	private String verifieEmail;

}
