package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.Model;

public class AntiKnight implements SudokuConstraint {
    @Override
    public void addConstraint(Constrained sudoku) {
        Model model = sudoku.getModel();
        int gridLength = sudoku.getGridLength();
        for (int row = 0; row < gridLength; row++) {
            for (int col = 0; col < gridLength; col++) {
                constrain(sudoku, model, gridLength, row, col, -2, 1);
                constrain(sudoku, model, gridLength, row, col, -1, 2);
                constrain(sudoku, model, gridLength, row, col, 1, 2);
                constrain(sudoku, model, gridLength, row, col, 2, 1);
                constrain(sudoku, model, gridLength, row, col, 2, -1);
                constrain(sudoku, model, gridLength, row, col, 1, -2);
                constrain(sudoku, model, gridLength, row, col, -1, -2);
                constrain(sudoku, model, gridLength, row, col, -2, -1);
            }
        }
    }

    private void constrain(Constrained sudoku, Model model, int gridLength, int row, int col, int offsetRow, int offsetCol) {
        int newRow = row + offsetRow;
        int newCol = col + offsetCol;
        if ((newRow >= 0) && (newRow < gridLength) && (newCol >= 0) && (newCol < gridLength)) {
            model.allDifferent(sudoku.getCellAt(row, col), sudoku.getCellAt(newRow, newCol)).post();
        }
    }
}
