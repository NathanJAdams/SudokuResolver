package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.variables.IntVar;

import java.util.Arrays;

public class Arrow implements SudokuConstraint {
    private final Cell head;
    private final Cell[] line;

    public Arrow(Cell head, Cell... line) {
        Args.gte(line.length, 1);
        this.head = head;
        this.line = line;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        IntVar[] sum = Arrays.stream(line)
                .map(sudoku::getCellAt)
                .toArray(IntVar[]::new);
        sudoku.getModel().sum(sum, "=", sudoku.getCellAt(head)).post();
    }
}
