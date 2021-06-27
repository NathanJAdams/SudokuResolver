package com.ripplargames.sudokuresolver;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public interface Constrained {
    Model getModel();

    int getBoxLength();

    int getGridLength();

    default IntVar getCellAt(Cell cell) {
        return getCellAt(cell.getRow(), cell.getCol());
    }

    IntVar getCellAt(int row, int col);
}
