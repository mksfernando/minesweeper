package com.game.component.model;

/**
 * The type MineCell.
 */
public class MineCell {
    private int neighbours;
    private boolean isRevealed;
    private boolean isBomb;

    /**
     * Gets neighbours.
     *
     * @return the neighbours
     */
    public int getNeighbours() {
        return neighbours;
    }

    /**
     * Add neighbour.
     */
    public void addNeighbour() {
        this.neighbours++;
    }

    /**
     * Is revealed boolean.
     *
     * @return the boolean
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Reveal.
     */
    public void reveal() {
        isRevealed = true;
    }

    /**
     * Is bomb boolean.
     *
     * @return the boolean
     */
    public boolean isBomb() {
        return isBomb;
    }

    /**
     * Sets bomb.
     */
    public void setBomb() {
        isBomb = true;
    }

    /**
     * Reset.
     */
    public void reset(){
        neighbours = 0;
        isRevealed = false;
        isBomb = false;
    }
}
