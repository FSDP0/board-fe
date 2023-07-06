package com.boardapp.boardfe.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.boardapp.boardfe.board.model.Board;
import com.boardapp.boardfe.board.service.BoardService;
import com.boardapp.boardfe.common.util.PagerInfo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardViewController {
    // ! Dependency injection
    private final BoardService boardService;

    @GetMapping("/list")
    public Mono<String> list(PagerInfo pagerInfo, Model model) {
        Flux<Board> boardList = this.boardService.getAllBoards();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return Mono.just("board/list");
    }
}
