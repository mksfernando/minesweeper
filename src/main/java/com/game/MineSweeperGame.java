package com.game;

import com.game.component.MineGrid;
import com.game.input.InputHelper;
import com.game.component.model.Coordinate;
import com.game.util.GameUtil;

import java.util.Scanner;

/**
 * The type Mine sweeper game.
 */
public class MineSweeperGame {
    private final InputHelper inputHelper;

    /**
     * Instantiates a new Mine sweeper game.
     */
    public MineSweeperGame() {
        inputHelper = new InputHelper(new Scanner(System.in));
    }

    /**
     * Start.
     */
    public void start() {
        do {
            // Get user input
            int[] inputs = inputHelper.getInputs();
            int size = inputs[0];
            int mines = inputs[1];

            // Initialize Game
            MineGrid mineGrid = new MineGrid(size, size, mines);
            mineGrid.print();

            // Play Game
            Coordinate coordinate;
            do {
                System.out.print(GameConstants.SELECT_SQR);
                String coordinateString = inputHelper.getInputOrQuit();
                coordinate = GameUtil.generateCoordinate(coordinateString);
                if (coordinate == null) {
                    System.out.println(GameConstants.INVALID_COORDINATE_FORMAT);
                } else if (!mineGrid.isValidCoordinate(coordinate)) {
                    System.out.println(GameConstants.INVALID_COORDINATE);
                } else {
                    if (!mineGrid.getMineCell(coordinate).isRevealed()) {
                        mineGrid.revealCell(coordinate);
                        mineGrid.print();
                    } else {
                        System.out.println(GameConstants.ALREADY_REVEALED);
                    }
                }
            } while (!mineGrid.isWon() && !mineGrid.isLost(coordinate));

            // Final Result
            if (mineGrid.isLost(coordinate))
                System.out.println(GameConstants.GAME_OVER);
            else if (mineGrid.isWon())
                System.out.println(GameConstants.GAME_WON);

            System.out.print(GameConstants.RETRY_OR_QUIT);
        } while (inputHelper.getInputOrQuit() != null);
    }
}
