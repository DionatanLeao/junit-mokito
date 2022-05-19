package com.testes.junitmokito.resources;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.testes.junitmokito.domain.User;
import com.testes.junitmokito.dto.UserDTO;
import com.testes.junitmokito.services.impl.UserServiceImpl;

@SpringBootTest
class UserResourceTest {

	private static final int INDEX = 0;
	private static final Integer ID = 1;
	private static final String NAME = "Mokito 1";
	private static final String EMAIL = "mokito1@email.com";
	private static final String PASSWORD = "123";

	@InjectMocks
	private UserResource resource;

	@Mock
	private UserServiceImpl service;

	@Mock
	private ModelMapper mapper;

	private User user;

	private UserDTO userDTO;

	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void findById() {
		when(service.findById(Mockito.anyInt())).thenReturn(user);
		when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(PASSWORD, response.getBody().getPassword());
	}

	@Test
	void findAll() {

	}

	@Test
	void create() {

	}

	@Test
	void update() {

	}

	@Test
	void delete() {

	}


}
