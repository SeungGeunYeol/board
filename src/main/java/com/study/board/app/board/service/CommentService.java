package com.study.board.app.board.service;

import com.study.board.app.board.dto.CommentDTO;
import com.study.board.app.board.entity.Board;
import com.study.board.app.board.entity.Comment;
import com.study.board.app.board.repository.BoardRepository;
import com.study.board.app.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        /* 부모 엔티티(Board) 조회 */
        Optional<Board> optionalBoard = boardRepository.findById(commentDTO.getBoardIdx());

        if (optionalBoard.isPresent()) {
            Comment comment = Comment.toSaveEntity(commentDTO, optionalBoard.get());
            return commentRepository.save(comment).getId();
        } else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long boardIdx) {

        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> new NoSuchElementException("Board doesn't exist"));

        List<Comment> commentList = commentRepository.findAllByBoardOrderByIdDesc(board);
        /* Entity -> DTOList 로 변환 */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(comment, boardIdx);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;

    }
}
