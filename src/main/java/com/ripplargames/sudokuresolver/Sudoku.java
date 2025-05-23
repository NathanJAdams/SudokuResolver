package com.ripplargames.sudokuresolver;

import com.ripplargames.sudokuresolver.constraints.*;
import com.ripplargames.sudokuresolver.listeners.NoOpSolutionListener;
import lombok.Getter;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.HashSet;
import java.util.Set;

public class Sudoku implements Constrained {
  @Getter
  private final int boxLength;
  @Getter
  private final int gridLength;
  private final IntVar[][] grid;
  @Getter
  private final Model model;

  public Sudoku(int boxLength) {
    Args.betweenInc(boxLength, 2, 3);
    this.boxLength = boxLength;
    this.gridLength = boxLength * boxLength;
    this.grid = new IntVar[gridLength][gridLength];
    this.model = new Model();
    for (int row = 0; row < gridLength; row++) {
      for (int col = 0; col < gridLength; col++) {
        grid[row][col] = model.intVar(row + "_" + col, 1, gridLength, false);
      }
    }
  }

  //  public static void main(String[] args) {
  //    new Sudoku(3)
  //      .standard()
  //      .antiKnight()
  //
  //      // key
  //      .given(Cell.ordinals(8, 8), 5)
  //      //                .given(Cell.ordinals(9, 8), 6)
  //      .thermo(Cell.ordinals(8, 8), OrthogonalDirection.S)
  //
  //      // crosses
  //      .thermoBulbless(Cell.ordinals(1, 6), DiagonalDirection.SE, DiagonalDirection.SE, DiagonalDirection.SE)
  //      .thermoBulbless(Cell.ordinals(1, 9), DiagonalDirection.SW, DiagonalDirection.SW, DiagonalDirection.SW)
  //      .thermoBulbless(Cell.ordinals(9, 1), DiagonalDirection.NE, DiagonalDirection.NE, DiagonalDirection.NE)
  //      .thermoBulbless(Cell.ordinals(9, 4), DiagonalDirection.NW, DiagonalDirection.NW, DiagonalDirection.NW)
  //
  //      //
  //      //                .kropkiWhite(Cell.ordinals(1, 6), OrthogonalDirection.S)
  //      .kropkiWhite(Cell.ordinals(1, 6), OrthogonalDirection.E)
  //      .kropkiWhite(Cell.ordinals(3, 9), OrthogonalDirection.S)
  //      //                .kropkiWhite(Cell.ordinals(3, 9), OrthogonalDirection.W)
  //      .kropkiWhite(Cell.ordinals(6, 1), OrthogonalDirection.S)
  //      //                .kropkiWhite(Cell.ordinals(6, 1), OrthogonalDirection.E)
  //      //                .kropkiWhite(Cell.ordinals(9, 3), OrthogonalDirection.N)
  //      .kropkiWhite(Cell.ordinals(9, 3), OrthogonalDirection.E)
  //      .solve(new CumulativeSolutionListener(), 1);
  //    //                .solve();
  //  }
  //  public static void main(String[] args) {
  //    new Sudoku(3)
  //      .standard()
  //      .antiKnight()
  //
  //      // key
  //      .given(Cell.ordinals(8, 8), 5)
  //      //      .thermo(Cell.ordinals(8, 8), OrthogonalDirection.S)
  //
  //      // cross
  //      .thermoBulbless(Cell.ordinals(1, 1), DiagonalDirection.SE, DiagonalDirection.SE, DiagonalDirection.SE)
  //      .thermoBulbless(Cell.ordinals(1, 4), DiagonalDirection.SW, DiagonalDirection.SW, DiagonalDirection.SW)
  //
  //      // cross kropki NW
  //      .kropkiWhite(Cell.ordinals(1, 3), OrthogonalDirection.S)
  //      .kropkiWhite(Cell.ordinals(3, 1), OrthogonalDirection.E)
  //
  //      // corner kropki
  //      .kropkiWhite(Cell.ordinals(4, 3), OrthogonalDirection.E)
  //      .kropkiWhite(Cell.ordinals(3, 4), OrthogonalDirection.S)
  //
  //      // wings
  //      .thermoBulbless(Cell.ordinals(4, 2), DiagonalDirection.SW, DiagonalDirection.SE, DiagonalDirection.SE)
  //      .thermoBulbless(Cell.ordinals(2, 4), DiagonalDirection.NE, DiagonalDirection.SE, DiagonalDirection.SE)
  //
  //      .solve(new NoOpSolutionListener(), 1);
  //    //      .solve(new CumulativeSolutionListener(), 1);
  //  }
  public static void main(String[] args) {
    new Sudoku(3)
      .standard()
      .antiKnight()

      // key
      .given(Cell.ordinals(8, 8), 5)
      .lt(Cell.ordinals(8, 8), OrthogonalDirection.S)
      //      .thermo(Cell.ordinals(8, 8), OrthogonalDirection.S)

      // cross
      .thermoBulbless(Cell.ordinals(2, 2), DiagonalDirection.SE, DiagonalDirection.SE, DiagonalDirection.SE, DiagonalDirection.SE)
      .thermoBulbless(Cell.ordinals(3, 6), DiagonalDirection.SW, DiagonalDirection.SW, DiagonalDirection.SW)
      .thermoBulbless(Cell.ordinals(2, 5), DiagonalDirection.SW, DiagonalDirection.SW, DiagonalDirection.SW)

      // kropki box 1
      .kropkiWhiteCyclic(Cell.ordinals(3, 3), OrthogonalDirection.S)
      .kropkiWhiteCyclic(Cell.ordinals(3, 3), OrthogonalDirection.E)

      // kropki box 5
      .kropkiWhiteCyclic(Cell.ordinals(4, 5), OrthogonalDirection.E)
      .kropkiWhiteCyclic(Cell.ordinals(5, 4), OrthogonalDirection.S)

      // tails
      .thermoBulbless(Cell.ordinals(4, 6), DiagonalDirection.SE, DiagonalDirection.SE, DiagonalDirection.SE)
      .thermoBulbless(Cell.ordinals(6, 4), DiagonalDirection.SE, DiagonalDirection.SE, DiagonalDirection.SE)

      //       kropki sides
      //      .kropkiWhiteCyclic(Cell.ordinals(1, 2), OrthogonalDirection.E)
      //      .kropkiWhiteCyclic(Cell.ordinals(1, 4), OrthogonalDirection.E)
      //      .kropkiWhiteCyclic(Cell.ordinals(2, 1), OrthogonalDirection.S)
      //      .kropkiWhiteCyclic(Cell.ordinals(4, 1), OrthogonalDirection.S)

      // wings
      //      .thermoBulbless(Cell.ordinals(1, 9), OrthogonalDirection.W, DiagonalDirection.SW)
      //      .thermoBulbless(Cell.ordinals(9, 1), OrthogonalDirection.N, DiagonalDirection.NE)

      // wings 2
      //      .thermoBulbless(Cell.ordinals(2, 7), DiagonalDirection.NE, DiagonalDirection.SE)
      //      .thermoBulbless(Cell.ordinals(7, 2), DiagonalDirection.SW, DiagonalDirection.SE)

      //       kropki box 3,7
      .kropkiWhiteCyclic(Cell.ordinals(3, 7), OrthogonalDirection.S)
      .kropkiWhiteCyclic(Cell.ordinals(7, 3), OrthogonalDirection.E)

      //       kropki box 9
      .kropkiWhiteCyclic(Cell.ordinals(7, 7), OrthogonalDirection.N)
      .kropkiWhiteCyclic(Cell.ordinals(7, 7), OrthogonalDirection.W)

      .solve(new NoOpSolutionListener(), 1);
    //              .solve(new CumulativeSolutionListener(), Integer.MAX_VALUE);
  }

  public <T> T solve(SolutionListener<T> listener) {
    return solve(listener, Long.MAX_VALUE);
  }

  public <T> T solve(SolutionListener<T> listener, long printIteration) {
    model.getSolver()
         .unplugAllSearchMonitors();
    SolutionMonitor<T> monitor = new SolutionMonitor<>(boxLength, gridLength, grid, listener, printIteration);
    model.getSolver()
         .plugMonitor(monitor);
    while (model.getSolver()
                .solve())
      ;
    T accumulator = monitor.getAccumulator();
    System.out.println(listener.toString(accumulator, boxLength, gridLength));
    return monitor.getAccumulator();
  }

  @Override
  public IntVar getCellAt(int row, int col) {
    return grid[row][col];
  }

  public Sudoku rows() {
    new Rows().addConstraint(this);
    return this;
  }

  public Sudoku columns() {
    new Columns().addConstraint(this);
    return this;
  }

  public Sudoku boxes() {
    new Boxes().addConstraint(this);
    return this;
  }

  public Sudoku standard() {
    return rows().columns()
                 .boxes();
  }

  public Sudoku given(Cell cell, int value) {
    new Given(cell, value).addConstraint(this);
    return this;
  }

  public Sudoku choice(Cell cell, int... choices) {
    Args.gte(choices.length, 1);
    new Choice(cell, choices).addConstraint(this);
    return this;
  }

  public Sudoku diagonal(boolean negative, boolean positive) {
    new Diagonal(negative, positive).addConstraint(this);
    return this;
  }

  public Sudoku antiKing() {
    new AntiKing().addConstraint(this);
    return this;
  }

  public Sudoku antiKnight() {
    new AntiKnight().addConstraint(this);
    return this;
  }

  public Sudoku nonConsecutive() {
    new NonConsecutive().addConstraint(this);
    return this;
  }

  public Sudoku disjointGroups() {
    new DisjointGroups().addConstraint(this);
    return this;
  }

  public Sudoku parity(Cell cell, boolean isOdd) {
    new Parity(cell, isOdd).addConstraint(this);
    return this;
  }

  public Sudoku quadruple(Cell topLeft, int... possibilities) {
    Args.gte(possibilities.length, 1);
    new Quadruple(topLeft, possibilities).addConstraint(this);
    return this;
  }

  public Sudoku v(Cell cell, OrthogonalDirection direction) {
    return sum(5, cell, cell.inDirection(direction));
  }

  public Sudoku x(Cell cell, OrthogonalDirection direction) {
    return sum(10, cell, cell.inDirection(direction));
  }

  public Sudoku killerCage(int sum, Cell cell, OrthogonalDirection... directions) {
    Set<Cell> cells = new HashSet<>();
    cells.add(cell);
    for (OrthogonalDirection direction : directions) {
      cell = cell.inDirection(direction);
      cells.add(cell);
    }
    return sum(sum, cells.toArray(new Cell[0]));
  }

  public Sudoku littleKiller(int column, DiagonalDirection direction, int sum) {
    Args.betweenInc(column, 1, gridLength);
    int row = direction.isS()
      ? 1
      : gridLength;
    Cell cell = Cell.ordinals(row, column);
    int count = direction.isW()
      ? column
      : gridLength - column + 1;
    Cell[] cells = new Cell[count];
    cells[0] = cell;
    for (int i = 1; i < count; i++) {
      cells[i] = cells[i - 1].inDirection(direction);
    }
    return sum(sum, cells);
  }

  public Sudoku sum(int sum, Cell... cells) {
    Args.gte(cells.length, 1);
    new Sum(sum, cells).addConstraint(this);
    return this;
  }

  public Sudoku gt(Cell a, OrthogonalDirection directional) {
    new GT(a, a.inDirection(directional)).addConstraint(this);
    return this;
  }

  public Sudoku lt(Cell a, OrthogonalDirection directional) {
    new LT(a, a.inDirection(directional)).addConstraint(this);
    return this;
  }

  public Sudoku eq(Cell a, Cell b) {
    new EQ(a, b).addConstraint(this);
    return this;
  }

  public Sudoku not(Cell a, int... values) {
    new Not(a, values).addConstraint(this);
    return this;
  }

  public Sudoku kropkiWhite(Cell a, OrthogonalDirection direction) {
    new Consecutive(a, a.inDirection(direction)).addConstraint(this);
    return this;
  }

  public Sudoku kropkiWhiteCyclic(Cell a, OrthogonalDirection direction) {
    new Consecutive(a, a.inDirection(direction), true).addConstraint(this);
    return this;
  }

  public Sudoku kropkiBlack(Cell a, OrthogonalDirection direction) {
    return ratio(a, a.inDirection(direction), 2);
  }

  public Sudoku difference(Cell a, Cell b, int difference) {
    Args.gte(difference, 0);
    new Difference(a, b, difference).addConstraint(this);
    return this;
  }

  public Sudoku ratio(Cell a, Cell b, int ratio) {
    Args.gt(ratio, 1);
    new Ratio(a, b, ratio).addConstraint(this);
    return this;
  }

  public Sudoku arrow(Cell head, Directional... directionals) {
    Args.gte(directionals.length, 1);
    Cell[] line = new Cell[directionals.length];
    line[0] = head.inDirection(directionals[0]);
    for (int i = 1; i < directionals.length; i++) {
      line[i] = line[i - 1].inDirection(directionals[i]);
    }
    new Arrow(head, line).addConstraint(this);
    return this;
  }

  public Sudoku palindrome(Cell start, Directional... directionals) {
    Args.gte(directionals.length, 1);
    Cell[] line = new Cell[directionals.length + 1];
    line[0] = start;
    for (int i = 0; i < directionals.length; i++) {
      line[i + 1] = line[i].inDirection(directionals[i]);
    }
    new Palindrome(line).addConstraint(this);
    return this;
  }

  public Sudoku renban(Cell start, Directional... directionals) {
    Args.gte(directionals.length, 1);
    Cell[] line = new Cell[directionals.length + 1];
    line[0] = start;
    for (int i = 0; i < directionals.length; i++) {
      line[i + 1] = line[i].inDirection(directionals[i]);
    }
    new Renban(line).addConstraint(this);
    return this;
  }

  public Sudoku thermo(Cell bulb, Directional... directionals) {
    Args.gte(directionals.length, 1);
    Cell[] line = new Cell[directionals.length];
    line[0] = bulb.inDirection(directionals[0]);
    for (int i = 1; i < directionals.length; i++) {
      line[i] = line[i - 1].inDirection(directionals[i]);
    }
    new Thermo(bulb, line).addConstraint(this);
    return this;
  }

  public Sudoku thermoBulbless(Cell start, Directional... directionals) {
    Args.gte(directionals.length, 1);
    Cell[] line = new Cell[directionals.length + 1];
    line[0] = start;
    for (int i = 0; i < directionals.length; i++) {
      line[i + 1] = line[i].inDirection(directionals[i]);
    }
    new ThermoBulbless(line).addConstraint(this);
    return this;
  }
}
