package com.study.board.app.system.user.service;

import com.study.board.app.system.user.dto.UserDTO;
import com.study.board.app.system.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserDTO userDTO) {

    }
}
