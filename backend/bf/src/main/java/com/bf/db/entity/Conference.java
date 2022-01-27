package com.bf.db.entity;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * conference[컨퍼런스] 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
@AttributeOverride(name = "id", column = @Column(name = "conferenceSerial"))
public class Conference extends BaseEntity{
	String title;
	String description;
	String password;
	String thumbnail;
	Boolean isActive;
	String userId;//host
	LocalDateTime startTime;
	LocalDateTime endTime;
}
