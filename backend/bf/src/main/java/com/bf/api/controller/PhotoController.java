package com.bf.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "이미지 api", tags = {"photo"})
@RestController
@RequestMapping("/api/image")
public class PhotoController {
	
	
	@GetMapping(
			value="/{thumbnail}",
			  produces = MediaType.IMAGE_JPEG_VALUE
			)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String thumbnail) throws IOException {
		String absolutePath = new File("").getAbsolutePath() + "\\images\\";
		InputStream in = new FileInputStream(absolutePath+thumbnail);
		byte[] imageByteArray = IOUtils.toByteArray(in);
		return imageByteArray;
		}

}
