package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Client;

public interface ClientRepository extends PagingAndSortingRepository<Client,Long> {
	@Query("select client from Client client where client.id = :x")
	public Client ClientParId(@Param("x")Long id);
	@Query("select client from Client client")
	public List<Client> allClient();
	public Client findByEmail(String email);

}
