package com.boardapp.boardfe.board.service;

import java.util.List;
import com.boardapp.boardfe.board.model.Board;

public interface BoardService {
    List<Board> getAllBoards();

    Board getByBoardId(Long boardId);

    String createBoard(Board boardDto);

    String editBoard(Board boardDto);

    String removeBoard(Long boardId);
}
