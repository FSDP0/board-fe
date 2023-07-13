package com.boardapp.boardfe.board.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.boardapp.boardfe.board.client.BoardClient;
import com.boardapp.boardfe.board.model.Board;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    // ! Dependency injection
    private final BoardClient boardClient;

    public List<Board> getAllBoards() {
        return this.boardClient.getAllBoards();
    }

    public Board getByBoardId(Long boardId) {
        return this.boardClient.getByBoardId(boardId);
    }

    public String createBoard(Board boardDto) {
        return this.boardClient.createBoard(boardDto);
    }

    public String editBoard( Board boardDto) {
        return this.boardClient.editBoard(boardDto.getNum(), boardDto);
    }

    public String removeBoard(Long boardId) {
        return this.boardClient.removeBoard(boardId);
    }
}
