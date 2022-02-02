package com.bf.api.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.bf.api.request.ConferenceFetchReq;
import com.bf.api.request.ConferenceRegisterPostReq;
import com.bf.api.response.ConferenceDetailRes;
import com.bf.api.response.ConferenceRes;
import com.bf.db.entity.Conference;
import com.bf.db.entity.ConferenceHistory;


public interface ConferenceService {
	Conference createConference(ConferenceRegisterPostReq conference);
	List<ConferenceRes> getAllConference() throws NoSuchElementException ;
	List<ConferenceRes> getAllEndConference() throws NoSuchElementException ;
	ConferenceDetailRes getConferenceDetail(Long id) throws NoSuchElementException;
	Conference updateConference(ConferenceFetchReq conferenceFetchReq) throws NoSuchElementException;
	Conference endConference(Long id);

}
