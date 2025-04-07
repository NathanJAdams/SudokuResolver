package com.ripplargames.sudokuresolver;

import com.ripplargames.sudokuresolver.listeners.CumulativeSolutionListener;
import com.ripplargames.sudokuresolver.listeners.NoOpSolutionListener;

public class AllTimeFavourite {
  public static void main(String[] args) {
    new Sudoku(3)
      .standard()
      .antiKing()
      .solve(new NoOpSolutionListener());
  }
}
