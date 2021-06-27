package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;

public class Palindrome implements SudokuConstraint {
    private final Cell[] line;

    public Palindrome(Cell... line) {
        Args.gte(line.length, 2);
        this.line = line;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        int halfLength = line.length / 2;
        for (int i = 0; i < halfLength; i++) {
            Cell a = line[i];
            Cell b = line[line.length - 1 - i];
            sudoku.getModel().allEqual(sudoku.getCellAt(a), sudoku.getCellAt(b)).post();
        }
    }
}
