package com.ripplargames.sudokuresolver;

import com.ripplargames.sudokuresolver.listeners.NoOpSolutionListener;

public class AllTimeFavourite {
  public static void main(String[] args) {
    new Sudoku(3)
      .standard()
      .diagonal(true, false)
      .killerCage(21, Cell.ordinals(1,2),OrthogonalDirection.E,OrthogonalDirection.S)
      .killerCage(21, Cell.ordinals(9,8),OrthogonalDirection.W,OrthogonalDirection.N)
      .killerCage(18, Cell.ordinals(5,2),OrthogonalDirection.W,OrthogonalDirection.S,OrthogonalDirection.S)
      .killerCage(17, Cell.ordinals(5,8),OrthogonalDirection.E,OrthogonalDirection.N,OrthogonalDirection.N)
      .arrow(Cell.ordinals(4,5),DiagonalDirection.NW,OrthogonalDirection.N,OrthogonalDirection.N)
      .arrow(Cell.ordinals(6,5),DiagonalDirection.SE,OrthogonalDirection.S,OrthogonalDirection.S)
      .arrow(Cell.ordinals(7,9),DiagonalDirection.NW,DiagonalDirection.NW)
      .thermo(Cell.ordinals(5,3),DiagonalDirection.NW,DiagonalDirection.NW)
      .thermo(Cell.ordinals(3,8),OrthogonalDirection.N,OrthogonalDirection.E)
      .solve(new NoOpSolutionListener());
  }
}
