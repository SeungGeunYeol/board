package com.study.board.app.board.entity;

import com.study.board.app.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    @Column(nullable = false)
    private String boardTitle;

    @Column(length = 3000, nullable = false)
    private String boardContent;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
//        boardEntity.setFileName(boardDTO.getFileName());
//        boardEntity.setFilePath(boardDTO.getFilePath());
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardIdx(boardDTO.getBoardIdx());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
//        boardEntity.setFileName(boardDTO.getFileName());
//        boardEntity.setFilePath(boardDTO.getFilePath());
        return boardEntity;
    }
}
