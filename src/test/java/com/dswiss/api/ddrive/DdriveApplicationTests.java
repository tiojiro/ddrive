package com.dswiss.api.ddrive;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.dswiss.api.ddrive.controller.FileController;
import com.dswiss.api.ddrive.controller.UserController;

@SpringBootTest
@AutoConfigureMockMvc
class DdriveApplicationTests {

	@Autowired
	private UserController userController;
	
	@Autowired
	private FileController fileController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
		assertThat(fileController).isNotNull();
	}
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Server Ok!")));
	}

}
