package com.ripplargames.sudokuresolver;

import java.util.Arrays;

public class Args {
    public static void index(int index, int length) {
        if ((index < 0) || (index >= length)) {
            throw new IllegalArgumentException("Index: " + index + " must be between 0 and " + length);
        }
    }

    public static void gt(int value, int minExc) {
        if (value <= minExc) {
            throw new IllegalArgumentException("Value: " + value + " must be greater than " + minExc);
        }
    }

    public static void gte(int value, int minInc) {
        if (value < minInc) {
            throw new IllegalArgumentException("Value: " + value + " must be greater than or equal to " + minInc);
        }
    }

    public static void lt(int value, int maxExc) {
        if (value >= maxExc) {
            throw new IllegalArgumentException("Value: " + value + " must be lesser than " + maxExc);
        }
    }

    public static void lte(int value, int maxInc) {
        if (value > maxInc) {
            throw new IllegalArgumentException("Value: " + value + " must be lesser than or equal to " + maxInc);
        }
    }

    public static void betweenInc(int value, int minInc, int maxInc) {
        if ((value < minInc) || (value > maxInc)) {
            throw new IllegalArgumentException("Value: " + value + " must be between " + minInc + " and " + maxInc + " inclusive");
        }
    }

    public static void choice(int value, int... choices) {
        for (int choice : choices) {
            if (value == choice) {
                return;
            }
        }
        throw new IllegalArgumentException("Value: " + value + " must be one of " + Arrays.toString(choices));
    }
}
