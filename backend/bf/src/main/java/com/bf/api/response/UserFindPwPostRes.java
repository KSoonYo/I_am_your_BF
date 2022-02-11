package com.bf.api.response;

import com.bf.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 비밀번호 찾기 API ([POST] /api/users/find/password) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserFindIdPostRes")
public class UserFindPwPostRes extends BaseResponseBody{
	@ApiModelProperty(name="userId", example="ssafy_web")
	String userId;
	
	public static UserFindPwPostRes of(Integer statusCode, String message, String userId) {
		UserFindPwPostRes res = new UserFindPwPostRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUserId(userId);
		return res;
	}
}
