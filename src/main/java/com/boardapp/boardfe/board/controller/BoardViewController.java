package com.boardapp.boardfe.board.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
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
    public String list(PagerInfo pagerInfo, Model model) {
        // Flux<Board> boardList = this.boardService.getAllBoards();
        // List<Board> boardList = this.boardService.getAllBoards().collectList().block();

        IReactiveDataDriverContextVariable reactiveDataDrivenMode = new ReactiveDataDriverContextVariable(this.boardService.getAllBoards(),1);

        model.addAttribute("boardList", reactiveDataDrivenMode);
        model.addAttribute("pagerInfo", pagerInfo);

        return "board/list";
    }
}
