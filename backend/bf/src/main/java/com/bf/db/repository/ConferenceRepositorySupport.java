package com.bf.db.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bf.db.entity.Conference;
import com.bf.db.entity.QConference;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 회의실 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */

@Repository
public class ConferenceRepositorySupport {
	@Autowired
    private JPAQueryFactory jpaQueryFactory;
	QConference qConference= QConference.conference;
	
	Optional<Conference> findByID(Long id){
		Conference conference= jpaQueryFactory.select(qConference).from(qConference)
				.where(qConference.id.eq(id)).fetchOne();
		if(conference==null) return Optional.empty();
		return Optional.ofNullable(conference);
		
		
	}
}
