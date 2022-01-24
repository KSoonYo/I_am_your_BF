package com.bf.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * conference_category[컨퍼런스 종류] 모델 정의.
 */
@Entity
@Getter
@Setter
public class ConferenceCategory extends BaseEntity{
    String name;
}
