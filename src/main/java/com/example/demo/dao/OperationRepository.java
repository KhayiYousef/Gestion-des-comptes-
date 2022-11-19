package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation,Long>{
	@Query("select operation from Operation operation where operation.compte.numCompte =:x")
	public Page<Operation> ListOperation(@Param("x") String code,Pageable pageable); 

}
