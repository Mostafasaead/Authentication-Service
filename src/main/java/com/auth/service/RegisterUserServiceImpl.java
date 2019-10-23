package com.auth.service;

import org.springframework.stereotype.Service;

import com.auth.dto.UserInfo;
import com.auth.exceptions.BusinessException;

@Service
public class RegisterUserServiceImpl extends UserService implements RegisterUserService {

	@Override
	public void registerNewUser(UserInfo userInfo) throws BusinessException {
		UserService.saveUser(userInfo);
	}

}
