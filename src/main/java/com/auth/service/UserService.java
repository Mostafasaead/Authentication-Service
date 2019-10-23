package com.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.auth.dto.ErrorCodesEnum;
import com.auth.dto.UserInfo;
import com.auth.exceptions.BusinessException;

abstract class UserService {
	public static List<UserInfo> users = new ArrayList<UserInfo>();

	protected static UserInfo findUserByUserName(String userName) {
		if (users != null) {
			Optional<UserInfo> existingUser = users.stream().filter(f -> f.getUsername().equalsIgnoreCase(userName))
					.findAny();
			if (existingUser.isPresent())
				return existingUser.get();
		}
		return null;

	}

	protected static boolean isAuthenticated(String password, UserInfo existingUser) {
		if (existingUser.getPassword().equals(password))
			return true;
		else
			return false;
	}

	protected static boolean isValidAnswer(String answer, UserInfo existingUser) {
		if (existingUser.getAnswer().equals(answer))
			return true;
		else
			return false;
	}

	protected static void saveUser(UserInfo userInfo) throws BusinessException {
		if (!users.isEmpty()) {
			if (findUserByUserName(userInfo.getUsername()) != null)
				throw new BusinessException(ErrorCodesEnum.USER_ALREADY_EXIST,
						"User is Already exsist, please try to login.", HttpStatus.ALREADY_REPORTED);
		}
		if (userInfo == null || userInfo.getUsername() == null || userInfo.getPassword() == null)
			throw new BusinessException(ErrorCodesEnum.INVALID_USER_MODEL, "You have entered invalid user data",
					HttpStatus.BAD_REQUEST);
		users.add(userInfo);
	}

	public static boolean resetPassword(UserInfo userInfo) {
		for (UserInfo user : users) {
			if (user.getUsername().equalsIgnoreCase(userInfo.getUsername())
					&& user.getAnswer().equalsIgnoreCase(userInfo.getAnswer())) {
				user.setPassword(userInfo.getPassword());
				return true;
			}
		}
		return false;
	}

}
