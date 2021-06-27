package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;

public class Choice implements SudokuConstraint {
    private final Cell cell;
    private final int[] choices;

    public Choice(Cell cell, int... choices) {
        Args.gte(choices.length, 1);
        this.cell = cell;
        this.choices = choices;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        sudoku.getModel().member(sudoku.getCellAt(cell), choices).post();
    }
}
