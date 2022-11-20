package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CompteDto;
import com.example.demo.meties.ComptesServices;
import com.example.demo.request.CompteRequest;
import com.example.demo.response.CompteResponse;

@RestController
@RequestMapping("/compte")
public class CompteRestController {
	
	@Autowired
	private ComptesServices comptesServices;
	@GetMapping(path="/{id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public CompteResponse getCompte(@PathVariable String id) {
		CompteDto compteDto = comptesServices.getCompte(id);
		CompteResponse compteResponse = new CompteResponse();
		BeanUtils.copyProperties(compteDto, compteResponse);
		return compteResponse;
	}
	@GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<CompteResponse> findAllCompte(@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size)
	{
		List<CompteDto> compteDtos = comptesServices.AllCompte(page, size);
		List<CompteResponse> compteResponses = new ArrayList<>();
		for(CompteDto compteDto:compteDtos)
		{
			CompteResponse compteResponse = new CompteResponse();
			BeanUtils.copyProperties(compteDto, compteResponse);
			compteResponses.add(compteResponse);
		}
		return compteResponses;
	}
	@PostMapping(produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CompteResponse> addCompte(@Valid @RequestBody CompteRequest compteRequest)
	{
		CompteDto compteDto = new CompteDto();
		CompteResponse compteResponse = new CompteResponse();
		BeanUtils.copyProperties(compteRequest, compteDto);
		CompteDto dto = comptesServices.postCompte(compteDto);
		BeanUtils.copyProperties(dto, compteResponse);
		return new ResponseEntity<CompteResponse>(compteResponse,HttpStatus.CREATED);
	}
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> removeCompte(@PathVariable String id)
	{
		comptesServices.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@PutMapping(path="/{id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			    consumes =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CompteResponse> updateCompte(@RequestBody CompteRequest compteRequest,@PathVariable String id ) {
		CompteDto compteDto = new CompteDto();
		BeanUtils.copyProperties(compteRequest, compteDto);
		CompteDto compte =  comptesServices.updateCompte(compteDto, id);
		CompteResponse compteResponse = new CompteResponse();
		BeanUtils.copyProperties(compte, compteResponse);
		return new ResponseEntity<CompteResponse>(compteResponse,HttpStatus.ACCEPTED);  
	}
	

}
