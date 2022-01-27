package com.bf.api.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bf.api.request.ConferenceFetchReq;
import com.bf.api.request.ConferenceRegisterPostReq;
import com.bf.api.response.ConferenceRes;
import com.bf.common.auth.SsafyUserDetails;
//import com.bf.common.util.FileHandler;
import com.bf.db.entity.Conference;
import com.bf.db.repository.ConferenceRepository;
import com.bf.db.repository.ConferenceRepositorySupport;

@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService{
	
	@Autowired
	ConferenceRepository conferenceRepository;
	
	@Autowired
	ConferenceRepositorySupport conferenceRepositorySupport;
	
//	private FileHandler fileHandler= new FileHandler();
	

	@Override
	public Conference createConference(ConferenceRegisterPostReq conferenceRegisterPostReq) {
		// TODO Auto-generated method stub
		Conference conference=new Conference();
		conference.setTitle(conferenceRegisterPostReq.getTitle());
		conference.setDescription(conferenceRegisterPostReq.getDescription());
		conference.setPassword(conferenceRegisterPostReq.getPassword());
		conference.setUserId(conferenceRegisterPostReq.getUserId());
		conference.setThumbnail(conferenceRegisterPostReq.getThumbnail());
		conference.setIsActive(true);
		conference.setStartTime(LocalDateTime.now());

		return conferenceRepository.save(conference);
	}


	@Override
	public List<ConferenceRes> getAllConference() {
		// TODO Auto-generated method stub
		List<ConferenceRes> conferencereses=new ArrayList<ConferenceRes>();
		List<Conference> result=conferenceRepository.findByIsActive(true).get();

		for (int i = 0; i < result.size(); i++) {
			ConferenceRes conferenceres=new ConferenceRes();
			conferenceres.setId(result.get(i).getId());
			conferenceres.setTitle(result.get(i).getTitle());
			conferenceres.setUserId(result.get(i).getUserId());
			conferenceres.setDescription(result.get(i).getDescription());
			conferenceres.setThumbnail(result.get(i).getThumbnail());
			
			
//			InputStream imageStream = new FileInputStream(result.get(i).getThumbnail());
//			byte[] imageByteArray = IOUtils.toByteArray(imageStream);
//			imageStream.close();
//			
//			conferenceres.setThumbnail(imageByteArray);
//			
			conferencereses.add(conferenceres);
		}
		
		return conferencereses;
	}
	
	@Override
	public List<ConferenceRes> getAllEndConference() {
		// TODO Auto-generated method stub
		List<ConferenceRes> conferencereses=new ArrayList<ConferenceRes>();
		List<Conference> result=conferenceRepository.findByIsActive(false).get();

		for (int i = 0; i < result.size(); i++) {
			ConferenceRes conferenceres=new ConferenceRes();
			conferenceres.setId(result.get(i).getId());
			conferenceres.setTitle(result.get(i).getTitle());
			conferenceres.setUserId(result.get(i).getUserId());
			conferenceres.setDescription(result.get(i).getDescription());
			conferenceres.setThumbnail(result.get(i).getThumbnail());

			conferencereses.add(conferenceres);
		}
		
		return conferencereses;
	}


	@Override
	public Optional<Conference> getConferenceDetail(Long id) {
		// TODO Auto-generated method stub
		Optional<Conference> conference=conferenceRepository.findById(id);
		return conference;
	}


	@Override
	public Conference updateConference(ConferenceFetchReq conferenceFetchReq)
			{
		
		Conference conference= conferenceRepository.findById(conferenceFetchReq.getId()).get();
		conference.setTitle(conferenceFetchReq.getTitle());
		conference.setDescription(conferenceFetchReq.getDescription());
		conference.setPassword(conferenceFetchReq.getPassword());
		conference.setThumbnail(conferenceFetchReq.getThumbnail());
		// TODO Auto-generated method stub
		return conferenceRepository.save(conference);
	}


	@Override
	public Conference endConference(Long id) {
		// TODO Auto-generated method stub
		Conference conference= conferenceRepository.findById(id).get();
		conference.setEndTime(LocalDateTime.now());
		conference.setIsActive(false);
		return conferenceRepository.save(conference);
	}

}
