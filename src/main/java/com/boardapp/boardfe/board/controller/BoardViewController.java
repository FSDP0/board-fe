package com.boardapp.boardfe.board.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.boardapp.boardfe.board.model.Board;
import com.boardapp.boardfe.board.model.BoardEdit;
import com.boardapp.boardfe.board.model.BoardSave;
import com.boardapp.boardfe.board.service.BoardService;
import com.boardapp.boardfe.common.util.PagerInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardViewController {
    // ! Dependency injection
    private final BoardService boardService;

    @GetMapping("/list")
    public Mono<String> list(PagerInfo pagerInfo, Model model) {
        Flux<Board> boardFlux = this.boardService.getAllBoards();


        model.addAttribute("boardList", boardFlux);
        model.addAttribute("pagerInfo", pagerInfo);

        return Mono.just("board/list");
    }

    @GetMapping("/view")
    public Mono<String> view(@RequestParam Long num,Model model){
        Mono<Board> board = this.boardService.getByBoardId(num);

        model.addAttribute("board",board);

        return Mono.just("board/view");
    }

    @GetMapping("/write")
    public Mono<String> write() {
        return Mono.just("board/form");
    }

    @GetMapping("/modifyForm")
    public Mono<String> edit(@RequestParam Long num,Model model){
        Mono<Board> boardMono = this.boardService.getByBoardId(num);

        model.addAttribute("board", boardMono);

        return Mono.just("board/form");
    }

    @PostMapping(value = "/writeSubmit", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<String> writeSubmit(Mono<BoardSave> boardMono, PagerInfo pagerInfo, Model model) {
        this.boardService.saveBoard(boardMono).subscribe();

        Flux<Board> boardFlux = this.boardService.getAllBoards();

        model.addAttribute("boardList", boardFlux);
        model.addAttribute("pagerInfo", pagerInfo);

        // * Redirect url
        return Mono.just("redirect:/boards/list");
    }

    @PostMapping(value = "/modifySubmit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<String> modifySubmit(Mono<BoardEdit> boardMono, PagerInfo pagerInfo, Model model) {
        this.boardService.updateBoard(Long.valueOf(1),boardMono).subscribe();

        Flux<Board> boardFlux = this.boardService.getAllBoards();

        model.addAttribute("boardList", boardFlux);
        model.addAttribute("pagerInfo", pagerInfo);

        // * Redirect url
        return Mono.just("redirect:/boards/list");
    }

    @GetMapping("/delete")
    public Mono<String> delete(@RequestParam Long num,PagerInfo pagerInfo,Model model){
        log.warn(num+"");;

        this.boardService.deleteBoard(num).subscribe();

        Flux<Board> boardFlux = this.boardService.getAllBoards();

        model.addAttribute("boardList", boardFlux);
        model.addAttribute("pagerInfo", pagerInfo);

        // Redirect url
        return Mono.just("redirect:/boards/list");
    }

    @GetMapping("/home")
    public String test(PagerInfo pagerInfo,Model model){
        
        return "board/layout/basic";
    }
}
