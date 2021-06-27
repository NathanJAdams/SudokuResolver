package com.ripplargames.sudokuresolver;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
@ToString
public class Cell {
    private final int row;
    private final int col;

    public static Cell ordinals(int row, int col) {
        return indexes(row - 1, col - 1);
    }

    public static Cell indexes(int row, int col) {
        Args.gte(row, 0);
        Args.gte(col, 0);
        return new Cell(row, col);
    }

    public Cell inDirection(Directional directional) {
        int newRow = row + directional.getRowOffset();
        int newCol = col + directional.getColOffset();
        return new Cell(newRow, newCol);
    }
}
