package com.testes.junitmokito.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testes.junitmokito.domain.User;
import com.testes.junitmokito.repositories.UserRepository;
import com.testes.junitmokito.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElse(null);
	}

}
