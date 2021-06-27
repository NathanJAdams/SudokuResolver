package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import lombok.AllArgsConstructor;
import org.chocosolver.solver.variables.IntVar;

@AllArgsConstructor
public class Diagonal implements SudokuConstraint {
    private final boolean negative;
    private final boolean positive;

    @Override
    public void addConstraint(Constrained sudoku) {
        if (negative) {
            constrain(sudoku, false);
        }
        if (positive) {
            constrain(sudoku, true);
        }
    }

    private void constrain(Constrained sudoku, boolean positive) {
        int gridLength = sudoku.getGridLength();
        IntVar[] diagonal = new IntVar[gridLength];
        for (int i = 0; i < gridLength; i++) {
            int col = positive
                    ? gridLength - i - 1
                    : i;
            diagonal[i] = sudoku.getCellAt(i, col);
        }
        sudoku.getModel().allDifferent(diagonal).post();
    }
}
