package com.auth.service;

import com.auth.dto.UserInfo;
import com.auth.exceptions.BusinessException;

public interface ResetUserService {

	boolean resetUser(UserInfo userInfo) throws BusinessException;

}
