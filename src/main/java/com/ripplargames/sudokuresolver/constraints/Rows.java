package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class Rows implements SudokuConstraint {
    @Override
    public void addConstraint(Constrained sudoku) {
        Model model = sudoku.getModel();
        int gridLength = sudoku.getGridLength();
        for (int row = 0; row < gridLength; row++) {
            IntVar[] rowVars = new IntVar[gridLength];
            for (int col = 0; col < gridLength; col++) {
                rowVars[col] = sudoku.getCellAt(row, col);
            }
            model.allDifferent(rowVars).post();
        }
    }
}
