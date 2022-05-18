package com.testes.junitmokito.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.testes.junitmokito.domain.User;
import com.testes.junitmokito.dto.UserDTO;
import com.testes.junitmokito.repositories.UserRepository;
import com.testes.junitmokito.services.exceptions.DataIntegratyViolationException;
import com.testes.junitmokito.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {

	private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
	private static final int INDEX = 0;
	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	private static final Integer ID = 1;
	private static final String NAME = "Mokito 1";
	private static final String EMAIL = "mokito1@email.com";
	private static final String PASSWORD = "123";

	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;
	
	private UserDTO userDTO;

	private Optional<User> optionalUser;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD); 
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD); 
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}
	
	@Test
	void findById() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		
		User response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
	}

	@Test
	void findByIdObjectNotFoundException() {
		when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		
		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
		}

	}
	
	@Test
	void findAll() {
		when(repository.findAll()).thenReturn(List.of(user));
		
		List<User> response = service.findAll();
		
		 assertNotNull(response);
		 assertEquals(ID, response.size());
		 assertEquals(User.class, response.get(INDEX).getClass());
		 
		 assertEquals(ID, response.get(INDEX).getId());
		 assertEquals(NAME, response.get(INDEX).getName());
		 assertEquals(EMAIL, response.get(INDEX).getEmail());
		 assertEquals(PASSWORD, response.get(INDEX).getPassword());
	}
	
	@Test
	void create() {
		when(repository.save(Mockito.any())).thenReturn(user);
		
		User response = service.create(userDTO);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
		
	}
	
	
	@Test
	void createDataIntegrityViolationException() {
		when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2);
			service.create(userDTO);
		} catch (Exception e) {
			assertEquals(DataIntegratyViolationException.class, e.getClass());
			assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, e.getMessage());
			
		}
		
	}
	
	@Test
	void update() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		when(repository.save(Mockito.any())).thenReturn(user);
		
		User response = service.update(userDTO);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
		
	}

	@Test
	void updateDataIntegrityViolationException() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2);
			service.update(userDTO);
		} catch (Exception e) {
			assertEquals(DataIntegratyViolationException.class, e.getClass());
			assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, e.getMessage());
			
		}
		
	}
	
	@Test
	void delete() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		doNothing().when(repository).deleteById(Mockito.anyInt());
		
		service.delete(ID);
		
		verify(repository, times(1)).deleteById(Mockito.anyInt());
		
	}
	
	@Test
	void deleteObjectNotFoundException() {
		when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		
		try {
			service.delete(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
		}
		
	}
}
