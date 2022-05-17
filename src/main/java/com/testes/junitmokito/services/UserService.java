package com.testes.junitmokito.services;

import java.util.List;

import com.testes.junitmokito.domain.User;
import com.testes.junitmokito.dto.UserDTO;

public interface UserService {
	
	User findById(Integer id);
	
	List<User> findAll();
	
	User create(UserDTO obj);
}
