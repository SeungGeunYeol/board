package com.study.board.app.board.service;

import com.study.board.app.board.dto.BoardDTO;
import com.study.board.app.board.entity.BoardEntity;
import com.study.board.app.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 글 작성 처리
    public void save(BoardDTO boardDTO) throws IOException {

        boardRepository.save(BoardEntity.toSaveEntity(boardDTO));
    }

    // 게시글 리스트 처리
    @Transactional(readOnly = true)
    public List<BoardDTO> findALl() {

        List<BoardEntity> boardEntityList = boardRepository.findAll();
        ArrayList<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    // 특정 게시글 불러오기
    @Transactional(readOnly = true)
    public BoardDTO findById(Long id) {

        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            return BoardDTO.toBoardDTO(boardEntity);
        } else {
            return null;
        }
    }

    // 특정 게시글 삭제
    public void boardDelete(Long id) {
        boardRepository.deleteById(id);
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getBoardIdx());
    }


//    // 특정 키워드로 게시글 검색
//    public Page<BoardEntity> boardSearchList(String searchKeyword, Pageable pageable) {
//
//        return boardRepository.findByTitleContaining(searchKeyword, pageable);
//    }


}
