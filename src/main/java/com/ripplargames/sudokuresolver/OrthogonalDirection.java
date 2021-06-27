package com.ripplargames.sudokuresolver;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum OrthogonalDirection implements Directional {
    N(-1, 0),
    E(0, 1),
    S(1, 0),
    W(0, -1);

    private final int rowOffset;
    private final int colOffset;
}
