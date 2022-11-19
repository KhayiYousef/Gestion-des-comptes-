package com.example.demo.meties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dto.ClientDto;
import com.example.demo.entities.Client;

@Service
public class ClientServicesImpl implements ClientServices {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ClientDto ajouterClient(ClientDto clientDto) {
		Client client = new Client();
		
		BeanUtils.copyProperties(clientDto, client);
		client.setEncryptedPassword(bCryptPasswordEncoder.encode(clientDto.getPassword()));
		if(clientRepository.findByEmail(client.getEmail()) != null) throw new RuntimeException("email deja exist");
		Client clientCree = clientRepository.save(client);
		BeanUtils.copyProperties(clientCree, clientDto);
		return clientDto;
	}

	@Override
	public ClientDto getClient(Long id) {
		Client client = clientRepository.findById(id).get();
		if(client == null ) throw new RuntimeException("cet client pas exist");
		ClientDto clientDto = new ClientDto();
		BeanUtils.copyProperties(client, clientDto);
		return clientDto;
	}

	@Override
	public ClientDto updateClient(ClientDto clientDto, Long id) {
		Client client = clientRepository.findById(id).get();
		if(client == null ) throw new RuntimeException("cet client pas exist");
		client.setNom(clientDto.getNom());
		clientRepository.save(client);
		//ClientDto clientDto2 = new ClientDto();
		BeanUtils.copyProperties(client, clientDto);
		return clientDto;
	}

	@Override
	public void daleteClient(Long id) {
		Client client = clientRepository.findById(id).get();
		if(client == null ) throw new RuntimeException("cet client pas exist");
		clientRepository.deleteById(id);

	}

	@Override
	public List<ClientDto> allClient(int page,int size) {
		List<ClientDto> clientDtos = new ArrayList<>();
		Page<Client> pageClient = clientRepository.findAll(PageRequest.of(page, size));
		List<Client> clients = pageClient.getContent();
		for(Client client:clients)
		{
			ClientDto clientDto = new ClientDto();
			BeanUtils.copyProperties(client, clientDto);
			clientDtos.add(clientDto);
		}
		return clientDtos;
	}

}
