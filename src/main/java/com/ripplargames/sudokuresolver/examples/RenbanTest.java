package com.ripplargames.sudokuresolver.examples;

import com.ripplargames.sudokuresolver.Cell;
import com.ripplargames.sudokuresolver.OrthogonalDirection;
import com.ripplargames.sudokuresolver.Sudoku;
import com.ripplargames.sudokuresolver.listeners.NoOpSolutionListener;

public class RenbanTest {
    public static void main(String[] args) {
        new Sudoku(3)
                .renban(Cell.ordinals(1, 1), OrthogonalDirection.E, OrthogonalDirection.E)
                .solve(new NoOpSolutionListener(), 1);
    }
}
