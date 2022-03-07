package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.constraints.Constraint;

public class Not implements SudokuConstraint {
    private final Cell cell;
    private final int[] values;

    public Not(Cell cell, int... values) {
        this.cell = cell;
        this.values = values;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        Constraint constraint = sudoku.getModel().member(sudoku.getCellAt(cell), values);
        sudoku.getModel().not(constraint).post();
    }
}
