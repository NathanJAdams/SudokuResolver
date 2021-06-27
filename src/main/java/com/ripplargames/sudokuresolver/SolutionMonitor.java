package com.ripplargames.sudokuresolver;

import lombok.Getter;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.variables.IntVar;

public class SolutionMonitor<T> implements IMonitorSolution {
    private final int boxLength;
    private final int gridLength;
    private final IntVar[][] grid;
    private final SolutionListener<T> listener;
    private final int printIteration;
    @Getter
    private T accumulator;
    private int iteration;

    public SolutionMonitor(int boxLength, int gridLength, IntVar[][] grid, SolutionListener<T> listener, int printIteration) {
        this.boxLength = boxLength;
        this.gridLength = gridLength;
        this.grid = grid;
        this.listener = listener;
        this.accumulator = listener.blank(boxLength, gridLength);
        this.printIteration = printIteration;
    }

    @Override
    public void onSolution() {
        iteration++;
        int[][] solution = new int[gridLength][gridLength];
        for (int row = 0; row < gridLength; row++) {
            for (int col = 0; col < gridLength; col++) {
                solution[row][col] = grid[row][col].getValue();
            }
        }
        this.accumulator = listener.combine(this.accumulator, boxLength, gridLength, solution);
        if (iteration >= printIteration) {
            System.out.println("");
            System.out.println("=====");
            System.out.println(listener.toString(accumulator, boxLength, gridLength));
            System.out.println("=====");
            iteration = 0;
        }
    }
}
