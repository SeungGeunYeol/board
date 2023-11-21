package com.study.board.app.board.entity;

import com.study.board.app.board.dto.BoardDTO;
import com.study.board.cmmn.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    @Column(nullable = false)
    private String boardTitle;

    @Column(length = 3000, nullable = false)
    private String boardContent;

//    @Column
//    private int boardHits;

    public static Board toSaveEntity(BoardDTO boardDTO) {
        Board boardEntity = new Board();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
//        boardEntity.setBoardHits(0);
//        boardEntity.setFileName(boardDTO.getFileName());
//        boardEntity.setFilePath(boardDTO.getFilePath());
        return boardEntity;
    }

    public static Board toUpdateEntity(BoardDTO boardDTO) {
        Board boardEntity = new Board();
        boardEntity.setBoardIdx(boardDTO.getBoardIdx());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
//        boardEntity.setFileName(boardDTO.getFileName());
//        boardEntity.setFilePath(boardDTO.getFilePath());
        return boardEntity;
    }
}
