package com.bf.api.response;

import java.time.LocalDateTime;

import com.bf.model.response.BaseResponseBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConferenceResponse")
public class ConferenceDetailRes{
	
	@ApiModelProperty(name="id")
	Long id;
	@ApiModelProperty(name="title")
	String title;
	@ApiModelProperty(name="description")
	String description;
	@ApiModelProperty(name="thumbnail")
	String thumbnail;
	@ApiModelProperty(name="User id")
	String userId;
	@ApiModelProperty(name="User Name")
	String userName;
	@ApiModelProperty(name="password")
	String password;
	@ApiModelProperty(name="startTime")
	LocalDateTime startTime;
	@ApiModelProperty(name="endTime")
	LocalDateTime endTime;
	

}
