package com.bf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 정보 수정 API ([POST] /api/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserInfoFetchReq")
public class UserInfoFetchReq {



	@ApiModelProperty(name="유저 Name", example="your_name")
	String userName;
	@ApiModelProperty(name="유저 Email", example="yourEmail@naver.com")
	String userEmail;
	@ApiModelProperty(name="유저 thumbnail", example="202201279661680879100young-people-giving-high-five-illustrations-set_23-2148373642.jpg")
	String thumbnail;
	@ApiModelProperty(name="유저 description", example="나는야 코딩왕")
	String userDescription;


	
}
