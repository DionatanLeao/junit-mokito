package com.testes.junitmokito.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testes.junitmokito.domain.User;
import com.testes.junitmokito.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	
	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User u1 = new User(null, "Mokito 1", "mokito1@email.com", "111111");
		User u2 = new User(null, "Mokito 2", "mokito2@email.com", "222222");
	
		repository.saveAll(List.of(u1, u2));
		
	}
}
