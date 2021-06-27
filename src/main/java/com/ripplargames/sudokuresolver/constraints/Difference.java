package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;

public class Difference implements SudokuConstraint {
    private final Cell a;
    private final Cell b;
    private final int difference;

    public Difference(Cell a, Cell b, int difference) {
        Args.gte(difference, 0);
        this.a = a;
        this.b = b;
        this.difference = difference;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        sudoku.getModel().distance(sudoku.getCellAt(a), sudoku.getCellAt(b), "=", difference).post();
    }
}
