package com.bf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회의실 생성 API ([POST] /api/conferences) 요청에 필요한 리퀘스트 바디 정의.
 */

@Getter
@Setter
@ApiModel("ConferenceRegisterPostRequest")
//로그인 권한 필요
public class ConferenceRegisterPostReq {
	
	@ApiModelProperty(name="회의실 제목", example="대전_1반수업")
	String title;
	@ApiModelProperty(name="회의실 설명", example="오늘은 간단히 jpa에대해서 배워보기로 해요")
	String description;
	@ApiModelProperty(name="회의 주최자", example="aa")
	String userId;
	@ApiModelProperty(name="thumbnail", example="이미지")
	String formData;
	@ApiModelProperty(name="회의실 비밀번호", example="12345")
	String password;
	@ApiModelProperty(name="회의실 상태", example="true")
	Boolean isActive;
	
	
}
