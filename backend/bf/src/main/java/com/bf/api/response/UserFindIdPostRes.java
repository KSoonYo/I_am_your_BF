package com.bf.api.response;

import com.bf.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 아디 찾기 API ([POST] /api/users/find/userId) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserFindIdPostRes")
public class UserFindIdPostRes extends BaseResponseBody{
	@ApiModelProperty(name="userId", example="ssafy_web")
	String userId;
	
	public static UserFindIdPostRes of(Integer statusCode, String message, String userId) {
		UserFindIdPostRes res = new UserFindIdPostRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setUserId(userId);
		return res;
	}
}
