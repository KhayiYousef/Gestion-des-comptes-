package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;

import com.example.demo.entities.Client;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.CompteEpargne;
import com.example.demo.entities.Comptes;
import com.example.demo.entities.Operation;
import com.example.demo.entities.Retrait;

import com.example.demo.entities.Versement;


@SpringBootApplication
public class TpJpaHibernateApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		 SpringApplication.run(TpJpaHibernateApplication.class, args);
		
		 
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		
//	List<Client> clients = clientRepository.findAll();
//	for(Client client:clients) {
//		System.out.println(client);
//	}
//		Client c1 = clientRepository.save(new Client(null,"Yousef","yousef@gmail.com"));
//		Client c2 = clientRepository.save(new Client("aziz","aziz@gmail.com"));
//		Client c3 = clientRepository.save(new Client("mjid","mjid@gmail.com"));
//		Client c4 = clientRepository.save(new Client("abdo","abdo@gmail.com"));
//		CompteCourant compte1 = compteRepository.save(new CompteCourant("a1",dateFormat.parse("19/02/2014"),1000,c1,12));
//		CompteCourant compte2 = compteRepository.save(new CompteCourant("a2",dateFormat.parse("19/10/2012"),2000,c2,120));
//		CompteCourant compte3 = compteRepository.save(new CompteCourant("a3",dateFormat.parse("16/12/2010"),3000,c3,220));
//		CompteEpargne compte4 = compteRepository.save(new CompteEpargne("a4",dateFormat.parse("29/01/2016"),5000,c4,228));
//		Retrait op1 = operationRepository.save(new Retrait(dateFormat.parse("19/01/2020"),1000,compte1));
//		Retrait op2 = operationRepository.save(new Retrait(dateFormat.parse("19/01/2019"),1500,compte2));
//		Versement op3 = operationRepository.save(new Versement(dateFormat.parse("19/01/2019"),1500,compte3));
//		Versement op4 = operationRepository.save(new Versement(dateFormat.parse("19/01/2019"),2000,compte4));
//		
}

}
