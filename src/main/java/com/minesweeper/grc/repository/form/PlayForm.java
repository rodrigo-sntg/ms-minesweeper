package com.minesweeper.grc.repository.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayForm {

    String boardCode;
    int row;
    int col;
    boolean isFlagging; // if false then it is normal click
}
