package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import lombok.AllArgsConstructor;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

@AllArgsConstructor
public class Consecutive implements SudokuConstraint {
  private final Cell a;
  private final Cell b;
  private final boolean cyclic;

  public Consecutive(Cell a, Cell b) {
    this(a, b, false);
  }

  @Override
  public void addConstraint(Constrained sudoku) {
    Model model = sudoku.getModel();
    IntVar modelA = sudoku.getCellAt(a);
    IntVar modelB = sudoku.getCellAt(b);
    Constraint consecutive = model.distance(modelA, modelB, "=", 1);
    if (cyclic) {
      Constraint cycle = model.distance(modelA, modelB, "=", sudoku.getGridLength() - 1);
      model.or(consecutive, cycle)
           .post();
    } else {
      consecutive.post();
    }
  }
}
