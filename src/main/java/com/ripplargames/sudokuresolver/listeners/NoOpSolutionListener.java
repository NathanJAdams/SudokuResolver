package com.ripplargames.sudokuresolver.listeners;

import com.ripplargames.sudokuresolver.SolutionListener;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoOpSolutionListener implements SolutionListener<int[][]> {
    @Override
    public int[][] blank(int boxLength, int gridLength) {
        return new int[gridLength][gridLength];
    }

    @Override
    public int[][] combine(int[][] accumulator, int boxLength, int gridLength, int[][] solution) {
        return solution;
    }

    @Override
    public String toString(int[][] accumulator, int boxLength, int gridLength) {
        StringBuilder sb = new StringBuilder();
        for (int rowBox = 0; rowBox < boxLength; rowBox++) {
            for (int rowInner = 0; rowInner < boxLength; rowInner++) {
                int rowIndex = rowBox * boxLength + rowInner;
                for (int colBox = 0; colBox < boxLength; colBox++) {
                    for (int colInner = 0; colInner < boxLength; colInner++) {
                        int colIndex = colBox * boxLength + colInner;
                        int value = accumulator[rowIndex][colIndex];
                        if (value == 0) {
                            sb.append(' ');
                        } else {
                            sb.append(value);
                        }
                    }
                    sb.append('|');
                }
                decrement(sb);
                sb.append('\n');
            }
            for (int colBox = 0; colBox < boxLength; colBox++) {
                for (int colInner = 0; colInner < boxLength; colInner++) {
                    sb.append('-');
                }
                sb.append('+');
            }
            decrement(sb);
            sb.append('\n');
        }
        decrement(sb, gridLength + boxLength + 1);
        return sb.toString();
    }

    private void decrement(StringBuilder sb) {
        decrement(sb, 1);
    }

    private void decrement(StringBuilder sb, int count) {
        sb.setLength(sb.length() - count);
    }
}
