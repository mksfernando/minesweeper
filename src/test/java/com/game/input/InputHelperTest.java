package com.game.input;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputHelperTest {
    private InputHelper inputHelper;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream actualOutput = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(actualOutput);
        inputHelper = null;
    }

    @Test
    void getInputs() {
        // Given
        inputHelper = new InputHelper(new Scanner(new ByteArrayInputStream(("6" + System.lineSeparator() + "1").getBytes())));

        // When
        int[] inputs = inputHelper.getInputs();

        //Then
        assertEquals(6, inputs[0]);
        assertEquals(1, inputs[1]);
    }

    @Test
    void getInputOrQuitWhenIntegerInput() {
        // Given
        inputHelper = new InputHelper(new Scanner(new ByteArrayInputStream("10".getBytes())));

        // When
        String input = inputHelper.getInputOrQuit();

        //Then
        assertEquals("10", input);
    }

    @Test
    void getInputOrQuitWhenGridCoordinateInput() {
        // Given
        inputHelper = new InputHelper(new Scanner(new ByteArrayInputStream("C05".getBytes())));

        // When
        String input = inputHelper.getInputOrQuit();

        //Then
        assertEquals("C05", input);
    }
}