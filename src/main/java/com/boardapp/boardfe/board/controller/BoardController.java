package com.boardapp.boardfe.board.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boardapp.boardfe.board.model.Board;
import com.boardapp.boardfe.board.model.BoardEdit;
import com.boardapp.boardfe.board.model.BoardSave;
import com.boardapp.boardfe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
// @RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
    // ! Dependency injection
    private final BoardService boardService;

    @GetMapping
    public Flux<Board> findAllBoards() {
        return this.boardService.getAllBoards();
    }

    @GetMapping("/:{id}")
    public Mono<Board> findByBoardId(@PathVariable Long id) {
        return this.boardService.getByBoardId(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    private String createBoard(
        BoardSave board
        // @RequestParam String title,
        // @RequestParam String contents,
        // @RequestParam String writeName
        // String title,
        // String contents,
        // String writeName
        // @RequestBody BoardSave boardS
    ) {
        // log.warn(title);
        // log.warn(contents);
        // log.warn(writeName);
        log.warn(board.getTitle());        

        return "sample";
        // return this.boardService.saveBoard(boardMono);
    }

    @PutMapping("/:{id}")
    private Mono<Void> updateBoard(@PathVariable Long id, @RequestBody Mono<BoardEdit> boardMono) {
        return this.boardService.updateBoard(id, boardMono);
    }

    @DeleteMapping("/:{id}")
    public Mono<Void> deleteBoard(@PathVariable Long id) {
        return this.boardService.deleteBoard(id);
    }
}
