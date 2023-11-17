package com.study.board.app.board.web;

import com.study.board.app.board.dto.BoardDTO;
import com.study.board.app.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write") //localhost:8080/board/write
    public String boardWriteForm() {

        return "board/boardWriteForm";
    }

    @PostMapping("/save")
    public String boardWriteDo(@ModelAttribute BoardDTO boardDTO) throws IOException {

        boardService.save(boardDTO);

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String boardList(Model model) {
        List<BoardDTO> boardDTOList = boardService.findALl();
        model.addAttribute("boardList", boardDTOList);

        return "board/boardList";
    }

    @GetMapping("/view") // localhost:8080/board/view?id=1
    public String boardView(Model model, Long id) {

        model.addAttribute("board", boardService.findById(id));

        return "board/boardView";
    }

    @GetMapping("/delete")
    public String boardDelete(Long id) {

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {

        model.addAttribute("board", boardService.findById(id));

        return "board/boardUpdate";
    }

    @PostMapping("/update")
    public String boardUpdate(@ModelAttribute BoardDTO boardDTO, Model model) {

        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);

        return "board/boardView";
    }
}
