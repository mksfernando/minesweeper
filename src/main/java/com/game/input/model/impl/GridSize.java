package com.game.input.model.impl;

import com.game.GameConstants;
import com.game.input.model.GameInput;

/**
 * The type Grid size.
 */
public class GridSize implements GameInput {
    private static final int MAX_GRID_SIZE = 26;
    private static final int MIN_GRID_SIZE = 2;
    private int value;

    @Override
    public String getInputMsg() {
        return GameConstants.GRID_SIZE_INPUT_MSG;
    }

    @Override
    public void setValue(int value, GameInput[] inputs) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isValid() {
        return value > 0 && value >= MIN_GRID_SIZE && value <= MAX_GRID_SIZE;
    }

    @Override
    public String getInvalidMsg() {
        return String.format(GameConstants.INVALID_GRID_SIZE_MSG, MIN_GRID_SIZE, MAX_GRID_SIZE);
    }
}
