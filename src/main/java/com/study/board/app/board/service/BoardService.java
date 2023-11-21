package com.study.board.app.board.service;

import com.study.board.app.board.dto.BoardDTO;
import com.study.board.app.board.entity.Board;
import com.study.board.app.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 글 작성 처리
    public void save(BoardDTO boardDTO) {



        boardRepository.save(Board.toSaveEntity(boardDTO));
    }

    // 게시글 리스트 처리
    @Transactional(readOnly = true)
    public Page<BoardDTO> findAll(Pageable pageable) {

        int page = pageable.getPageNumber() - 1;

        int pageLimit = 10; // 한 페이지에 보여줄 글의 개수

        Page<Board> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));

        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getBoardIdx(), board.getBoardTitle()));
        return boardDTOS;
    }

    // 특정 게시글 불러오기
    @Transactional(readOnly = true)
    public BoardDTO findById(Long id) {

        Optional<Board> optionalBoardEntity = boardRepository.findById(id);

//        if (optionalBoardEntity.isPresent()) {
//            BoardEntity boardEntity = optionalBoardEntity.get();
//            return BoardDTO.toBoardDTO(boardEntity);
//        } else {
//            return null;
//        }

        // 값이 존재하면 제공된 매핑 함수를 적용하고 결과가 null이 아니면 결과에 대한 Optional을 반환한다.
        // 그렇지 않은 경우 즉, 적용 결과가 null인 경우 빈 Optional을 반환한다.
        return optionalBoardEntity.map(BoardDTO::toBoardDTO).orElseThrow(() -> new NoSuchElementException("Board Not Found"));

    }

    // 특정 게시글 삭제
    public void boardDelete(Long id) {
        boardRepository.deleteById(id);
    }

    public BoardDTO update(BoardDTO boardDTO) {
        Board boardEntity = Board.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getBoardIdx());
    }

//    @Transactional
//    public void updateHits(Long id) {
//        boardRepository.updateHits(id);
//    }


//    // 특정 키워드로 게시글 검색
//    public Page<BoardEntity> boardSearchList(String searchKeyword, Pageable pageable) {
//
//        return boardRepository.findByTitleContaining(searchKeyword, pageable);
//    }


}
