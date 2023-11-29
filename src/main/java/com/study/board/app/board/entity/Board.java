package com.study.board.app.board.entity;

import com.study.board.app.board.dto.BoardDTO;
import com.study.board.cmmn.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPd;

    @Column
    private int boardHits;

    @Column
    private int fileAttached; // 0 or 1

    /* Board:BoardFile = 1:N */
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFile> boardFileList = new ArrayList<>();

    /* Board:Comment = 1:N */
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    public static Board toSaveEntity(BoardDTO boardDTO) {
        Board boardEntity = new Board();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPd(boardDTO.getBoardPd());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(0); // 파일 없음
        return boardEntity;
    }

    public static Board toSaveFileEntity(BoardDTO boardDTO) {
        Board boardEntity = new Board();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPd(boardDTO.getBoardPd());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(1); // 파일 있음.
        return boardEntity;
    }

    public static Board toUpdateEntity(BoardDTO boardDTO) {
        Board boardEntity = new Board();
        boardEntity.setBoardIdx(boardDTO.getBoardIdx());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPd(boardDTO.getBoardPd());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }
}
