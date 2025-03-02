package com.game;

/**
 * The interface Game constants.
 */
public interface GameConstants {
    String ALREADY_REVEALED = "Mine cell is already Revealed. Please select an unrevealed cell.";
    String GAME_OVER = "Oh no, you detonated a mine! Game over.";
    String GAME_WON = "Congratulations, you have won the game!";
    String GOOD_BYE = "Good Bye";
    String GRID_HEADER = "Here is your minefield:";
    String GRID_SIZE_INPUT_MSG = "Enter the size of the grid (e.g. 4 for a 4x4 grid): ";
    String INVALID_COORDINATE = "Invalid coordinate.";
    String INVALID_COORDINATE_FORMAT = "Invalid coordinate format. Please follow this format [A1/a1 or A01/a01].";
    String INVALID_GRID_SIZE_MSG = "Invalid input for size of the grid. Grid size should be a digit between [%s-%s] inclusive.";
    String INVALID_MINE_SIZE_MSG = "Number of mines should be a digit and neither be 0 nor exceed %s%% of the total squares.";
    String MINE_SIZE_INPUT_MSG = "Enter the number of mines to place on the grid (maximum is %s%% of the total squares):";
    String QUIT = "quit";
    String RETRY_OR_QUIT = "Press [Enter] to play again... , Type 'quit' and hit [Enter] to exit : ";
    String SELECT_SQR = "Select a square to reveal (e.g. A1): ";
    String WELCOME_MSG = "Welcome to Minesweeper!";
}
