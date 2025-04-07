package com.ripplargames.sudokuresolver;

import com.ripplargames.sudokuresolver.listeners.CountingSolutionListener;

public class Test {
  public static void main(String[] args) {
    new Sudoku(3)
      .standard()

      .thermo(Cell.ordinals(1, 1), OrthogonalDirection.E, OrthogonalDirection.E, OrthogonalDirection.E, OrthogonalDirection.E, OrthogonalDirection.E, OrthogonalDirection.E, OrthogonalDirection.E, OrthogonalDirection.E)

      .solve(new CountingSolutionListener(), 10_000);
  }
}
