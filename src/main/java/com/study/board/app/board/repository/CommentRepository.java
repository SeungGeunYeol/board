package com.study.board.app.board.repository;

import com.study.board.app.board.entity.Board;
import com.study.board.app.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoardOrderByIdDesc(Board board);
}
