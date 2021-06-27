package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Given implements SudokuConstraint {
    private final Cell cell;
    private final int value;

    @Override
    public void addConstraint(Constrained sudoku) {
        sudoku.getModel().member(sudoku.getCellAt(cell), new int[]{value}).post();
    }
}
