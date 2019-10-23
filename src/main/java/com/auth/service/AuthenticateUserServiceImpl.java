package com.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.auth.dto.ErrorCodesEnum;
import com.auth.dto.UserInfo;
import com.auth.exceptions.BusinessException;

@Service
public class AuthenticateUserServiceImpl extends UserService implements AuthenticateUserService {

	@Override
	public boolean athenticateUser(UserInfo userInfo) throws BusinessException {
		UserInfo existingUser = UserService.findUserByUserName(userInfo.getUsername());
		if (existingUser != null) {

			if (isAuthenticated(userInfo.getPassword(), existingUser)) {
				existingUser.setWrongTrials(0);
				return true;
			} else {
				if (existingUser.getWrongTrials() < 5)
					existingUser.setWrongTrials(existingUser.getWrongTrials() + 1);
				else {
					throw new BusinessException(ErrorCodesEnum.INVALID_PASSWORD,
							"Invalid Password!, you have reached the maximum numbers of trails, please reset your account",
							HttpStatus.UNAUTHORIZED);
				}
				throw new BusinessException(ErrorCodesEnum.INVALID_PASSWORD,
						"Invalid Password!, you have been entered wrong password " + existingUser.getWrongTrials()
								+ "out of 5",
						HttpStatus.UNAUTHORIZED);
			}
		}
		throw new BusinessException(ErrorCodesEnum.INVALID_USER_NAME, "This user is not exist!",
				HttpStatus.UNAUTHORIZED);
	}

}
