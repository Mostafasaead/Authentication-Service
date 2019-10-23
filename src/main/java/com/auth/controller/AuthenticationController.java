package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.ErrorCodesEnum;
import com.auth.dto.UserInfo;
import com.auth.exceptions.BusinessException;
import com.auth.service.AuthenticateUserService;
import com.auth.service.RegisterUserService;
import com.auth.service.ResetUserService;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {
	@Autowired
	private RegisterUserService registerUserService;
	@Autowired
	private AuthenticateUserService authenticateUserService;
	@Autowired
	private ResetUserService resetUserService;

	@PutMapping(value = "/register")
	public ResponseEntity<UserInfo> registerNewUser(@RequestBody UserInfo userInfo) throws BusinessException {
		registerUserService.registerNewUser(userInfo);
		return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
	}

	@ExceptionHandler(BusinessException.class)
	@PostMapping(value = "/authenticate")
	public ResponseEntity<HttpStatus> authenticateUser(@RequestBody UserInfo userInfo) throws BusinessException {
		if (authenticateUserService.athenticateUser(userInfo))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PatchMapping(value = "/reset")
	public ResponseEntity<UserInfo> resetUser(@RequestBody UserInfo userInfo) throws BusinessException {
		if (resetUserService.resetUser(userInfo))
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		else
			throw new BusinessException(ErrorCodesEnum.INVALID_ANSWER, "Your answer is not matched, please try again!",
					HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { BusinessException.class })
	public ResponseEntity<BusinessException> rulesForBusinessException(BusinessException e) {
		return new ResponseEntity<BusinessException>(e, e.getHttpStatus());
	}
}
