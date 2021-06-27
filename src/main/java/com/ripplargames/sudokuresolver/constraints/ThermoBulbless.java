package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;

public class ThermoBulbless implements SudokuConstraint {
    private final Cell[] line;

    public ThermoBulbless(Cell... line) {
        Args.gte(line.length, 2);
        this.line = line;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        Model model = sudoku.getModel();
        Constraint[] up = new Constraint[line.length - 1];
        Constraint[] down = new Constraint[line.length - 1];
        for (int i = 0; i < line.length - 1; i++) {
            IntVar current = sudoku.getCellAt(line[i]);
            IntVar next = sudoku.getCellAt(line[i + 1]);
            up[i] = model.arithm(current, "<", next);
            down[i] = model.arithm(current, ">", next);
        }
        Constraint allUp = model.and(up);
        Constraint allDown = model.and(down);
        model.or(allUp, allDown).post();
    }
}
