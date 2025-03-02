package com.game.input;

import com.game.GameConstants;
import com.game.input.model.GameInput;
import com.game.input.model.impl.GridSize;
import com.game.input.model.impl.MineSize;

import java.util.Scanner;

/**
 * The type Input helper.
 */
public class InputHelper {
    private final Scanner scanner;

    /**
     * Instantiates a new Input helper.
     *
     * @param scanner the scanner
     */
    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Get inputs int [ ].
     *
     * @return the int [ ]
     */
    public int[] getInputs() {
        GameInput[] inputs = {new GridSize(), new MineSize()};
        int[] result = new int[inputs.length];
        System.out.println(GameConstants.WELCOME_MSG);

        for (int i = 0; i < inputs.length; i++) {
            while (true) {
                try {
                    System.out.print(inputs[i].getInputMsg());
                    result[i] = Integer.parseInt(getInputOrQuit());
                    inputs[i].setValue(result[i], inputs);
                    if (inputs[i].isValid())
                        break;
                    else
                        System.out.println(inputs[i].getInvalidMsg());
                } catch (NumberFormatException e) {
                    System.out.println(inputs[i].getInvalidMsg());
                }
            }
        }
        return result;
    }

    /**
     * Gets input or quit.
     *
     * @return the input or quit
     */
    public String getInputOrQuit() {
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase(GameConstants.QUIT)) {
            System.out.println(GameConstants.GOOD_BYE);
            System.exit(0);
        }
        return input;
    }
}
