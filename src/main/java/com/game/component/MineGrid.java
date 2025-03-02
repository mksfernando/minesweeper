package com.game.component;

import com.game.GameConstants;
import com.game.component.model.Coordinate;
import com.game.component.model.MineCell;

import java.util.*;

/**
 * The type MineGrid.
 */
public class MineGrid {
    private final int rows;
    private final int cols;
    private final int totalMines;
    private int totalReveals;
    private final MineCell[][] mineCells;

    /**
     * Instantiates a new MineGrid.
     *
     * @param rows       the rows
     * @param cols       the cols
     * @param totalMines the total mines
     */
    public MineGrid(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        this.mineCells = new MineCell[rows][cols];

        // Initialize Squares
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                this.mineCells[r][c] = new MineCell();
            }
        }

        // Initialize mines randomly
        for (int i = 0; i < totalMines; i++) {
            addMine();
        }
    }

    private void addMine() {
        Random random = new Random();
        Coordinate coordinate;
        // Populate a unique coordinate for the mine
        do {
            coordinate = new Coordinate(random.nextInt(rows - 1), random.nextInt(cols - 1));
        } while (getMineCell(coordinate).isBomb());

        // Mark current coordinate as a mine
        getMineCell(coordinate).setBomb();

        // Update neighbours
        int minRow = Math.max(0, coordinate.getRow() - 1);
        int maxRow = Math.min(rows - 1, coordinate.getRow() + 1);
        int minCol = Math.max(0, coordinate.getCol() - 1);
        int maxCol = Math.min(cols - 1, coordinate.getCol() + 1);
        for (int r = minRow; r <= maxRow; r++) {
            for (int c = minCol; c <= maxCol; c++) {
                mineCells[r][c].addNeighbour();
            }
        }
    }

    /**
     * Print.
     */
    public void print() {
        System.out.println(GameConstants.GRID_HEADER);
        System.out.println();

        System.out.print("  ");
        for (int c = 0; c < cols; c++) {
            System.out.printf("%02d ", c + 1);
        }
        System.out.println();
        for (int r = 0; r < rows; r++) {
            System.out.print((char) ('A' + r) + " ");
            for (int c = 0; c < cols; c++) {
                System.out.printf("%2s ", mineCells[r][c].isRevealed() ?
                        (mineCells[r][c].isBomb() ? "*" : mineCells[r][c].getNeighbours())
                        : "_");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Reveal cell.
     *
     * @param coordinate the coordinate
     */
    public void revealCell(Coordinate coordinate) {
        if (getMineCell(coordinate).getNeighbours() != 0) {
            // Reveal a single cell
            getMineCell(coordinate).reveal();
            totalReveals++;
        } else {
            // Reveal cells with 0 neighbours and currently unrevealed using BFS
            List<Coordinate> revealedList1 = bfs(coordinate);
            List<Coordinate> revealedList2 = revealAround(revealedList1);
            totalReveals += (revealedList1.size() + revealedList2.size());
        }
    }

    private List<Coordinate> bfs(Coordinate coordinate) {
        List<Coordinate> changedList = new ArrayList<>();
        int[][] visited = new int[rows][cols];
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.offer(coordinate);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (isValidCoordinate(current) && visited[current.getRow()][current.getCol()] == 0 &&
                    !getMineCell(current).isRevealed() && getMineCell(current).getNeighbours() == 0) {
                changedList.add(current);
                getMineCell(current).reveal();
                visited[current.getRow()][current.getCol()] = 1;

                queue.add(new Coordinate(current.getRow(), current.getCol() - 1));
                queue.add(new Coordinate(current.getRow(), current.getCol() + 1));
                queue.add(new Coordinate(current.getRow() - 1, current.getCol()));
                queue.add(new Coordinate(current.getRow() + 1, current.getCol()));
            }
        }
        return changedList;
    }

    private List<Coordinate> revealAround(List<Coordinate> revealedList) {
        List<Coordinate> changedList = new ArrayList<>();
        for (Coordinate coordinate : revealedList) {
            changedList.addAll(revealAroundCoordinate(coordinate));
        }
        return changedList;
    }

    private List<Coordinate> revealAroundCoordinate(Coordinate coordinate) {
        List<Coordinate> changedList = new ArrayList<>();

        int minRow = Math.max(0, coordinate.getRow() - 1);
        int maxRow = Math.min(rows - 1, coordinate.getRow() + 1);
        int minCol = Math.max(0, coordinate.getCol() - 1);
        int maxCol = Math.min(cols - 1, coordinate.getCol() + 1);
        for (int r = minRow; r < maxRow; r++) {
            for (int c = minCol; c < maxCol; c++) {
                if (!mineCells[r][c].isRevealed() && mineCells[r][c].getNeighbours() > 0) {
                    mineCells[r][c].reveal();
                    changedList.add(new Coordinate(r, c));
                }
            }
        }
        return changedList;
    }

    /**
     * Is valid coordinate boolean.
     *
     * @param coordinate the coordinate
     * @return the boolean
     */
    public boolean isValidCoordinate(Coordinate coordinate) {
        return coordinate.getRow() >= 0 && coordinate.getRow() < rows &&
                coordinate.getCol() >= 0 && coordinate.getCol() < cols;
    }

    /**
     * Is won boolean.
     *
     * @return the boolean
     */
    public boolean isWon() {
        return totalMines + totalReveals == rows * cols;
    }

    /**
     * Is lost boolean.
     *
     * @param coordinate the coordinate
     * @return the boolean
     */
    public boolean isLost(Coordinate coordinate) {
        if (coordinate != null && isValidCoordinate(coordinate))
            return getMineCell(coordinate).isBomb();
        return false;
    }

    /**
     * Gets mine cell.
     *
     * @param coordinate the coordinate
     * @return the mine cell
     */
    public MineCell getMineCell(Coordinate coordinate) {
        return mineCells[coordinate.getRow()][coordinate.getCol()];
    }
}
