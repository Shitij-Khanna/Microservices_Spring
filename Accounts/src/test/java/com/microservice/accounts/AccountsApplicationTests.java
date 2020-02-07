package com.microservice.accounts;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservice.accounts.data.AlbumEntity;
import com.microservice.accounts.service.AlbumsService;
import com.microservice.accounts.ui.controllers.AccountsController;

@SpringBootTest
class AccountsApplicationTests {

	@InjectMocks
	AccountsController employeeController;

	@Mock 
	private AlbumsService helloService;

	 @BeforeEach
	    void setMockOutput() {
		 List<AlbumEntity> returnValue = new ArrayList<>();
	        when(helloService.getAlbums("1")).thenReturn(returnValue);
	    }
	
	@Test
	void contextLoads() {
		withSuccess();
	}

}
