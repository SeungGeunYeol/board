package com.study.board.app.board.dto;

import com.study.board.app.board.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardIdx;
    private String registDe;

    public static CommentDTO toCommentDTO(Comment comment, Long boardIdx) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setCommentWriter(comment.getCommentWriter());
        commentDTO.setCommentContents(comment.getCommentContents());
        commentDTO.setRegistDe(comment.getRegistDe());
        commentDTO.setBoardIdx(boardIdx);
        return commentDTO;
    }
}
