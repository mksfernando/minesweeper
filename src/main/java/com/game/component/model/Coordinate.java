package com.game.component.model;

/**
 * The type Coordinate.
 */
public class Coordinate {
    /**
     * The Row.
     */
    int row;
    /**
     * The Col.
     */
    int col;

    /**
     * Instantiates a new Coordinate.
     *
     * @param row the row
     * @param col the col
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets col.
     *
     * @return the col
     */
    public int getCol() {
        return col;
    }
}
