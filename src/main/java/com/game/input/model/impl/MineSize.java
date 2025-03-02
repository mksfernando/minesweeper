package com.game.input.model.impl;

import com.game.GameConstants;
import com.game.input.model.GameInput;

/**
 * The type Mine size.
 */
public class MineSize implements GameInput {
    private static final int MAX_PROP = 35;
    private int value;
    private GameInput[] inputs;

    @Override
    public String getInputMsg() {
        return String.format(GameConstants.MINE_SIZE_INPUT_MSG, MAX_PROP);
    }

    @Override
    public void setValue(int value, GameInput[] inputs) {
        this.value = value;
        this.inputs = inputs;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isValid() {
        return value > 0 && value * 100f / (inputs[0].getValue() * inputs[0].getValue()) <= MAX_PROP;
    }

    @Override
    public String getInvalidMsg() {
        return String.format(GameConstants.INVALID_MINE_SIZE_MSG, MAX_PROP);
    }
}
