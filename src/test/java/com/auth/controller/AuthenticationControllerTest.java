package com.auth.controller;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.auth.dto.UserInfo;
import com.auth.service.AuthenticateUserService;
import com.auth.service.RegisterUserService;
import com.auth.service.ResetUserService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
public class AuthenticationControllerTest {
	@InjectMocks
	private AuthenticationController authenticationController;
	@Mock
	private RegisterUserService registerUserService;
	@Mock
	private AuthenticateUserService authenticateUserService;
	@Mock
	private ResetUserService resetUserService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
	}

	@Test
	public void testRegisterAPI() throws Exception {
		Gson gson = new Gson();
		UserInfo request = new UserInfo();
		request.setUsername("Alex");
		request.setPassword("123456");
		request.setMsisdn("+20102146784");
		request.setEmail("Alex@vodafone.com");
		request.setValidationQuestion("what is your favorite color");
		request.setAnswer("green");

		doNothing().when(registerUserService).registerNewUser(anyObject());
		mockMvc.perform(put("/authentication/register").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(request)).accept(MediaType.APPLICATION_JSON).header("header", ""))
				.andExpect(status().isOk());
	}

	@Test
	public void testAuthenticationAPI() throws Exception {
		Gson gson = new Gson();
		UserInfo request = new UserInfo();
		request.setUsername("Alex");
		request.setPassword("123456");
		doReturn(true).when(authenticateUserService).athenticateUser(anyObject());
		mockMvc.perform(post("/authentication/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(request)).accept(MediaType.APPLICATION_JSON).header("header", ""))
				.andExpect(status().isOk());
	}

	@Test
	public void testResetAPI() throws Exception {
		Gson gson = new Gson();
		UserInfo request = new UserInfo();
		request.setUsername("Alex");
		request.setPassword("12345678");
		request.setEmail("Alex@vodafone.com");
		request.setValidationQuestion("what is your favorite color");
		request.setAnswer("green");

		doReturn(true).when(resetUserService).resetUser(anyObject());
		mockMvc.perform(patch("/authentication/reset").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(request)).accept(MediaType.APPLICATION_JSON).header("header", ""))
				.andExpect(status().isOk());
	}
}
