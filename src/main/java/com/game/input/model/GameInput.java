package com.game.input.model;

/**
 * The interface Game input.
 */
public interface GameInput {
    /**
     * Gets input msg.
     *
     * @return the input msg
     */
    String getInputMsg();

    /**
     * Sets value.
     *
     * @param value  the value
     * @param inputs the inputs
     */
    void setValue(int value, GameInput[] inputs);

    /**
     * Gets value.
     *
     * @return the value
     */
    int getValue();

    /**
     * Is valid boolean.
     *
     * @return the boolean
     */
    boolean isValid();

    /**
     * Gets invalid msg.
     *
     * @return the invalid msg
     */
    String getInvalidMsg();
}
