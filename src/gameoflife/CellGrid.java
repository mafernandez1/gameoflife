/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.Scanner;

/**
 *
 * @author misaf
 */
public class CellGrid {

    private int rowNumber;
    private int columnNumber;
    private int numGeneration;
    private int[][] cells;
    private char option;

    public void readInitialise() {
        System.out.println("================");
        System.out.println("= Game of Life =");
        System.out.println("================");
        System.out.println("Please set the grid dimension");
        //System.out.print("Set the number of rows: ");
        Scanner readData = new Scanner(System.in);
        validateNumber(readData, rowNumber, "Set the number of rows: ");
        readData.nextLine();
        System.out.print("Set the number of columns: ");
        columnNumber = readData.nextInt();
        readData.nextLine();
        System.out.print("Set the number of generations: ");
        numGeneration = readData.nextInt();
        readData.nextLine();
        System.out.print("Do you wish to seed the grid? S/N: ");
        option = readData.next().toLowerCase().charAt(0);

        cells = new int[rowNumber][columnNumber];

        if (option == 's') {
            setGridSeed();
        }
    }

    private void setGridSeed() {
        for (int i = 0; i < (rowNumber * columnNumber); i++) {
            int randomRow = (int) Math.floor(Math.random() * rowNumber);
            int randomColumn = (int) Math.floor(Math.random() * columnNumber);

            cells[randomRow][randomColumn] = 1;
        }
    }

    /**
     * Prints the cell grid
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
     * Prints an empty space in case the cell is dead (null) or * in case the
     * cell is alive
     *
     * @param cell The value of the cell dead (null) or alive (*)
     * @return Empty space or * for printing purposes
     */
    private String printCell(int cell) {
        return cell == 0 ? " " : "*";
    }

    private void setAliveDeathCell(int rowPosition, int columnPosition, int[][] nextCellGrid) {
        int aliveNeighbour = 0;
        for (int rowNeighbour = -1; rowNeighbour <= 1; rowNeighbour++) {
            for (int columnNeighbour = -1; columnNeighbour <= 1; columnNeighbour++) {
                int rowIndex = rowPosition + rowNeighbour;
                int columnIndex = columnPosition - columnNeighbour;
                if (rowIndex >= 0 && columnIndex >= 0 && rowIndex < rowNumber && columnIndex < columnNumber) {
                    if (cells[rowIndex][columnIndex] != 0) {
                        aliveNeighbour++;
                    }
                }
            }
        }

        aliveNeighbour -= cells[rowPosition][columnPosition];

        if (cells[rowPosition][columnPosition] == 0 && aliveNeighbour == 0) {
            nextCellGrid[rowPosition][columnPosition] = 0;
        } else if (cells[rowPosition][columnPosition] != 0 && aliveNeighbour < 2) {
            nextCellGrid[rowPosition][columnPosition] = 0;
        } else if (cells[rowPosition][columnPosition] != 0 && aliveNeighbour > 3) {
            nextCellGrid[rowPosition][columnPosition] = 0;
        } else if (cells[rowPosition][columnPosition] != 0 && (aliveNeighbour == 2 || aliveNeighbour == 3)) {
            nextCellGrid[rowPosition][columnPosition] = 1;
        } else if (cells[rowPosition][columnPosition] == 0 && aliveNeighbour == 3) {
            nextCellGrid[rowPosition][columnPosition] = 1;
        }
    }

    private void nextGeneration() {
        int[][] nextCellGrid = new int[rowNumber][columnNumber];
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                setAliveDeathCell(i, j, nextCellGrid);
            }
        }

        cells = nextCellGrid;
    }

    public void beginGameOfLife() {
        System.out.println("Initial state");
        printGrid();

        for (int i = 1; i <= numGeneration; i++) {
            System.out.println("\nGeneration " + i);
            nextGeneration();

            printGrid();
        }
    }

    private void validateNumber(Scanner readData, int input, String message) {
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
    }
}
