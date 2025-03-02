package com.game.util;

import com.game.component.model.Coordinate;

/**
 * The type Game util.
 */
public class GameUtil {
    /**
     * Generate coordinate.
     *
     * @param coordinateString the coordinate string
     * @return the coordinate
     */
    public static Coordinate generateCoordinate(String coordinateString) {
        if (coordinateString != null && coordinateString.length() >= 2 && coordinateString.length() <= 3) {
            String rowStr = coordinateString.substring(0, 1);
            String colStr = coordinateString.substring(1);
            try {
                char rowChar = rowStr.toUpperCase().charAt(0);
                if (Character.isLetter(rowChar)) {
                    int row = rowChar - 'A';
                    int col = Integer.parseInt(colStr) - 1;
                    return new Coordinate(row, col);
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
