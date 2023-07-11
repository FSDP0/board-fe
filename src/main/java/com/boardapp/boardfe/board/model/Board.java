package com.boardapp.boardfe.board.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Board {
    private Long num;
    private String title;
    private String contents;
    private String writeName;
    private String modifyName;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date writeDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
