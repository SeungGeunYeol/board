package com.study.board.app.board.dto;


import com.study.board.app.board.entity.BoardEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

    private Long boardIdx;
    private String boardTitle;
    private String boardContent;
    private String fileName;
    private String filePath;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardIdx(boardEntity.getBoardIdx());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContent(boardEntity.getBoardContent());
//        boardDTO.setFileName(boardEntity.getFileName());
//        boardDTO.setFilePath(boardEntity.getFilePath());
        return boardDTO;
    }
}
