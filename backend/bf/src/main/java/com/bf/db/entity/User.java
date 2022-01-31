package com.bf.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 유저 모델 정의.
 *
 * @author : mo_ah
 * : update user model
 * : update userSerial
 */
@Entity
@Getter
@Setter
@ToString
@AttributeOverride(name = "id", column = @Column(name = "userSerial"))
public class User extends BaseEntity{

    String userId;
    String userName;
    String userEmail;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    String thumbnail;
    String description;
    int report;


    //////
    LocalDateTime createDate;
    LocalDateTime updateDate;
    String Assign;
    
    
}
