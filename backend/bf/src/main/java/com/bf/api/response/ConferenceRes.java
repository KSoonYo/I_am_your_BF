package com.bf.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConferenceResponse")
public class ConferenceRes {
	Long id;
	String title;
	String description;
	String thumbnail;
	Long userId;
}
