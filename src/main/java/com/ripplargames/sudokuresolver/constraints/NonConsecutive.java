package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;

public class NonConsecutive implements SudokuConstraint {
    @Override
    public void addConstraint(Constrained sudoku) {
        int gridLength = sudoku.getGridLength();
        for (int row = 0; row < gridLength; row++) {
            for (int col = 0; col < gridLength; col++) {
                constrain(sudoku, row, col, -1, 0);
                constrain(sudoku, row, col, 1, 0);
                constrain(sudoku, row, col, 0, -1);
                constrain(sudoku, row, col, 0, 1);
            }
        }
    }

    private void constrain(Constrained sudoku, int row, int col, int offsetRow, int offsetCol) {
        int gridLength = sudoku.getGridLength();
        int newRow = row + offsetRow;
        int newCol = col + offsetCol;
        if ((newRow >= 0) && (newRow < gridLength) && (newCol >= 0) && (newCol < gridLength)) {
            sudoku.getModel().distance(sudoku.getCellAt(row, col), sudoku.getCellAt(newRow, newCol), ">", 1).post();
        }
    }
}
