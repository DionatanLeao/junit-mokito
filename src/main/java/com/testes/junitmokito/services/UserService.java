package com.testes.junitmokito.services;

import java.util.List;

import com.testes.junitmokito.domain.User;

public interface UserService {
	
	User findById(Integer id);
	
	List<User> findAll();
}
