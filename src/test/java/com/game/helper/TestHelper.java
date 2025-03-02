package com.game.helper;

import com.game.component.model.MineCell;
import com.game.component.MineGrid;
import com.game.component.model.Coordinate;

import java.lang.reflect.Field;

public class TestHelper {
    public static final int INITIAL_BOMBS = 1;
    public static final int ROWS = 3;
    public static final int COLS = 3;

    public static MineGrid getMineGrid() {
        return new MineGrid(ROWS, COLS, INITIAL_BOMBS);
    }

    public static Coordinate getValidCoordinate_0_0() {
        return new Coordinate(0, 0);
    }
    public static Coordinate getValidCoordinate_0_1() {
        return new Coordinate(0, 1);
    }
    public static Coordinate getValidCoordinate_0_2() {
        return new Coordinate(0, 2);
    }

    public static Coordinate getValidCoordinate_1_0() {
        return new Coordinate(1, 0);
    }
    public static Coordinate getValidCoordinate_1_1() {
        return new Coordinate(1, 1);
    }
    public static Coordinate getValidCoordinate_1_2() {
        return new Coordinate(1, 2);
    }

    public static Coordinate getValidCoordinate_2_0() {
        return new Coordinate(2, 0);
    }
    public static Coordinate getValidCoordinate_2_1() {
        return new Coordinate(2, 1);
    }
    public static Coordinate getValidCoordinate_2_2() {
        return new Coordinate(2, 2);
    }

    public static Coordinate getInvalidCoordinate() {
        return new Coordinate(27, 2);
    }

    public static MineCell[][] getMineCells(MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("mineCells");
        field.setAccessible(true);
        return (MineCell[][]) field.get(mineGrid);
    }

    public static void setAsNotBomb(MineCell mineCell) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineCell.class.getDeclaredField("isBomb");
        field.setAccessible(true);
        field.set(mineCell, false);
    }

    public static Coordinate searchBomb(MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("mineCells");
        field.setAccessible(true);
        MineCell[][] mineCells = (MineCell[][]) field.get(mineGrid);

        for (int r = 0; r < mineCells.length; r++) {
            for (int c = 0; c < mineCells[0].length; c++) {
                if (mineCells[r][c].isBomb())
                    return new Coordinate(r, c);
            }
        }
        return null;
    }

    public static void updateTotalReveals(int reveals, MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("totalReveals");
        field.setAccessible(true);
        field.set(mineGrid, reveals);
    }

    public static void updateTotalMines(int mines, MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("totalMines");
        field.setAccessible(true);
        field.set(mineGrid, mines);
    }

    public static void resetMineCells(MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("mineCells");
        field.setAccessible(true);
        MineCell[][] mineCells = (MineCell[][]) field.get(mineGrid);

        for (int r = 0; r < mineCells.length; r++) {
            for (int c = 0; c < mineCells[0].length; c++) {
                mineCells[r][c].reset();
            }
        }
    }

    public static void updateIsRevealed(boolean isRevealed, Coordinate coordinate, MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("mineCells");
        field.setAccessible(true);
        MineCell[][] mineCells = (MineCell[][]) field.get(mineGrid);
        MineCell mineCell = mineCells[coordinate.getRow()][coordinate.getCol()];

        field = MineCell.class.getDeclaredField("isRevealed");
        field.setAccessible(true);
        field.set(mineCell, isRevealed);
    }

    public static void updateNeighbours(int neighbours, Coordinate coordinate, MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("mineCells");
        field.setAccessible(true);
        MineCell[][] mineCells = (MineCell[][]) field.get(mineGrid);
        MineCell mineCell = mineCells[coordinate.getRow()][coordinate.getCol()];

        field = MineCell.class.getDeclaredField("neighbours");
        field.setAccessible(true);
        field.set(mineCell, neighbours);
    }

    public static boolean isRevealed(Coordinate coordinate, MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("mineCells");
        field.setAccessible(true);
        MineCell[][] mineCells = (MineCell[][]) field.get(mineGrid);
        MineCell mineCell = mineCells[coordinate.getRow()][coordinate.getCol()];
        return mineCell.isRevealed();
    }

    public static boolean isBomb(Coordinate coordinate, MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("mineCells");
        field.setAccessible(true);
        MineCell[][] mineCells = (MineCell[][]) field.get(mineGrid);
        MineCell mineCell = mineCells[coordinate.getRow()][coordinate.getCol()];
        return mineCell.isBomb();
    }

    public static void updateIsBomb(boolean isBomb, Coordinate coordinate, MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        Field field = MineGrid.class.getDeclaredField("mineCells");
        field.setAccessible(true);
        MineCell[][] mineCells = (MineCell[][]) field.get(mineGrid);
        MineCell mineCell = mineCells[coordinate.getRow()][coordinate.getCol()];

        field = MineCell.class.getDeclaredField("isBomb");
        field.setAccessible(true);
        field.set(mineCell, isBomb);
    }

    public static void resetGrid(MineGrid mineGrid) throws NoSuchFieldException, IllegalAccessException {
        updateTotalReveals(0, mineGrid);
        updateTotalMines(0, mineGrid);
        resetMineCells(mineGrid);
    }
}
