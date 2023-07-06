package com.boardapp.boardfe.board.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.boardapp.boardfe.board.model.Board;
import com.boardapp.boardfe.board.model.BoardEdit;
import com.boardapp.boardfe.board.model.BoardSave;
import com.boardapp.boardfe.common.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BoardService {
    // ! Dependency injection
    private final WebClientConfig webClient;

    public Flux<Board> getAllBoards() {
        return this.webClient.getWebClient()
                                .get()
                                .uri("/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .bodyToFlux(Board.class);
    }

    public Mono<Board> getByBoardId(Long id) {
        return this.webClient.getWebClient()
                                .get()
                                .uri("/boards/:" + id)
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .bodyToMono(Board.class);
    }

    public Mono<Void> saveBoard(Mono<BoardSave> boardMono) {
        return this.webClient.getWebClient()
                                .post()
                                .uri("/boards")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(boardMono, BoardSave.class)
                                .retrieve()
                                .bodyToMono(Void.class);
    }

    public Mono<Void> updateBoard(Long id,Mono<BoardEdit> boardMono) {
        return this.webClient.getWebClient()
                                .put()
                                .uri("/boards/:" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(boardMono,BoardEdit.class)
                                .retrieve()
                                .bodyToMono(Void.class);
    }

    public Mono<Void> deleteBoard(Long id) {
        return this.webClient.getWebClient()
                                .delete()
                                .uri("/boards/:" + id)
                                .retrieve()
                                .bodyToMono(Void.class);
    }
}
