package com.boardapp.boardfe.board.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Board {
    private Long num;

    private String title;
    private String contents;

    private String writeName;
    private String modifyName;
    
    private Date writeDate;
    private Date modifyDate;
}
