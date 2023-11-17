package com.study.board.app.system.user.web;

import com.study.board.app.system.user.dto.UserDTO;
import com.study.board.app.system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입 페이지 출력
    @GetMapping("/user/insert")
    public String insetForm() {

        return "system/user/user_insert";
    }

    @PostMapping("/user/insert")
    public String insert(@ModelAttribute UserDTO userDTO) {

        System.out.println("userDTO : " + userDTO);

        return "";
    }
}
