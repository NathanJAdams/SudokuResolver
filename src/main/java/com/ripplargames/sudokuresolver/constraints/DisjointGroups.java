package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import lombok.AllArgsConstructor;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

@AllArgsConstructor
public class DisjointGroups implements SudokuConstraint {
    @Override
    public void addConstraint(Constrained sudoku) {
        int boxLength = sudoku.getBoxLength();
        int gridLength = sudoku.getGridLength();
        Model model = sudoku.getModel();
        for (int setRow = 0; setRow < boxLength; setRow++) {
            for (int setCol = 0; setCol < boxLength; setCol++) {
                IntVar[] disjointSet = new IntVar[gridLength];
                for (int rowBox = 0; rowBox < boxLength; rowBox++) {
                    for (int colBox = 0; colBox < boxLength; colBox++) {
                        int row = (rowBox * boxLength) + setRow;
                        int col = (colBox * boxLength) + setCol;
                        int setIndex = (rowBox * boxLength) + colBox;
                        disjointSet[setIndex] = sudoku.getCellAt(row, col);
                    }
                }
                model.allDifferent(disjointSet).post();
            }
        }
    }
}
