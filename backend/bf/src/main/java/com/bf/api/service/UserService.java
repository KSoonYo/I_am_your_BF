package com.bf.api.service;

import com.bf.api.request.UserInfoFetchReq;
import com.bf.api.request.UserRegisterPostReq;
import com.bf.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserId(String userId);
	
	/** start **/
	boolean DupliecateUserId(String userId);
	void updateUserInfo(String userId,UserInfoFetchReq userInfo);
	void deleteUserInfo(String userId);
	
}
