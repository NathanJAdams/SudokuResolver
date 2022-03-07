package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

public class Quadruple implements SudokuConstraint {
    private final Cell topLeft;
    private final int[] possibilities;

    public Quadruple(Cell topLeft, int... possibilities) {
        Args.gte(possibilities.length, 1);
        this.topLeft = topLeft;
        this.possibilities = possibilities;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        int row = topLeft.getRow();
        int col = topLeft.getCol();
        IntVar nw = sudoku.getCellAt(row, col);
        IntVar ne = sudoku.getCellAt(row, col + 1);
        IntVar sw = sudoku.getCellAt(row + 1, col);
        IntVar se = sudoku.getCellAt(row + 1, col + 1);
        Model model = sudoku.getModel();
        Constraint[] constraints = new Constraint[possibilities.length];
        for (int i = 0; i < possibilities.length; i++) {
            int possibility = possibilities[i];
            Constraint nwConstraint = model.arithm(nw, "=", possibility);
            Constraint neConstraint = model.arithm(ne, "=", possibility);
            Constraint swConstraint = model.arithm(sw, "=", possibility);
            Constraint seConstraint = model.arithm(se, "=", possibility);
            constraints[i] = model.or(nwConstraint, neConstraint, swConstraint, seConstraint);
        }
        sudoku.getModel().and(constraints).post();
    }
}
