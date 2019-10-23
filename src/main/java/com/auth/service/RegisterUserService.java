package com.auth.service;

import com.auth.dto.UserInfo;
import com.auth.exceptions.BusinessException;

public interface RegisterUserService {
	void registerNewUser(UserInfo userInfo) throws BusinessException;
}
