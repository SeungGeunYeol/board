package com.study.board.app.board.dto;


import com.study.board.app.board.entity.Board;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

    private Long boardIdx;
    private String boardWriter;
    private String boardTitle;
    private String boardContent;
    private int boardHits;
    private LocalDate registDe;
    private LocalDate updateDe;

    private MultipartFile boardFile; // save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)


    public BoardDTO(Long boardIdx, String boardTitle) {
        this.boardIdx = boardIdx;
        this.boardTitle = boardTitle;
    }

    public static BoardDTO toBoardDTO(Board boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardIdx(boardEntity.getBoardIdx());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContent(boardEntity.getBoardContent());
//        boardDTO.setFileName(boardEntity.getFileName());
//        boardDTO.setFilePath(boardEntity.getFilePath());
        return boardDTO;
    }
}
