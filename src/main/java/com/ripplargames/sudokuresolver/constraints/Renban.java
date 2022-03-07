package com.ripplargames.sudokuresolver.constraints;

import com.ripplargames.sudokuresolver.Args;
import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.Constrained;
import com.ripplargames.sudokuresolver.SudokuConstraint;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.nary.circuit.CircuitConf;
import org.chocosolver.solver.variables.IntVar;

import java.util.Arrays;

public class Renban implements SudokuConstraint {
    private final Cell[] line;

    public Renban(Cell... line) {
        Args.gte(line.length, 2);
        this.line = line;
    }

    @Override
    public void addConstraint(Constrained sudoku) {
        Model model = sudoku.getModel();
        IntVar[] cells = Arrays.stream(line)
                .map(sudoku::getCellAt)
                .toArray(IntVar[]::new);
        System.out.println(cells.length);
        int possibilityCount = sudoku.getGridLength() + 1 - line.length;
        Constraint[] constraints = new Constraint[possibilityCount];
        for (int i = 0; i < possibilityCount; i++) {
            System.out.println("offset: " + i);
            constraints[i] = model.circuit(cells, i, CircuitConf.LIGHT);
        }
        model.or(constraints).post();
    }
}
