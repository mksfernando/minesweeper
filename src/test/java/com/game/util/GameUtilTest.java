package com.game.util;

import com.game.component.model.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameUtilTest {

    @Test
    void generateCoordinateNull() {
        // When
        Coordinate coordinate = GameUtil.generateCoordinate("@hareld~");
        // Then
        assertNull(coordinate);
    }

    @Test
    void generateCoordinateInvalid() {
        // When
        Coordinate coordinate = GameUtil.generateCoordinate("A27");
        // Then
        assertNotNull(coordinate);
    }

    @Test
    void generateCoordinateValid() {
        // When
        Coordinate coordinate = GameUtil.generateCoordinate("A2");
        // Then
        assertNotNull(coordinate);
    }
}