package com.auth.service;

import org.springframework.stereotype.Service;

import com.auth.dto.UserInfo;

@Service
public class ResetUserServiceImpl extends UserService implements ResetUserService {

	@Override
	public boolean resetUser(UserInfo userInfo) {
		return UserService.resetPassword(userInfo);
	}

}
