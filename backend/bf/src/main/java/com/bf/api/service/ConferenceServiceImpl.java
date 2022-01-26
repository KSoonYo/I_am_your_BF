package com.bf.api.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bf.api.request.ConferenceRegisterPostReq;
import com.bf.common.auth.SsafyUserDetails;
import com.bf.common.util.FileHandler;
import com.bf.db.entity.Conference;
import com.bf.db.repository.ConferenceRepository;
import com.bf.db.repository.ConferenceRepositorySupport;

@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService{
	
	@Autowired
	ConferenceRepository conferenceRepository;
	
	@Autowired
	ConferenceRepositorySupport conferenceRepositorySupport;
	
	private FileHandler fileHandler= new FileHandler();
	

	@Override
	public Conference createConference(Conference conference,MultipartFile thumbnail) throws URISyntaxException,Exception {
		// TODO Auto-generated method stub
		
		conference.setThumbnail(fileHandler.parseFileInfo(thumbnail));
		
		conference.setIsActive(true);
		conference.setStartTime(LocalDateTime.now());

		return conferenceRepository.save(conference);
	}


	@Override
	public Optional<List<Conference>> getAllConference() {
		// TODO Auto-generated method stub
		
		return conferenceRepository.findByIsActive(true);
	}

}
