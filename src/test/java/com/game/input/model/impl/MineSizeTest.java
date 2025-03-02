package com.game.input.model.impl;

import com.game.input.model.GameInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MineSizeTest {
    private MineSize mineSize;

    @BeforeEach
    void setUp() {
        mineSize = new MineSize();
    }

    @Test
    void isValidFalseForZero() {
        // When
        GridSize gridSize = new GridSize();
        gridSize.setValue(3, new GameInput[]{});
        mineSize.setValue(0, new GameInput[]{gridSize});

        // Then
        assertFalse(mineSize.isValid());
    }

    @Test
    void isValidFalseForExceed35() {
        // When
        GridSize gridSize = new GridSize();
        gridSize.setValue(3, new GameInput[]{});
        mineSize.setValue(5, new GameInput[]{gridSize});

        // Then
        assertFalse(mineSize.isValid());
    }

    @Test
    void isValidTrueForLessThan35() {
        // When
        GridSize gridSize = new GridSize();
        gridSize.setValue(3, new GameInput[]{});
        mineSize.setValue(3, new GameInput[]{gridSize});

        // Then
        assertTrue(mineSize.isValid());
    }
}