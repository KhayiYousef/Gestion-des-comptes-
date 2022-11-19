package com.example.demo.meties;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.dto.VersDto;
import com.example.demo.dto.VirementDto;
import com.example.demo.entities.Comptes;
import com.example.demo.entities.Operation;
import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;

public class LesOperation implements IBanque{
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public VersDto Verser(VersDto versDto) {
		Comptes compte = consultationCompte(versDto.getCode());
		if(compte == null ) throw new RuntimeException("cet Compte pas exict");
		Operation v = new Versement(new Date(),versDto.getMontant(),compte);
		operationRepository.save(v);
		compte.setSolde(versDto.getMontant() + compte.getSolde());
		compteRepository.save(compte);
		return versDto;
		
	}

	@Override
	public VersDto Retirer(VersDto versDto) {
		Comptes compte = consultationCompte(versDto.getCode());
		if(compte == null ) throw new RuntimeException("cet Compte pas exict");
		if(versDto.getMontant()>compte.getSolde()) {
			throw new RuntimeException("solde insufisant");
		}
		Operation r = new Retrait(new Date(),versDto.getMontant(),compte);
		operationRepository.save(r);
		compte.setSolde(compte.getSolde()-versDto.getMontant());
		compteRepository.save(compte);
		return versDto;
		
	}

	@Override
	public VirementDto Virement(VirementDto virementDto) {
		if(virementDto.getCode1().equals(virementDto.getCode2()))
			throw new RuntimeException("pas de virement dans la meme compte");
		VersDto versDto = new VersDto();
		versDto.setCode(virementDto.getCode1());
		versDto.setMontant(virementDto.getMontant());
		VersDto versDto2 = new VersDto();
		versDto2.setCode(virementDto.getCode2());
		versDto2.setMontant(virementDto.getMontant());
		Retirer(versDto);
		Verser(versDto2);
		return virementDto;
		
	}

	@Override
	public Comptes consultationCompte(String code) {
		return compteRepository.findByCode(code);
	}

	@Override
	public Page<Operation> ListOperation(String code, int page,int size) {
		Page<Operation> pages=operationRepository.ListOperation(code, PageRequest.of(page, size));
		return pages;
	}

}
