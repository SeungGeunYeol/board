package com.study.board.app.board.web;

import com.study.board.app.board.dto.BoardDTO;
import com.study.board.app.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save") //localhost:8080/board/save
    public String boardWriteForm() {

        return "board/board_writeForm";
    }

    @PostMapping("/save")
    public String boardWriteDo(@ModelAttribute BoardDTO boardDTO) throws IOException {

        boardService.save(boardDTO);

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String paging(@PageableDefault(page=1) Pageable pageable, Model model) {

        Page<BoardDTO> boardList = boardService.findAll(pageable);

        int blockLimit = 5;

        // 1, 6, 11, 16...
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), boardList.getTotalPages());

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/board_list";
    }

    @GetMapping("/view/{id}") // localhost:8080/board/view?id=1
    public String boardView(Model model, @PathVariable Long id) {

        //  해당 게시글의 조회수를 하나 올리고 게시글 데이터를 가져와서 board_view.html 에 출력
        boardService.updateHits(id);

        model.addAttribute("board", boardService.findById(id));

        return "board/board_view";
    }

    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable Long id) {

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {

        model.addAttribute("board", boardService.findById(id));

        return "board/board_update";
    }

    @PostMapping("/update")
    public String boardUpdate(@ModelAttribute BoardDTO boardDTO, Model model) {

        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);

        return "board/board_view";
    }

}
