package com.boardapp.boardfe.board.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import com.boardapp.boardfe.board.model.Board;
import com.boardapp.boardfe.board.model.BoardEdit;
import com.boardapp.boardfe.board.model.BoardSave;
import com.boardapp.boardfe.common.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
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
        // MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();

        // boardMono.map(board -> Mono.fromRunnable(
        //     () -> {
        //         formData.add("title", board.getTitle());
        //         formData.add("contents",board.getContents());
        //         formData.add("writeName",board.getWriteName());
        //     }
        // ));

        return this.webClient.getWebClient()
                                .post()
                                .uri("/boards")
                                // .contentType(MediaType.APPLICATION_JSON)
                                .body(boardMono, BoardSave.class)
                                // .body(BodyInserters.fromFormData("title", "test")
                                //                     .with("contents", "test con")
                                //                     .with("writeName", "tester"))
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
        log.warn("Received Parameter : " +id);

        return this.webClient.getWebClient()
                                .delete()
                                .uri("/boards/:" + id)
                                .retrieve()
                                .bodyToMono(Void.class);
    }
}
