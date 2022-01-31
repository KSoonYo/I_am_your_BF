package com.bf.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * conferenceHistory[컨퍼런스 기록] 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
@AttributeOverride(name = "id", column = @Column(name = "histroySerial"))
public class ConferenceHistory extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "userSerial")
	User userSerial;

	@ManyToOne
	@JoinColumn(name = "conferenceSerial")
	Conference conferenceSerial;

	LocalDateTime enterTime;
	LocalDateTime exitTime;
}
