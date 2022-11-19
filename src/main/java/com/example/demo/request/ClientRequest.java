package com.example.demo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
	@NotBlank
	@Size(max=15,message="nom contient au max 15 character")
	@Size(max=4,message="nom contient au max 4 character")
	private String nom;
	@Email(message="email format invalide")
	private String email;
	@Size(min=8,message="password ne doit etre au moins 8 character")
	@Size(min=16,message="password ne doit etre au max 16 character")
	private String password;

}
