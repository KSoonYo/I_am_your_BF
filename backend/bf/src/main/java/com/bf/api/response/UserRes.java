package com.bf.api.response;

import com.bf.db.entity.User;
import com.bf.model.response.BaseResponseBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes{
	@ApiModelProperty(name="User ID")
	String userId;
	
	/** 내 프로필 명세 start **/
	@ApiModelProperty(name="User department")
	String department;
	@ApiModelProperty(name="User position")
	String position;
	@ApiModelProperty(name="User name")
	String name;
	/** end **/
	
	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setUserId(user.getUserId());
		
		/** 내 프로필 명세 start **/
		res.setDepartment(user.getDepartment());
		res.setName(user.getName());
		res.setPosition(user.getPosition());
		/** end **/
		
		
		return res;
	}
}
