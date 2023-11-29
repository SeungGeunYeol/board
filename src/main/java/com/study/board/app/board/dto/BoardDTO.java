package com.study.board.app.board.dto;


import com.study.board.app.board.entity.Board;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

    private Long boardIdx;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardPd;
    private int boardHits;
    private LocalDateTime registDe;
    private LocalDateTime updateDe;

    private MultipartFile boardFile; // save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)


    public BoardDTO(Long boardIdx, String boardWriter, String boardTitle, int boardHits, LocalDateTime registDe) {
        this.boardIdx = boardIdx;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.registDe = registDe;
    }

    public static BoardDTO toBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardIdx(board.getBoardIdx());
        boardDTO.setBoardWriter(board.getBoardWriter());
        boardDTO.setBoardPd(board.getBoardPd());
        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContent(board.getBoardContent());
        boardDTO.setBoardHits(board.getBoardHits());
        boardDTO.setRegistDe(board.getRegistDe());
        boardDTO.setUpdateDe(board.getUpdateDe());
        if (board.getFileAttached() == 0) {
            boardDTO.setFileAttached(board.getFileAttached()); // 0
        } else {
            boardDTO.setFileAttached(board.getFileAttached()); // 1
            boardDTO.setOriginalFileName(board.getBoardFileList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(board.getBoardFileList().get(0).getStoredFileName());
        }

        return boardDTO;
    }
}
