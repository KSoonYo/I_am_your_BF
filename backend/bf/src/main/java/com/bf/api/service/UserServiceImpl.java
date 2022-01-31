package com.bf.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bf.api.request.UserInfoFetchReq;
import com.bf.api.request.UserRegisterPostReq;
import com.bf.db.entity.User;
import com.bf.db.repository.UserRepository;
import com.bf.db.repository.UserRepositorySupport;
import java.time.LocalDateTime;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
    UserRepositorySupport userRepositorySupport;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * 새로운 user 생성
	 * @param userRegisterInfo
	 * @return
	 */
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();

		user.setUserId(userRegisterInfo.getUserId());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setUserName(userRegisterInfo.getUserName());
		user.setUserEmail(userRegisterInfo.getUserEmail());

		// 로그 관리
		LocalDateTime currentDateTime = LocalDateTime.now();
		user.setCreateDate(currentDateTime);
		user.setAssign("mo_ah");

		return userRepository.save(user);
	}

	/**
	 * 디비의 유저 정보 조회 (userId 를 통한 조회).
	 * @param userId
	 * @return
	 */
	@Override
	public User getUserByUserId(String userId) {
		User user = userRepositorySupport.findUserByUserId(userId).get();
		return user;
	}

	/**
	 * userID 중복 체크
	 * @param userId
	 * @return
	 */
	@Override
	public boolean chkDplByUserId(String userId) {
		if(userRepositorySupport.findUserByUserId(userId).isPresent()) // 유저 정보가 존재하면
			return true;
		else return false;
	}

	/**
	 * userEmail 중복 체크
	 * @param userEmail
	 * @return
	 */
	@Override
	public boolean chkDplByUserEmail(String userEmail) {
		if(userRepositorySupport.findUserByUserEmail(userEmail).isPresent()) // 유저 정보가 존재하면
			return true;
		else return false;
	}

	/**
	 * userPassword 변경
	 * @param userId
	 * @param password
	 */
	@Override
	public void updatePassword(String userId,String password){
		User updateUser = userRepositorySupport.findUserByUserId(userId).get();
//		updateUser.setPassword(passwordEncoder.encode(password));
		updateUser.setPassword(passwordEncoder.encode(password));
		userRepository.save(updateUser);
	}

	/**
	 * user 정보 업데이트 (Password제외)
	 * @param userId
	 * @param userInfo
	 */
	@Override
	public void updateUser(String userId,UserInfoFetchReq userInfo) {
		User updateUser = userRepositorySupport.findUserByUserId(userId).get();

		// if(updateUser == null) throw시겨주기 ! 공부필요
		updateUser.setUserName(userInfo.getUserName());
		updateUser.setUserEmail(userInfo.getUserEmail());
		updateUser.setThumbnail(userInfo.getThumbnail());
		updateUser.setDescription(userInfo.getUserDescription());

		userRepository.save(updateUser);
	}

	/**
	 * user 탈퇴
	 * @param userId
	 */
	@Override
	public void deleteUser(String userId) {
		User deleteUser = userRepositorySupport.findUserByUserId(userId).get();
		userRepository.deleteById(deleteUser.getId());
	}


}
