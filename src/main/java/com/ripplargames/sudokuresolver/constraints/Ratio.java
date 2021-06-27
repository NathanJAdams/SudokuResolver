package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

public class Ratio implements SudokuConstraint {
    private final Cell a;
    private final Cell b;
    private final int ratio;

    public Ratio(Cell a, Cell b, int ratio) {
        Args.gt(ratio, 1);
        this.a = a;
        this.b = b;
        this.ratio = ratio;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        Model model = sudoku.getModel();
        IntVar _a = sudoku.getCellAt(a);
        IntVar _b = sudoku.getCellAt(b);
        IntVar _ratio = model.intVar(ratio);
        Constraint ab = model.times(_a, _ratio, _b);
        Constraint ba = model.times(_b, _ratio, _a);
        model.or(ab, ba).post();
    }
}
