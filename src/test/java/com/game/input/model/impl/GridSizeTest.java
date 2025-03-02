package com.game.input.model.impl;

import com.game.input.model.GameInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GridSizeTest {
    private GridSize gridSize;

    @BeforeEach
    void setUp() {
        gridSize = new GridSize();
    }

    @Test
    void isValidFalse() {
        gridSize.setValue(1, new GameInput[]{});
        assertFalse(gridSize.isValid());

        gridSize.setValue(27, new GameInput[]{});
        assertFalse(gridSize.isValid());

        gridSize.setValue(-1, new GameInput[]{});
        assertFalse(gridSize.isValid());

        gridSize.setValue(0, new GameInput[]{});
        assertFalse(gridSize.isValid());
    }

    @Test
    void isValidTrue() {
        gridSize.setValue(2, new GameInput[]{});
        assertTrue(gridSize.isValid());

        gridSize.setValue(26, new GameInput[]{});
        assertTrue(gridSize.isValid());

        gridSize.setValue(8, new GameInput[]{});
        assertTrue(gridSize.isValid());
    }
}