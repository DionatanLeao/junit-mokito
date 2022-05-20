package com.testes.junitmokito.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

	private static final String PASSWORD = "123";
	private static final String EMAIL = "mokito@email.com";
	private static final String NAME = "Mokito";
	private static final int ID = 1;

	@Test
	void test() {
		User user = new User();
		
		user.setId(ID);
		user.setName(NAME);
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		
		assertNotNull(user);
		
		assertEquals(ID, user.getId());
		assertEquals(NAME, user.getName());
		assertEquals(EMAIL, user.getEmail());
		assertEquals(PASSWORD, user.getPassword());
	}

}
