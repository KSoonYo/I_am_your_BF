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
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bf.api.request.ConferenceFetchReq;
import com.bf.api.request.ConferenceRegisterPostReq;
import com.bf.api.response.ConferenceDetailRes;
import com.bf.api.response.ConferenceRes;
import com.bf.common.auth.SsafyUserDetails;
//import com.bf.common.util.FileHandler;
import com.bf.db.entity.Conference;
import com.bf.db.entity.User;
import com.bf.db.repository.ConferenceRepository;
import com.bf.db.repository.ConferenceRepositorySupport;
import com.bf.db.repository.UserRepository;

@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService{
	
	@Autowired
	ConferenceRepository conferenceRepository;
	
	@Autowired
	UserRepository userRepository;
	
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
	public List<ConferenceRes> getAllConference() throws NoSuchElementException {
		// TODO Auto-generated method stub
		List<ConferenceRes> conferencereses=new ArrayList<ConferenceRes>();
		List<Conference> result=conferenceRepository.findByIsActive(true).get();

		for (int i = 0; i < result.size(); i++) {
			Optional<User> user=userRepository.findUserByUserId(result.get(i).getUserId());
			
			ConferenceRes conferenceres=new ConferenceRes();
			conferenceres.setId(result.get(i).getId());
			conferenceres.setTitle(result.get(i).getTitle());
			conferenceres.setUserName(user.get().getUserName());//애를 이 userid로 검색해서 name을 가져오게 해야됨
			conferenceres.setDescription(result.get(i).getDescription());
			conferenceres.setThumbnail(result.get(i).getThumbnail());
					
			conferencereses.add(conferenceres);
		}
		
		return conferencereses;
	}
	
	@Override
	public List<ConferenceRes> getAllEndConference() throws NoSuchElementException {
		// TODO Auto-generated method stub
		List<ConferenceRes> conferencereses=new ArrayList<ConferenceRes>();
		List<Conference> result=conferenceRepository.findByIsActive(false).get();

		for (int i = 0; i < result.size(); i++) {
			Optional<User> user=userRepository.findUserByUserId(result.get(i).getUserId());
			ConferenceRes conferenceres=new ConferenceRes();
			conferenceres.setId(result.get(i).getId());
			conferenceres.setTitle(result.get(i).getTitle());
			conferenceres.setUserName(user.get().getUserName());
			conferenceres.setDescription(result.get(i).getDescription());
			conferenceres.setThumbnail(result.get(i).getThumbnail());

			conferencereses.add(conferenceres);
		}
		
		return conferencereses;
	}


	@Override
	public ConferenceDetailRes getConferenceDetail(Long id) throws NoSuchElementException {
		// TODO Auto-generated method stub
		Conference conference=conferenceRepository.findById(id).get();
		ConferenceDetailRes conferenceDetail=new ConferenceDetailRes();
		conferenceDetail.setDescription(conference.getDescription());
		conferenceDetail.setId(conference.getId());
		conferenceDetail.setTitle(conference.getTitle());
		conferenceDetail.setUserId(conference.getUserId());
		conferenceDetail.setThumbnail(conference.getThumbnail());
		conferenceDetail.setPassword(conference.getPassword());
		conferenceDetail.setStartTime(conference.getStartTime());
		conferenceDetail.setEndTime(conference.getEndTime());
		conferenceDetail.setUserName(userRepository.findUserByUserId(conference.getUserId()).get().getUserName());
		
		return conferenceDetail;
	}


	@Override
	public Conference updateConference(ConferenceFetchReq conferenceFetchReq) throws NoSuchElementException
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
