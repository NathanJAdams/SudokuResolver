package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Parity implements SudokuConstraint {
    private static final int[] ODDS = new int[]{1, 3, 5, 7, 9};
    private static final int[] EVENS = new int[]{2, 4, 6, 8};

    private final Cell cell;
    private final boolean isOdd;

    @Override
    public void addConstraint(Constrained sudoku) {
        int[] group = isOdd
                ? ODDS
                : EVENS;
        sudoku.getModel().member(sudoku.getCellAt(cell), group).post();
    }
}
