package com.example.demo.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VersDto;
import com.example.demo.meties.IBanque;
import com.example.demo.request.VersRequest;
import com.example.demo.response.VersResponse;

@RestController
public class OperationRestController {

	@Autowired
	private IBanque banque;
	
	@PostMapping(produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			     consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public VersResponse vers(@RequestBody VersRequest request) 
	{
		VersDto versDto = new VersDto();
		BeanUtils.copyProperties(request, versDto);
		VersDto dto = banque.Verser(versDto);
		VersResponse response = new VersResponse();
		BeanUtils.copyProperties(dto, response);
		return response;
	}
	
	@PostMapping(produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
		     consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public VersResponse Retirer(@RequestBody VersRequest request) 
	{
		VersDto versDto = new VersDto();
		BeanUtils.copyProperties(request, versDto);
		VersDto dto = banque.Retirer(versDto);
		VersResponse response = new VersResponse();
		BeanUtils.copyProperties(dto, response);
		return response;
	}
}
