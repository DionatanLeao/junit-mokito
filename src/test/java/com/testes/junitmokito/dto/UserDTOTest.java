package com.testes.junitmokito.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserDTOTest {
	
	private static final String EMAIL = "mokito@email.com";
	private static final String NAME = "Mokito";
	private static final int ID = 1;

	@Test
	void test() {
		UserDTO userDTO = new UserDTO();

		userDTO.setId(ID);
		userDTO.setName(NAME);
		userDTO.setEmail(EMAIL);

		assertNotNull(userDTO);

		assertEquals(ID, userDTO.getId());
		assertEquals(NAME, userDTO.getName());
		assertEquals(EMAIL, userDTO.getEmail());
		
	}

}
