package com.bf.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConferenceFetchReq")
public class ConferenceFetchReq {

	@ApiModelProperty(name="회의실 id", example="2")
	Long id;
	@ApiModelProperty(name="회의실 제목", example="대전_1반수업")
	String title;
	@ApiModelProperty(name="회의실 설명", example="오늘은 간단히 jpa에대해서 배워보기로 해요")
	String description;
	@ApiModelProperty(name="회의 주최자", example="aa")
	String userId;
	@ApiModelProperty(name="thumbnail", example="202201279661680879100young-people-giving-high-five-illustrations-set_23-2148373642.jpg")
	String thumbnail;
	@ApiModelProperty(name="회의실 비밀번호", example="12345")
	String password;
}
