package com.auth.service;

import static org.junit.Assert.assertEquals;

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
public class RegisterUserServiceTest {
	@InjectMocks
	private RegisterUserServiceImpl registerUserService;

	@BeforeClass
	public static void init() {

		MockitoAnnotations.initMocks(RegisterUserService.class);
	}

	@Test
	public void When_ValidUserModel_Expect_SuccessRegisteration() throws BusinessException {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("Alex");
		userInfo.setPassword("123456");
		userInfo.setMsisdn("+20102146784");
		userInfo.setEmail("Alex@vodafone.com");
		userInfo.setValidationQuestion("what is your favorite color");
		userInfo.setAnswer("green");
		registerUserService.registerNewUser(userInfo);
		assertEquals(UserService.users.size(), 1);
	}

	@Test
	public void When_InvalidValidModel_Expect_BusinessException() throws BusinessException {
		UserInfo userInfo = new UserInfo();
		userInfo.setMsisdn("+20102146784");
		userInfo.setEmail("Alex@vodafone.com");
		userInfo.setValidationQuestion("what is your favorite color?");
		userInfo.setAnswer("green");
		try {
			registerUserService.registerNewUser(userInfo);
		} catch (BusinessException e) {
			assertEquals(ErrorCodesEnum.INVALID_USER_MODEL.getErrorCode(), e.getCode());
		}
	}

	@Test
	public void When_UserAlreadyExsit_Expect_BusinessException() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("Alex");
		userInfo.setPassword("123456");
		userInfo.setMsisdn("+20102146784");
		userInfo.setEmail("Alex@vodafone.com");
		userInfo.setValidationQuestion("what is your favorite color");
		userInfo.setAnswer("green");
		try {
			registerUserService.registerNewUser(userInfo);

			registerUserService.registerNewUser(userInfo);
		} catch (BusinessException e) {
			assertEquals(ErrorCodesEnum.USER_ALREADY_EXIST.getErrorCode(), e.getCode());
		}
	}

}
