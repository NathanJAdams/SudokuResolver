package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EQ implements SudokuConstraint {
    private final Cell a;
    private final Cell b;

    @Override
    public void addConstraint(Constrained sudoku) {
        sudoku.getModel().arithm(sudoku.getCellAt(a), "=", sudoku.getCellAt(b)).post();
    }
}
