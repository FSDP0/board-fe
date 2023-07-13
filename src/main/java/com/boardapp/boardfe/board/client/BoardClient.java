package com.boardapp.boardfe.board.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.boardapp.boardfe.board.model.Board;

// ! Board API client interface with OpenFeign
@FeignClient(name = "BOARD-CLIENT", url = "${api.url}")
public interface BoardClient {
    // * Find all posts
    @GetMapping
    public List<Board> getAllBoards();

    // * Find specific post
    @GetMapping("/:{id}")
    public Board getByBoardId(@PathVariable Long id);

    // * Create new post
    @PostMapping
    public String createBoard(@RequestBody Board requestBodyDto);

    // * Modify exist post
    @PutMapping("/:{id}")
    public String editBoard(@PathVariable Long id, @RequestBody Board requestBodyDto);

    // * Delete exist post
    @DeleteMapping("/:{id}")
    public String removeBoard(@PathVariable Long id);
}
