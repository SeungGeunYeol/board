package com.study.board.app.system.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_table")
public class UserEntity {

    @Id  //PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long userIdx;

    @Column(unique = true) // unique 제양조건 추가
    private String userEmail;

    @Column(name = "user_password", length = 100, nullable = false)
    private String userPassword;

    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;

//    private String useAt;
//    private String registDe;
//    private String registIdx;
//    private String updtDe;
//    private String updtIdx;
}
