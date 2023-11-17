package com.study.board.app.system.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    //회원가입 페이지 출력
    @GetMapping("/member/save")
    public String saveForm() {

        return "memberSave";
    }
}
