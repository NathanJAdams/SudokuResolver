package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.Model;

public class AntiKing implements SudokuConstraint {
    @Override
    public void addConstraint(Constrained sudoku) {
        Model model = sudoku.getModel();
        int boxLength = sudoku.getBoxLength();
        int gridLength = sudoku.getGridLength();
        for (int box = 0; box < boxLength - 1; box++) {
            int boxIndex = (box + 1) * boxLength - 1;
            for (int gridIndex = 0; gridIndex < gridLength; gridIndex++) {
                if (gridIndex > 0) {
                    model.allDifferent(sudoku.getCellAt(boxIndex, gridIndex), sudoku.getCellAt(boxIndex + 1, gridIndex - 1)).post();
                    model.allDifferent(sudoku.getCellAt(gridIndex, boxIndex), sudoku.getCellAt(gridIndex - 1, boxIndex + 1)).post();
                }
                if (gridIndex < (gridLength - 1)) {
                    model.allDifferent(sudoku.getCellAt(boxIndex, gridIndex), sudoku.getCellAt(boxIndex + 1, gridIndex + 1)).post();
                    model.allDifferent(sudoku.getCellAt(gridIndex, boxIndex), sudoku.getCellAt(gridIndex + 1, boxIndex + 1)).post();
                }
            }
        }
    }
}
