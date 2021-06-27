package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.variables.IntVar;

import java.util.Arrays;
import java.util.List;

public class Sum implements SudokuConstraint {
    private final List<Cell> cells;
    private final int sum;

    public Sum(int sum, Cell... cells) {
        this(Arrays.asList(cells), sum);
    }

    public Sum(List<Cell> cells, int sum) {
        Args.gte(cells.size(), 1);
        this.cells = cells;
        this.sum = sum;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        IntVar[] array = cells.stream()
                .map(sudoku::getCellAt)
                .toArray(IntVar[]::new);
        sudoku.getModel().sum(array, "=", sum).post();
    }
}
