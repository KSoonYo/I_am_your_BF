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
	@ApiModelProperty(name="Host EduClass Name", example="우주의 끝을 찾아서")
	String title;
	@ApiModelProperty(name="Host Name", example="김우주")
	String sender;
	@ApiModelProperty(name="Edu Log", example="안녕하세요 오늘 수업내용은 BF입니다")
	String text;
	
}
