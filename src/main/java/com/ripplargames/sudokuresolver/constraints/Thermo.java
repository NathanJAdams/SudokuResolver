package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;

public class Thermo implements SudokuConstraint {
    private final Cell bulb;
    private final Cell[] line;

    public Thermo(Cell bulb, Cell... line) {
        Args.gte(line.length, 1);
        this.bulb = bulb;
        this.line = line;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        constrain(sudoku, bulb, line[0]);
        sudoku.getModel().arithm(sudoku.getCellAt(bulb), "<", sudoku.getCellAt(line[0])).post();
        for (int i = 1; i < line.length; i++) {
            constrain(sudoku, line[i - 1], line[i]);
        }
    }

    private void constrain(Constrained sudoku, Cell a, Cell b) {
        sudoku.getModel().arithm(sudoku.getCellAt(a), "<", sudoku.getCellAt(b)).post();
    }
}
