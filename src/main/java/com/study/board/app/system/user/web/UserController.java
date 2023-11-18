package com.study.board.app.system.user.web;

import com.study.board.app.system.user.dto.UserDTO;
import com.study.board.app.system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입 페이지 출력
    @GetMapping("/insert")
    public String insetForm() {

        return "system/user/user_insert";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute UserDTO userDTO) {

        userService.save(userDTO);

        return "login/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {

        UserDTO login = userService.login(userDTO);
        if (login != null) {
            // login 성공
            session.setAttribute("userName", login.getUserName());
            session.setAttribute("userIdx", login.getUserIdx());

            return "redirect:/board/list";
        } else {
            return "login/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/list")
    public String findAll(Model model) {

        model.addAttribute("userList", userService.findAll());

        return "system/user/user_list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {

        model.addAttribute("user", userService.findById(id));

        return "system/user/user_view";
    }

    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {

        Long id = (Long) session.getAttribute("userIdx");
        model.addAttribute("updateUser", userService.findById(id));

        return "system/user/user_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute UserDTO userDTO) {

        userService.update(userDTO);
        return "redirect:/user/" + userDTO.getUserIdx();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }
}
