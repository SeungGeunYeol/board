package com.study.board.app.system.user.entity;

import com.study.board.app.system.user.dto.UserDTO;
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

    public static UserEntity toUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserPassword(userDTO.getUserPassword());
        userEntity.setUserName(userDTO.getUserName());
        return userEntity;
    }

    public static UserEntity toUpdateUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserIdx(userDTO.getUserIdx());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserPassword(userDTO.getUserPassword());
        userEntity.setUserName(userDTO.getUserName());
        return userEntity;
    }

//    private String useAt;
//    private String registDe;
//    private String registIdx;
//    private String updtDe;
//    private String updtIdx;
}
