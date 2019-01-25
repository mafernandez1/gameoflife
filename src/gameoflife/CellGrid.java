package gameoflife;

import java.util.Scanner;

/**
 *
 * @author Misahael Fernandez
 */
public class CellGrid {

    // Variables declaration
    private int rowNumber;
    private int columnNumber;
    private int numGeneration;
    private int[][] cells;
    private char option;

    public CellGrid() {
    }

    public CellGrid(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        cells = new int[rowNumber][columnNumber];
    }

    /**
     * Reads the necessary data and initialises the grid
     */
    public void readInitialise() {
        System.out.println("================");
        System.out.println("= Game of Life =");
        System.out.println("================");
        System.out.println("Please set the grid dimension");
        Scanner readData = new Scanner(System.in);
        rowNumber = validateNumber(readData, rowNumber, "Set the number of rows: ");
        readData.nextLine();
        columnNumber = validateNumber(readData, columnNumber, "Set the number of columnss: ");
        readData.nextLine();
        numGeneration = validateNumber(readData, numGeneration, "Set the number of generationss: ");
        readData.nextLine();
        System.out.print("Do you wish to seed the grid? S/N: ");
        option = readData.next().toLowerCase().charAt(0);

        cells = new int[rowNumber][columnNumber];

        if (option == 's') {
            setGridSeed();
        }
    }

    /**
     * Set alive cells in random positions of the grid
     */
    private void setGridSeed() {
        for (int i = 0; i < (rowNumber * columnNumber); i++) {
            // Random numbers from 0 to rowNumber - 1
            int randomRow = (int) Math.floor(Math.random() * rowNumber);
            int randomColumn = (int) Math.floor(Math.random() * columnNumber);
            // Set the alive cell (1)
            cells[randomRow][randomColumn] = 1;
        }
    }

    /**
     * Prints the cell grid formatted
     */
    private void printGrid() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                System.out.print(" | " + printCell(cells[i][j]));
                if (j == columnNumber - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints an empty space in case the cell is dead (0) or * in case the cell
     * is alive (1)
     *
     * @param cell The value of the cell dead (0) or alive (1)
     * @return Empty space or * for printing and formatting purposes
     */
    private String printCell(int cell) {
        return cell == 0 ? " " : "*";
    }

    /**
     * Sets to the next generation grid if the cell is alive or dead
     *
     * @param rowPosition Current row of the main grid
     * @param columnPosition Current column of the main grid
     * @param nextCellGrid Temporal grid to store the new state
     */
    private void setAliveDeathCell(int rowPosition, int columnPosition, int[][] nextCellGrid) {
        // Count how many alive neighbours are
        int aliveNeighbour = 0;
        // Since we need to look up for the inmediate neighbours if the current cell, we have to subtract 1 and add 1.
        // The two for loops look up for all the eight neighbours around the current cell.
        for (int rowNeighbour = -1; rowNeighbour <= 1; rowNeighbour++) {
            for (int columnNeighbour = -1; columnNeighbour <= 1; columnNeighbour++) {
                // The first value of the two for loops are -1. Therefore, we subtact 1 and as the loop continues we ended with adding 1.
                // We have to consider that we are counting the current cell too.
                int rowIndex = rowPosition + rowNeighbour;
                int columnIndex = columnPosition - columnNeighbour;
                // Validation to avoid ArrayIndexOutOfBoundsException
                if (rowIndex >= 0 && columnIndex >= 0 && rowIndex < rowNumber && columnIndex < columnNumber) {
                    // If the neighbour is alive, augment 1 to the counter
                    if (cells[rowIndex][columnIndex] != 0) {
                        aliveNeighbour++;
                    }
                }
            }
        }

        // Given that we are counting the current cell we must subtract the value in the current position. Either 0 or 1.
        aliveNeighbour -= cells[rowPosition][columnPosition];

        // Source: Attached document from email "BBC Resourcing Team <Nicola.Key@bbc.co.uk>"
        // Scenario 0: No interactions
        // Given a game of life
        // When there are no live cells
        // Then on the next step there are still no live cells
        if (cells[rowPosition][columnPosition] == 0 && aliveNeighbour == 0) {
            nextCellGrid[rowPosition][columnPosition] = 0;
        } //     Scenario 1: Underpopulation
        //	Given a game of life
        //	When a live cell has fewer than two neighbours
        //	Then this cell dies
        else if (cells[rowPosition][columnPosition] != 0 && aliveNeighbour < 2) {
            nextCellGrid[rowPosition][columnPosition] = 0;
        } //     Scenario 2: Overcrowding
        //	Given a game of life
        //	When a live cell has more than three neighbours
        //	Then this cell dies
        else if (cells[rowPosition][columnPosition] != 0 && aliveNeighbour > 3) {
            nextCellGrid[rowPosition][columnPosition] = 0;
        } //    Scenario 3: Survival
        //	Given a game of life
        //	When a live cell has two or three neighbours
        //	Then this cell stays alive
        else if (cells[rowPosition][columnPosition] != 0 && (aliveNeighbour == 2 || aliveNeighbour == 3)) {
            nextCellGrid[rowPosition][columnPosition] = 1;
        } //    Scenario 4: Creation of Life
        //	Given a game of life
        //	When an empty position has exactly three neighbouring cells
        //	Then a cell is created in this position
        else if (cells[rowPosition][columnPosition] == 0 && aliveNeighbour == 3) {
            nextCellGrid[rowPosition][columnPosition] = 1;
        }
    }

    /**
     * Iterates all the cells in the grid and applies the rules to determine if
     * the cell is alive or death
     */
    private void nextGeneration() {
        // Temporal grid to store the new state
        int[][] nextCellGrid = new int[rowNumber][columnNumber];
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                setAliveDeathCell(i, j, nextCellGrid);
            }
        }
        // Update the main grid with the temporal grid to begin another generation
        cells = nextCellGrid;
    }

    /**
     * Main process. The method begins printing the initial state and continues
     * printing each generation
     */
    public void beginGameOfLife() {
        System.out.println("Initial state");
        printGrid();

        for (int i = 1; i <= numGeneration; i++) {
            System.out.println("\nGeneration " + i);
            nextGeneration();

            printGrid();
        }
    }

    /**
     * User input validation for positive numbers only
     *
     * @param readData Scanner object to read the data
     * @param input The number to be read
     * @param message The message that the user will see to set the values
     */
    private int validateNumber(Scanner readData, int input, String message) {
        System.out.print(message);
        do {
            while (!readData.hasNextInt()) {
                System.out.print("Invalid input! Please try again: ");
                readData.next();
            }
            input = readData.nextInt();
            if (input <= 0) {
                System.out.print("Invalid input! Please try again: ");
            }
        } while (input <= 0);

        return input;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int getNumGeneration() {
        return numGeneration;
    }

    public void setNumGeneration(int numGeneration) {
        this.numGeneration = numGeneration;
    }

    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }
}
