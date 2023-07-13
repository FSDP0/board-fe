package com.boardapp.boardfe.board.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.boardapp.boardfe.board.model.Board;
import com.boardapp.boardfe.board.service.BoardService;
import com.boardapp.boardfe.common.util.PagerInfo;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardViewController {
    // ! Dependency injection
    private final BoardService boardService;

    @GetMapping("/list")
    private String boardList(PagerInfo pagerInfo, Model model) {
        List<Board> boardList = this.boardService.getAllBoards();

        pagerInfo.init(10, boardList.size());

        // ! Inject object paramter within thymeleaf list data template
        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return "board/list";
    }

    @GetMapping("/view")
    private String boardView(@RequestParam Long num, Model model) {
        Board board = this.boardService.getByBoardId(num);

        // ! Inject object paramter within thymeleaf view template
        model.addAttribute("board", board);

        return "board/view";
    }

    // * Go to write new post form html template
    @GetMapping("/write")
    private String writeBoard() {
        return "board/form";
    }

    @GetMapping("/modifyForm")
    private String editBoard(@RequestParam Long num, Model model){
        Board board = this.boardService.getByBoardId(num);

        // ! Inject object paramter within thymeleaf form template
        model.addAttribute("board", board);

        return "board/form";
    }

    @PostMapping(
        // * Thymeleaf template form th:action attribute route path
        value = "/writeSubmit",
        // ! HTML Form Tag send data with HTTP header [Content-Type] x-www-form-encoded
        // ! @RequestBody annotation will receive JSON content-type data
        // ! Set controller mapped content-type allow x-www-form-encoded with underline
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    ) private String writeSubmit(Board boardDto, PagerInfo pagerInfo, Model model) {
        this.boardService.createBoard(boardDto);

        List<Board> boardList = this.boardService.getAllBoards();

        // ! Inject object paramter within thymeleaf list data template
        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return "redirect:/boards/list"; // ! Redirection to main page route path;
    }

    @PostMapping(
         // * Thymeleaf template form th:action attribute route path
        value = "/modifySubmit",
        // ! HTML Form Tag send data with HTTP header [Content-Type] x-www-form-encoded
        // ! @RequestBody annotation will receive JSON content-type data
        // ! Set controller mapped content-type allow x-www-form-encoded with underline
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    ) private String modifySubmit(Board boardDto, PagerInfo pagerInfo, Model model){
        this.boardService.editBoard(boardDto);

        List<Board> boardList = this.boardService.getAllBoards();

        // ! Inject object paramter within thymeleaf list data template
        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return "redirect:/boards/list"; // ! Redirection to main page route path;
    }

    @GetMapping("/delete")
    public String removeBoard(@RequestParam Long num, PagerInfo pagerInfo, Model model){
        this.boardService.removeBoard(num);

        List<Board> boardList = this.boardService.getAllBoards();

        // ! Inject object paramter within thymeleaf list data template
        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return "redirect:/boards/list"; // ! Redirection to main page route path;
    }
}
