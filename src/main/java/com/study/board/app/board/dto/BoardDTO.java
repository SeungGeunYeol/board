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
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String boardPd;
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
            // 파일 이름을 가져가야 함.
            // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
            // join
            // select * from board_table b, board_file_table bf where b.id=bf.board_id
            // and where b.id=?
            boardDTO.setOriginalFileName(board.getBoardFileList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(board.getBoardFileList().get(0).getStoredFileName());
        }

        return boardDTO;
    }
}
