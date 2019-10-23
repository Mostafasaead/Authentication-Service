package com.auth.service;

import com.auth.dto.UserInfo;
import com.auth.exceptions.BusinessException;

public interface AuthenticateUserService {
	public boolean athenticateUser(UserInfo userInfo) throws BusinessException;
}
