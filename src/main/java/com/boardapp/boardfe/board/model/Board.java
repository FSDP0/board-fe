package com.boardapp.boardfe.board.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Board {
    private Long num;
    private String title;
    private String contents;
    private String writeName;
    private String modifyName;
    private Date writeDate;
    private Date modifyDate;

    @Builder
    public Board(Long num, String title, String contents, String writeName, String modifyName,
            Date writeDate, Date modifyDate) {
        this.num = num;
        this.title = title;
        this.contents = contents;
        this.writeName = writeName;
        this.modifyName = modifyName;
        this.writeDate = writeDate;
        this.modifyDate = modifyDate;
    }
}
