package com.lemeng.lecloud.utils.cache;

import com.lemeng.lecloud.model.user.UserLogin;

public class LoginCacheHelper {

	private static ThreadLocal<UserLogin> threadLocal = new ThreadLocal<UserLogin>();

	public static void setCurrentUserLogin(UserLogin userLogin) {
		threadLocal.set(userLogin);
	}

	public static UserLogin getCurrentUserLogin() {
		return threadLocal.get();
	}

}
