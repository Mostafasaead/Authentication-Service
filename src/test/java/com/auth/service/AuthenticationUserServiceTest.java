package com.auth.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.auth.dto.ErrorCodesEnum;
import com.auth.dto.UserInfo;
import com.auth.exceptions.BusinessException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationUserServiceTest {
	@InjectMocks
	private AuthenticateUserServiceImpl authenticateUserService;
	@InjectMocks
	private RegisterUserServiceImpl registerUserService;

	@BeforeClass
	public static void init() {

		MockitoAnnotations.initMocks(AuthenticateUserService.class);
		MockitoAnnotations.initMocks(RegisterUserService.class);

	}

	@Test
	public void When_LoginWithValidCredentials_Expect_SuccessLogin() throws BusinessException {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("Alex");
		userInfo.setPassword("123456");
		userInfo.setMsisdn("+20102146784");
		userInfo.setEmail("Alex@vodafone.com");
		userInfo.setValidationQuestion("what is your favorite color");
		userInfo.setAnswer("green");
		registerUserService.registerNewUser(userInfo);
		UserInfo LoggedInUser = new UserInfo();
		LoggedInUser.setUsername("Alex");
		LoggedInUser.setPassword("123456");

		assertTrue(authenticateUserService.athenticateUser(LoggedInUser));
	}

	@Test
	public void When_LoginWithInValidPassword_Expect_InvalidPasswordException() throws BusinessException  {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("peter");
		userInfo.setPassword("12345678");
		registerUserService.registerNewUser(userInfo);
		UserInfo LoggedInUser = new UserInfo();
		LoggedInUser.setUsername("peter");
		LoggedInUser.setPassword("123456789");
		try {
			authenticateUserService.athenticateUser(LoggedInUser);
		} catch (BusinessException e) {
			assertEquals(ErrorCodesEnum.INVALID_PASSWORD.getErrorCode(), e.getCode());
		}

	}
	@Test
	public void When_LoginWithInValidUserName_Expect_InvalidUserNameException() throws BusinessException  {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("John");
		userInfo.setPassword("12345678");
		registerUserService.registerNewUser(userInfo);
		UserInfo LoggedInUser = new UserInfo();
		LoggedInUser.setUsername("John1");
		LoggedInUser.setPassword("12345678");
		try {
			authenticateUserService.athenticateUser(LoggedInUser);
		} catch (BusinessException e) {
			assertEquals(ErrorCodesEnum.INVALID_USER_NAME.getErrorCode(), e.getCode());
		}

	}
}
