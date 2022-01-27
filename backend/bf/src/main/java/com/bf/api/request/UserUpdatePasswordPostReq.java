package com.bf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 updatePassword 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserUpdatePasswordPostRequest")
public class UserUpdatePasswordPostReq {
	@ApiModelProperty(name="유저 기존 Password", example="your_password")
	String currentPassword;
	@ApiModelProperty(name="유저 신규 Password", example="new_password")
	String newPassword;
}
