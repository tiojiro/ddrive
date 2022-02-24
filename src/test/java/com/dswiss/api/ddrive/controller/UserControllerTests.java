package com.dswiss.api.ddrive.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dswiss.api.ddrive.service.UserService;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	@Qualifier("userServiceTestImpl")
	private UserService userService;
	
	private String strUser = "{\"userName\":\"tiojiro\",\"name\":\"Renato Zukeran\",\"email\":\"renato@zukeran.com.br\",\"birth\":\"1983-07-14\"}";

	@Test
	public void saveUser() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/ddrive/user")
				.accept(MediaType.APPLICATION_JSON).content(strUser)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
	}

}
