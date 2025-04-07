package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import lombok.AllArgsConstructor;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

@AllArgsConstructor
public class ConsecutivePairs implements SudokuConstraint {
  private final boolean rows;
  private final boolean columns;

  @Override
  public void addConstraint(Constrained sudoku) {
    Model model = sudoku.getModel();
    int gridLength = sudoku.getGridLength();
    if (rows) {
      for (int col = 0; col < gridLength; col++) {
        IntVar[] colVars = new IntVar[gridLength];
        for (int row = 0; row < gridLength; row++) {
          colVars[row] = sudoku.getCellAt(row, col);
        }
        model.allDifferent(colVars).post();
      }
    }
  }
}
