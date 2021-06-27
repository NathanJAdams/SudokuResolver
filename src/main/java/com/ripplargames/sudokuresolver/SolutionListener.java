package com.ripplargames.sudokuresolver;

public interface SolutionListener<T> {
    T blank(int boxLength, int gridLength);

    T combine(T accumulator, int boxLength, int gridLength, int[][] solution);

    String toString(T accumulator, int boxLength, int gridLength);
}
