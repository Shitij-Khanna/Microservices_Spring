package com.microservice.users;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class UsersApplicationTests {
	
	@Test
	void checkLoginStatus() throws Exception {
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/status").accept(MediaType.TEXT_HTML_VALUE))
//				.andExpect(status().isOk()).andReturn();
//		assertEquals("Service is running !!", mvcResult.getResponse().getContentAsString());
	}

}
