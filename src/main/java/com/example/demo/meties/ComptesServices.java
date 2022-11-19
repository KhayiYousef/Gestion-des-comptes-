package com.example.demo.meties;

import java.util.List;

import com.example.demo.dto.CompteDto;

public interface ComptesServices {
	public CompteDto getCompte(String id);
	
	public List<CompteDto> AllCompte(int page,int size);
	
	public void delete(String id);
	

	public CompteDto postCompte(CompteDto compteDto);
	
	public CompteDto updateCompte(CompteDto compteDto,String id);
	
	
}
