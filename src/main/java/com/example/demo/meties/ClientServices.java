package com.example.demo.meties;

import java.util.List;

import com.example.demo.dto.ClientDto;

public interface ClientServices {
	public ClientDto ajouterClient(ClientDto clientDto);
	
	public ClientDto getClient(Long id);
	
	public ClientDto updateClient(ClientDto clientDto,Long id);
	
	public void daleteClient(Long id);
	
	public List<ClientDto> allClient(int page,int size);

}
