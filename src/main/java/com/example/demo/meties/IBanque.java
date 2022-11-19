package com.example.demo.meties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.VersDto;
import com.example.demo.dto.VirementDto;
import com.example.demo.entities.Comptes;
import com.example.demo.entities.Operation;

public interface IBanque {
	public VersDto Verser(VersDto versDto);
	public VersDto Retirer(VersDto versDto);
	public VirementDto Virement(VirementDto virementDto);
	public Comptes consultationCompte(String code);
	public Page<Operation> ListOperation(String code,int page,int size);

}
