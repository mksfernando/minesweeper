package com.game.component;

import com.game.component.model.MineCell;
import com.game.helper.TestHelper;
import com.game.component.model.Coordinate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MineGridTest {
    private MineGrid mineGrid;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream actualOutput = System.out;

    @BeforeEach
    void setUp() {
        mineGrid = TestHelper.getMineGrid();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(actualOutput);
    }

    @Test
    void print() {
        // Given
        String expectedOutput =
                "Here is your minefield:" +
                        System.lineSeparator() +
                        System.lineSeparator() +
                        "  01 02 03 " + System.lineSeparator() +
                        "A  _  _  _ " + System.lineSeparator() +
                        "B  _  _  _ " + System.lineSeparator() +
                        "C  _  _  _ " + System.lineSeparator() +
                        System.lineSeparator();

        // When
        mineGrid.print();

        // Then
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void revealSingleCell() {
        try {
            // Given
            TestHelper.resetGrid(mineGrid);
            Coordinate coordinate_0_0 = TestHelper.getValidCoordinate_0_0();
            TestHelper.updateIsBomb(true, coordinate_0_0, mineGrid);
            TestHelper.updateTotalMines(1, mineGrid);

            Coordinate coordinate_0_1 = TestHelper.getValidCoordinate_0_1();
            TestHelper.updateNeighbours(1, coordinate_0_1, mineGrid);
            Coordinate coordinate_1_0 = TestHelper.getValidCoordinate_1_0();
            TestHelper.updateNeighbours(1, coordinate_1_0, mineGrid);
            Coordinate coordinate_1_1 = TestHelper.getValidCoordinate_1_1();
            TestHelper.updateNeighbours(1, coordinate_1_1, mineGrid);

            Coordinate coordinate_0_2 = TestHelper.getValidCoordinate_0_2();
            Coordinate coordinate_1_2 = TestHelper.getValidCoordinate_1_2();
            Coordinate coordinate_2_0 = TestHelper.getValidCoordinate_2_0();
            Coordinate coordinate_2_1 = TestHelper.getValidCoordinate_2_1();
            Coordinate coordinate_2_2 = TestHelper.getValidCoordinate_2_2();

            // When
            mineGrid.revealCell(coordinate_1_1);

            // Then
            assertFalse(TestHelper.isRevealed(coordinate_0_0, mineGrid));
            assertFalse(TestHelper.isRevealed(coordinate_0_1, mineGrid));
            assertFalse(TestHelper.isRevealed(coordinate_0_2, mineGrid));
            assertFalse(TestHelper.isRevealed(coordinate_1_0, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_1_1, mineGrid));
            assertFalse(TestHelper.isRevealed(coordinate_1_2, mineGrid));
            assertFalse(TestHelper.isRevealed(coordinate_2_0, mineGrid));
            assertFalse(TestHelper.isRevealed(coordinate_2_1, mineGrid));
            assertFalse(TestHelper.isRevealed(coordinate_2_2, mineGrid));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void revealMultipleCell() {
        try {
            // Given
            TestHelper.resetGrid(mineGrid);
            Coordinate coordinate_0_0 = TestHelper.getValidCoordinate_0_0();
            TestHelper.updateIsBomb(true, coordinate_0_0, mineGrid);
            TestHelper.updateTotalMines(1, mineGrid);

            Coordinate coordinate_0_1 = TestHelper.getValidCoordinate_0_1();
            TestHelper.updateNeighbours(1, coordinate_0_1, mineGrid);
            Coordinate coordinate_1_0 = TestHelper.getValidCoordinate_1_0();
            TestHelper.updateNeighbours(1, coordinate_1_0, mineGrid);
            Coordinate coordinate_1_1 = TestHelper.getValidCoordinate_1_1();
            TestHelper.updateNeighbours(1, coordinate_1_1, mineGrid);

            Coordinate coordinate_0_2 = TestHelper.getValidCoordinate_0_2();
            Coordinate coordinate_1_2 = TestHelper.getValidCoordinate_1_2();
            Coordinate coordinate_2_0 = TestHelper.getValidCoordinate_2_0();
            Coordinate coordinate_2_1 = TestHelper.getValidCoordinate_2_1();
            Coordinate coordinate_2_2 = TestHelper.getValidCoordinate_2_2();

            // When
            mineGrid.revealCell(coordinate_2_2);

            // Then
            assertFalse(TestHelper.isRevealed(coordinate_0_0, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_0_1, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_0_2, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_1_0, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_1_1, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_1_2, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_2_0, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_2_1, mineGrid));
            assertTrue(TestHelper.isRevealed(coordinate_2_2, mineGrid));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void isValidCoordinateTrue() {
        // When
        Coordinate validCoordinate = TestHelper.getValidCoordinate_1_2();

        // Then
        assertTrue(mineGrid.isValidCoordinate(validCoordinate));
    }

    @Test
    void isValidCoordinateFalse() {
        // When
        Coordinate invalidCoordinate = TestHelper.getInvalidCoordinate();

        // Then
        assertFalse(mineGrid.isValidCoordinate(invalidCoordinate));
    }

    @Test
    void isWonTrue() {
        // Given
        int totalRevealsToWin = TestHelper.ROWS * TestHelper.COLS - TestHelper.INITIAL_BOMBS;

        // When
        try {
            TestHelper.updateTotalReveals(totalRevealsToWin, mineGrid);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        // Then
        assertTrue(mineGrid.isWon());
    }

    @Test
    void isWonFalse() {
        // Given (totalReveals < totalRevealsToWin)
        int totalReveals = 1;

        // When
        try {
            TestHelper.updateTotalReveals(totalReveals, mineGrid);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        // Then
        assertFalse(mineGrid.isWon());
    }

    @Test
    void isLostTrue() {
        try {
            // Given
            Coordinate coordinate = TestHelper.getValidCoordinate_1_2();

            // When
            MineCell[][] mineCells = TestHelper.getMineCells(mineGrid);
            mineCells[1][2].setBomb();

            // Then
            assertTrue(mineGrid.isLost(coordinate));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void isLostFalseDueToInvalidCoordinate() {
        // When
        Coordinate coordinate = TestHelper.getInvalidCoordinate();

        // Then
        assertFalse(mineGrid.isLost(coordinate));
    }

    @Test
    void isLostFalseDueToNullCoordinate() {
        // When
        Coordinate coordinate = null;

        // Then
        assertFalse(mineGrid.isLost(coordinate));
    }

    @Test
    void isLostFalseDueToNotBomb() {
        try {
            // Given
            Coordinate coordinate = TestHelper.getValidCoordinate_1_2();

            // When
            MineCell[][] mineCells = TestHelper.getMineCells(mineGrid);
            TestHelper.setAsNotBomb(mineCells[1][2]);

            // Then
            assertFalse(mineGrid.isLost(coordinate));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void getMineCell() {
        try {
            // Given
            Coordinate coordinate = TestHelper.getValidCoordinate_1_2();

            // When
            MineCell[][] mineCells = TestHelper.getMineCells(mineGrid);
            mineCells[1][2].setBomb();

            // Then
            MineCell mineCell = mineGrid.getMineCell(coordinate);
            assertEquals(MineCell.class, mineCell.getClass());
            assertTrue(mineCell.isBomb());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }
}