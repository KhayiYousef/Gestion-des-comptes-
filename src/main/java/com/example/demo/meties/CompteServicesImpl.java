package com.example.demo.meties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CompteRepository;
import com.example.demo.dto.CompteDto;
import com.example.demo.dto.Utils;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.Comptes;
@Service
public class CompteServicesImpl implements ComptesServices {

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private Utils utils;
	@Override
	public CompteDto getCompte(String id) {
		CompteDto compteDto = new CompteDto();
		Comptes compte= compteRepository.findByCode(id);
		if(compte == null) throw new RuntimeException("compte pas exist");
		BeanUtils.copyProperties(compte, compteDto);
		return compteDto;
	}

	@Override
	public List<CompteDto> AllCompte(int page,int size) {
		List<Comptes> comptes = new ArrayList<>();
		Page<Comptes> pages = compteRepository.findAll(PageRequest.of(page, size));
		comptes = pages.getContent();
		List<CompteDto> compteDtos = new ArrayList<>();
		for(Comptes compte:comptes)
		{
			CompteDto compteDto = new CompteDto();
			BeanUtils.copyProperties(compte, compteDto);
			compteDtos.add(compteDto);
		}
		return compteDtos;
	}

	@Override
	public void delete(String id) {
		Comptes compte = compteRepository.findByCode(id);
		if(compte == null) throw new RuntimeException("compte pas exist");
		compteRepository.deleteById(id);

	}

	@Override
	public CompteDto postCompte(CompteDto compteDto) {
		Comptes comptes = new CompteCourant();
		BeanUtils.copyProperties(compteDto, comptes);
		comptes.setDateCreation(new Date());
		comptes.setNumCompte(utils.generatedUserId(12));
		Comptes compteCourant = compteRepository.save(comptes);
		BeanUtils.copyProperties(compteCourant, compteDto);
		return compteDto;
	}

	@Override
	public CompteDto updateCompte(CompteDto compteDto, String id) {
		Comptes comptes = compteRepository.findByCode(id);
		comptes.setSolde(compteDto.getSolde());
		BeanUtils.copyProperties(comptes, compteDto);
		compteRepository.save(comptes);
		return compteDto;
	}

}
