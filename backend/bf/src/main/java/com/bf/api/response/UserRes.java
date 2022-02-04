package com.bf.api.response;

import com.bf.db.entity.User;
import com.bf.model.response.BaseResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes{
	@ApiModelProperty(name="ssafy_web")
	String userId;
	@ApiModelProperty(name="userName")
	String userName;
	@ApiModelProperty(name="userEmail")
	String userEmail;
	@ApiModelProperty(name="description")
	String description;
	@ApiModelProperty(name="thumbnail")
	String thumbnail;

	

	
	public static UserRes of(User user) {
		UserRes res = new UserRes();
		res.setUserId(user.getUserId());
		res.setUserEmail(user.getUserEmail());
		res.setUserName(user.getUserName());
		res.setDescription(user.getDescription());
		res.setThumbnail(user.getThumbnail());
		return res;
	}
}
