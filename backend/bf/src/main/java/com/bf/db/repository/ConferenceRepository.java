package com.bf.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bf.db.entity.Conference;


/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
	Optional<Conference> findById(Long id);
	Optional<List<Conference>> findByIsActive(boolean isActive);
}
