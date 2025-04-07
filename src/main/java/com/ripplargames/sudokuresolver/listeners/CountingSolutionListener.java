package com.ripplargames.sudokuresolver.listeners;

import com.ripplargames.sudokuresolver.SolutionListener;

public class CountingSolutionListener implements SolutionListener<Long> {
  @Override
  public Long blank(int boxLength, int gridLength) {
    return 0L;
  }

  @Override
  public Long combine(Long accumulator, int boxLength, int gridLength, int[][] solution) {
    return accumulator + 1;
  }

  @Override
  public String toString(Long accumulator, int boxLength, int gridLength) {
    return String.valueOf(accumulator);
  }
}
