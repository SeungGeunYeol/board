package com.study.board.app.system.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private Long userIdx;
    private Integer userId;
    private String userEmail;
    private String userPassword;
    private String userName;
}
