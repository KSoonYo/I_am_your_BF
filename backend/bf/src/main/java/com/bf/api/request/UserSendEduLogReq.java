package com.bf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 수업기록 이메일 전송 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserSendEduLogReq")
public class UserSendEduLogReq {
	@ApiModelProperty(name="수업 기록", example="안녕하세요")
	String text;
	
}
