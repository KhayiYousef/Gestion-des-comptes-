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

import com.example.demo.dto.ClientDto;
import com.example.demo.meties.ClientServices;
import com.example.demo.request.ClientRequest;
import com.example.demo.response.ClientResponse;

@RestController
@RequestMapping("/client")
public class ClientRestController {
	

	@Autowired
	private ClientServices clientServices;
	@GetMapping(path="/{id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {
		ClientResponse clientResponse = new ClientResponse();
		ClientDto clientDto = clientServices.getClient(id);
		BeanUtils.copyProperties(clientDto, clientResponse);
		return new ResponseEntity<ClientResponse>(clientResponse,HttpStatus.OK);
	}
	
	@GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<ClientResponse> getClients(@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="2") int size){
		List<ClientDto> clientDtos = clientServices.allClient(page, size);
		List<ClientResponse> clientResponses = new ArrayList<>();
		for(ClientDto client:clientDtos)
		{
			ClientResponse clientResponse = new ClientResponse();
			BeanUtils.copyProperties(client, clientResponse);
			clientResponses.add(clientResponse);
		}
		return clientResponses;
	}
	
	@PostMapping(produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ClientResponse> addClient(@Valid @RequestBody ClientRequest clientRequest) 
	{
		ClientDto clientDto = new ClientDto();
		BeanUtils.copyProperties(clientRequest, clientDto);
		ClientDto client = clientServices.ajouterClient(clientDto);
		ClientResponse clientResponse = new ClientResponse();
		BeanUtils.copyProperties(client, clientResponse);
		return new ResponseEntity<ClientResponse>(clientResponse,HttpStatus.CREATED);
	}
	@PutMapping(path="/{id}",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ClientResponse> updateClient(@RequestBody ClientRequest clientRequest,@PathVariable Long id)
	{
		ClientDto clientDto = new ClientDto();
		BeanUtils.copyProperties(clientRequest, clientDto);
		ClientDto client = clientServices.updateClient(clientDto,id);
		ClientResponse clientResponse = new ClientResponse();
		BeanUtils.copyProperties(client, clientResponse);
		return new ResponseEntity<ClientResponse>(clientResponse,HttpStatus.ACCEPTED);
	}
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable Long id)
	{
		clientServices.daleteClient(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
