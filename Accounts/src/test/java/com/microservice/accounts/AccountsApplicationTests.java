package com.microservice.accounts;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountsApplicationTests {

	@Test
	void contextLoads() {
		withSuccess();
	}

}
