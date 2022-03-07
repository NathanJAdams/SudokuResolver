package com.ripplargames.sudokuresolver;

import com.ripplargames.sudokuresolver.listeners.CumulativeSolutionListener;

import java.util.Arrays;

public class FortyEleven {
    public static void main(String[] args) {
        boolean[][][] solution = new Sudoku(3)
                .standard()

                .kropkiWhite(Cell.ordinals(1, 1), OrthogonalDirection.S)
                .kropkiWhite(Cell.ordinals(1, 2), OrthogonalDirection.S)
                .kropkiWhite(Cell.ordinals(1, 8), OrthogonalDirection.S)
                .kropkiWhite(Cell.ordinals(1, 9), OrthogonalDirection.S)

                .arrow(Cell.ordinals(1, 5), DiagonalDirection.SW, OrthogonalDirection.W)
                .arrow(Cell.ordinals(1, 7), DiagonalDirection.SW, OrthogonalDirection.W)
                .arrow(Cell.ordinals(2, 7), DiagonalDirection.NW)
                .thermo(Cell.ordinals(1,3), OrthogonalDirection.E)

                .kropkiWhite(Cell.ordinals(5, 4), OrthogonalDirection.W)

                .palindrome(Cell.ordinals(3, 3),
                        OrthogonalDirection.E,
                        DiagonalDirection.SE,
                        DiagonalDirection.SW,
                        OrthogonalDirection.W,
                        OrthogonalDirection.N,
                        OrthogonalDirection.E)

                .thermo(Cell.ordinals(9, 3),
                        OrthogonalDirection.N,
                        OrthogonalDirection.N,
                        OrthogonalDirection.E,
                        OrthogonalDirection.S,
                        OrthogonalDirection.E,
                        DiagonalDirection.SW)

                .arrow(Cell.ordinals(6, 6), DiagonalDirection.SW, DiagonalDirection.SE, DiagonalDirection.SE)
                .arrow(Cell.ordinals(6, 7), OrthogonalDirection.S,OrthogonalDirection.E, DiagonalDirection.SW)
                .arrow(Cell.ordinals(6, 8), DiagonalDirection.SE, OrthogonalDirection.S)

                .thermo(Cell.ordinals(4, 2), OrthogonalDirection.S, OrthogonalDirection.S)
//                .given(Cell.ordinals(5, 2),3)

                .solve(new CumulativeSolutionListener(), 1);
        System.out.println(Arrays.toString(solution));
    }
}
