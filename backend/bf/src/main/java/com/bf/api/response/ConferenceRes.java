package com.bf.api.response;

import com.bf.db.entity.Conference;
import com.bf.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConferenceResponse")
public class ConferenceRes {
	@ApiModelProperty(name="id")
	Long id;
	@ApiModelProperty(name="title")
	String title;
	@ApiModelProperty(name="description")
	String description;
	@ApiModelProperty(name="thumbnail")
	String thumbnail;
	@ApiModelProperty(name="User ID")
	String userId;
	
	
	public static ConferenceRes of(Conference conference) {
		ConferenceRes res = new ConferenceRes();
		res.setId(conference.getId());
		
		return res;
	}
}
