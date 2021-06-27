package com.ripplargames.sudokuresolver;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum DiagonalDirection implements Directional {
    NE(-1, 1),
    NW(-1, -1),
    SE(1, 1),
    SW(1, -1);

    private final int rowOffset;
    private final int colOffset;

    public boolean isN() {
        return (rowOffset == -1);
    }

    public boolean isE() {
        return (colOffset == 1);
    }

    public boolean isS() {
        return (rowOffset == 1);
    }

    public boolean isW() {
        return (colOffset == -1);
    }
}
