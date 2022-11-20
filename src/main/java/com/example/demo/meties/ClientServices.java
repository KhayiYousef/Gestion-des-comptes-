package com.example.demo.meties;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.dto.ClientDto;

public interface ClientServices extends UserDetailsService{
	public ClientDto ajouterClient(ClientDto clientDto);
	
	public ClientDto getClient(Long id);
	
	public ClientDto updateClient(ClientDto clientDto,Long id);
	
	public void daleteClient(Long id);
	
	public List<ClientDto> allClient(int page,int size);

}
