package com.bf.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bf.model.response.BaseResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;

@Api(value = "이미지 api", tags = {"photo"})
@RestController
@RequestMapping("/api/image")
public class PhotoController {
	
	
	@GetMapping(
			value="/{thumbnail}",
			  produces = MediaType.IMAGE_JPEG_VALUE
			)
	public byte[] getImageWithMediaType(@PathVariable String thumbnail) throws IOException {
		String absolutePath = new File("").getAbsolutePath() + "/images/";
  System.out.println("getImageWithMediaType absolutePath :" + absolutePath);
		InputStream in = new FileInputStream(absolutePath+thumbnail);
		byte[] imageByteArray = IOUtils.toByteArray(in);
		return imageByteArray;
		}
	
	
	
	@PostMapping("/upload")
	public ResponseEntity<Map<String,Object>> handleFileUpload(@RequestPart(value = "file") final MultipartFile uploadfile) throws IOException {
		String thumbnail=saveUploadedFiles(uploadfile);
		Map<String,Object> temp = new HashMap<>();
		temp.put("thumbnail",thumbnail );
		
		return ResponseEntity.status(200).body(temp);
		
	}

	private String saveUploadedFiles(final MultipartFile thumbnail) throws IOException {
		    String absolutePath = new File("").getAbsolutePath() + "/images/";
      System.out.println("saveUploadedFiles absolutePath :" + absolutePath);
        File file = new File("images");
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if(!file.exists()){
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());
//		current_date+Long.toString(System.nanoTime()) + originalFileExtension;
		final byte[] bytes = thumbnail.getBytes();
		String newFileName=current_date+Long.toString(System.nanoTime()) +thumbnail.getOriginalFilename();
		final Path path = Paths.get(absolutePath + newFileName);
		Files.write(path, bytes);
		
		return newFileName;
	}

}
