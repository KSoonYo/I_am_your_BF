package com.bf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 정보 수정 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserInfoFetchReq")
public class UserInfoFetchReq {
	
	

	String userName;
	String userEmail;
	String userPhoto;
	String userDescription;

	
}
