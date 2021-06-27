package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.List;

public class Boxes implements SudokuConstraint {
    @Override
    public void addConstraint(Constrained sudoku) {
        Model model = sudoku.getModel();
        int gridLength = sudoku.getGridLength();
        int boxLength = sudoku.getBoxLength();
        List<IntVar[]> boxes = new ArrayList<>(gridLength);
        for (int box = 0; box < gridLength; box++) {
            boxes.add(new IntVar[gridLength]);
        }
        for (int row = 0; row < gridLength; row++) {
            for (int col = 0; col < gridLength; col++) {
                int boxIndex = ((row / boxLength) * boxLength) + (col / boxLength);
                int boxCellIndex = ((row % boxLength) * boxLength) + (col % boxLength);
                IntVar[] box = boxes.get(boxIndex);
                box[boxCellIndex] = sudoku.getCellAt(row, col);
            }
        }
        boxes.forEach(box -> model.allDifferent(box).post());
    }
}
