package com.ripplargames.sudokuresolver.listeners;

import com.ripplargames.sudokuresolver.SolutionListener;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CumulativeSolutionListener implements SolutionListener<boolean[][][]> {
    @Override
    public boolean[][][] blank(int boxLength, int gridLength) {
        return new boolean[gridLength][gridLength][gridLength];
    }

    @Override
    public boolean[][][] combine(boolean[][][] accumulator, int boxLength, int gridLength, int[][] solution) {
        for (int row = 0; row < gridLength; row++) {
            for (int col = 0; col < gridLength; col++) {
                int value = solution[row][col];
                accumulator[row][col][value - 1] = true;
            }
        }
        return accumulator;
    }

    @Override
    public String toString(boolean[][][] accumulator, int boxLength, int gridLength) {
        StringBuilder sb = new StringBuilder();
        for (int rowBox = 0; rowBox < boxLength; rowBox++) {
            for (int rowInner = 0; rowInner < boxLength; rowInner++) {
                int rowIndex = rowBox * boxLength + rowInner;
                for (int rowValueIndex = 0; rowValueIndex < boxLength; rowValueIndex++) {
                    for (int colBox = 0; colBox < boxLength; colBox++) {
                        for (int colInner = 0; colInner < boxLength; colInner++) {
                            int colIndex = colBox * boxLength + colInner;
                            for (int colValueIndex = 0; colValueIndex < boxLength; colValueIndex++) {
                                int valueIndex = rowValueIndex * boxLength + colValueIndex;
                                if (accumulator[rowIndex][colIndex][valueIndex]) {
                                    sb.append(valueIndex + 1);
                                } else {
                                    sb.append(' ');
                                }
                            }
                            sb.append(' ');
                        }
                        decrement(sb);
                        sb.append(" | ");
                    }
                    decrement(sb, 3);
                    sb.append('\n');
                }
                for (int colBox = 0; colBox < boxLength; colBox++) {
                    for (int colInner = 0; colInner < boxLength; colInner++) {
                        for (int valueIndex = 0; valueIndex < boxLength; valueIndex++) {
                            sb.append((rowInner == boxLength - 1) ? '-' : ' ');
                        }
                        sb.append((rowInner == boxLength - 1) ? '-' : ' ');
                    }
                    decrement(sb);
                    sb.append((rowInner == boxLength - 1) ? "-+-" : " | ");
                }
                decrement(sb, 3);
                sb.append('\n');
            }
        }
        decrement(sb, (boxLength + 1) * gridLength + (2 * (boxLength - 1)) + 1);

        return sb.toString();
    }

    private void decrement(StringBuilder sb) {
        decrement(sb, 1);
    }

    private void decrement(StringBuilder sb, int count) {
        sb.setLength(sb.length() - count);
    }
}
