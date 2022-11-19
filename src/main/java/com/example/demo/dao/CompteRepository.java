package com.example.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Comptes;
public interface CompteRepository extends PagingAndSortingRepository<Comptes,String>{
	@Query("select compte from Comptes compte where compte.numCompte = :x")
	public Comptes findByCode(@Param("x") String code);

}
