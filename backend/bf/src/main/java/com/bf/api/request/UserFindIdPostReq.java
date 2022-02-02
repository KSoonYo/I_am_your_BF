package com.bf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 아이디 찾기 API ([POST] /api/users/find/userId) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserFindIdPostRequest")
public class UserFindIdPostReq {
	@ApiModelProperty(name="유저 Name", example="your_name")
	String userName;
	@ApiModelProperty(name="유저 Email", example="yourEmail@naver.com")
	String userEmail;
}
