package com.study.board.app.board.service;

import com.study.board.app.board.dto.BoardDTO;
import com.study.board.app.board.entity.Board;
import com.study.board.app.board.entity.BoardFile;
import com.study.board.app.board.repository.BoardFileRepository;
import com.study.board.app.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    // 글 작성 처리
    public void save(BoardDTO boardDTO) throws IOException {

        if (boardDTO.getBoardFile().isEmpty()) {
            // 첨부 파일이 없음
            Board board = Board.toSaveEntity(boardDTO);
            boardRepository.save(board);
        } else {
            // 첨부 파일이 있음
            /*
                1. DTO에 담긴 파일을 꺼낸다.
                2. 파일의 이름을 가져온다.
                3. 서버 저장용 이름으로 추가
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. board_table에 해당 데이터 save처리
                7. board_file_talbe에 해당 데이터 save 처리
             */

            Long saveId = boardRepository.save(Board.toSaveFileEntity(boardDTO)).getBoardIdx(); //6
            Board board = boardRepository.findById(saveId).get();

            MultipartFile boardFile = boardDTO.getBoardFile(); // 1
            String originalFileName = boardFile.getOriginalFilename(); // 2
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName; // 3
            String savePath = "C:\\workspace_spring_boot_img\\" + storedFileName; // 4

            boardFile.transferTo(new File(savePath)); // 5


            BoardFile boardFileEntity = BoardFile.toBoardFile(board, originalFileName, storedFileName);
            boardFileRepository.save(boardFileEntity); // 7


        }
    }

    // 게시글 리스트 처리
    @Transactional(readOnly = true)
    public Page<BoardDTO> findAll(Pageable pageable) {

        int page = pageable.getPageNumber() - 1;

        int pageLimit = 10; // 한 페이지에 보여줄 글의 개수

        Page<Board> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIdx")));

        return boardEntities.map(board -> new BoardDTO(board.getBoardIdx(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getRegistDe()));
    }

    // 특정 게시글 불러오기
    @Transactional(readOnly = true)
    public BoardDTO findById(Long id) {

        Optional<Board> optionalBoardEntity = boardRepository.findById(id);

        if (optionalBoardEntity.isPresent()) {
            Board board = optionalBoardEntity.get();
            return BoardDTO.toBoardDTO(board);
        } else {
            return null;
        }

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

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }
}
