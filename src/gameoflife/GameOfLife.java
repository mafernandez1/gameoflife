package gameoflife;

/**
 *
 * @author Misahael Fernandez
 */
public class GameOfLife {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CellGrid cellGrid = new CellGrid();
        // Read the necessary data and initialise the grid
        cellGrid.readInitialise();
        // Main process
        cellGrid.beginGameOfLife();
    }

}
