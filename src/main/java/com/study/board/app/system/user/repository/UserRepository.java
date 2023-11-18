package com.study.board.app.system.user.repository;

import com.study.board.app.system.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 이메일로 회웑 정보 조회
    Optional<UserEntity> findByUserEmail(String userEmail);
    List<UserEntity> findAllByOrderByUserIdxDesc();
}
