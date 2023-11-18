package com.study.board.app.system.user.service;

import com.study.board.app.system.user.dto.UserDTO;
import com.study.board.app.system.user.entity.UserEntity;
import com.study.board.app.system.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserDTO userDTO) {

        userRepository.save(UserEntity.toUserEntity(userDTO));

    }

    public UserDTO login(UserDTO userDTO) {
        // 회원이 입력한 이메일로 DB에서 조회
        // DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단.
        Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(userDTO.getUserEmail());

        if (byUserEmail.isPresent()) {
            // 조회 결과가 있다. (해당 이메일을 가진 회원 정보가 있다.)
            UserEntity userEntity = byUserEmail.get();

            if (userEntity.getUserPassword().equals(userDTO.getUserPassword())) {
                // 비밀번호 일치
                // entity -> dto 객체로 변환
                return UserDTO.toUserDTO(userEntity);
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            // 조회 결과가 없다. (해당 이메일을 가진 회원이 없다.
            return null;
        }
    }

    public List<UserDTO> findAll() {
        List<UserEntity> userEntityList = userRepository.findAllByOrderByUserIdxDesc();
        List<UserDTO> userDTOList = new ArrayList<>();

        // userEntiryList -> userDTOList 로 변환
        for (UserEntity userEntity : userEntityList) {
            userDTOList.add(UserDTO.toUserDTO(userEntity));
        }

        return userDTOList;

    }

    public UserDTO findById(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        return optionalUserEntity.map(UserDTO::toUserDTO).orElse(null);
    }

    public void update(UserDTO userDTO) {
        userRepository.save(UserEntity.toUpdateUserEntity(userDTO));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
