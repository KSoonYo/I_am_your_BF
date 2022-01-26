package com.bf.api.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.bf.api.request.ConferenceRegisterPostReq;
import com.bf.api.response.ConferenceRes;
import com.bf.db.entity.Conference;


public interface ConferenceService {
	Conference createConference(Conference conference,MultipartFile thumbnail)throws URISyntaxException,Exception;
	List<ConferenceRes> getAllConference() throws IOException;
	Optional<Conference> getConferenceDetail(Long id) ;
	
}
