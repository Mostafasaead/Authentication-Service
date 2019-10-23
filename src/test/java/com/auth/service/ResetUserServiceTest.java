package com.auth.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
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
class ResetUserServiceTest {
	@InjectMocks
	private ResetUserServiceImpl resetUserService;
	@InjectMocks
	private RegisterUserServiceImpl registerUserService;

	@BeforeClass
	public static void init() {

		MockitoAnnotations.initMocks(ResetUserService.class);
		MockitoAnnotations.initMocks(RegisterUserService.class);

	}

	@Test
	public void When_ResetPasswordWithValidAnswer_Expect_SuccessReset() throws BusinessException {
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
		LoggedInUser.setPassword("12345678");
		LoggedInUser.setAnswer("green");

		assertTrue(resetUserService.resetUser(LoggedInUser));
	}

	@Test
	public void When_ResetPasswordWithInValidAnswer_Expect_FailureReset() throws BusinessException {
		UserInfo LoggedInUser = new UserInfo();
		LoggedInUser.setUsername("Alex");
		LoggedInUser.setPassword("12345678");
		LoggedInUser.setAnswer("yellow");

		assertFalse(resetUserService.resetUser(LoggedInUser));
	}
}
