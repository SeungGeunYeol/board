package com.study.board.app.board.repository;

import com.study.board.app.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
        // custom method 등록 방법
    // SQL Table Query 기준 --> update board_table set board_hits=board_hits+1 where id=?
    // 아래는 entity 기준
    @Modifying
    @Query(value = "update Board b set b.boardHits=b.boardHits+1 where b.boardIdx=:id")
    void updateHits(@Param("id") Long id);
}
