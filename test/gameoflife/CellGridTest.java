package gameoflife;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Misahael Fernandez
 */
public class CellGridTest {

    public CellGridTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of all cells are dead method, of class CellGrid.
     */
    @Test
    public void testAllDeath() {
        System.out.println("Test all dead");
        CellGrid instance = new CellGrid(5, 5);
        for (int i = 0; i < instance.getRowNumber(); i++) {
            for (int j = 0; j < instance.getColumnNumber(); j++) {
                assertEquals(0, instance.getCells()[i][j]);
            }
        }
    }

    /**
     * Test of beginGameOfLife method, of class CellGrid.
     */
    @Test
    public void testBeginGameOfLifeTenCells() {
        System.out.println("beginGameOfLife");
        CellGrid instance = new CellGrid();
        instance.setRowNumber(17);
        instance.setColumnNumber(18);
        instance.setNumGeneration(10);
        int[][] testCellGrid = new int[17][18];
        testCellGrid[8][4] = 1;
        testCellGrid[8][5] = 1;
        testCellGrid[8][6] = 1;
        testCellGrid[8][7] = 1;
        testCellGrid[8][8] = 1;
        testCellGrid[8][9] = 1;
        testCellGrid[8][10] = 1;
        testCellGrid[8][11] = 1;
        testCellGrid[8][12] = 1;
        testCellGrid[8][13] = 1;
        instance.setCells(testCellGrid);
        instance.beginGameOfLife();
    }

    /**
     * Test of beginGameOfLife method, of class CellGrid.
     */
    @Test
    public void testBeginGameOfLife() {
        System.out.println("beginGameOfLife");
        CellGrid instance = new CellGrid();
        instance.setRowNumber(7);
        instance.setColumnNumber(7);
        instance.setNumGeneration(7);
        int[][] testCellGrid = new int[instance.getRowNumber()][instance.getColumnNumber()];
        testCellGrid[3][2] = 1;
        testCellGrid[3][3] = 1;
        testCellGrid[3][4] = 1;
        instance.setCells(testCellGrid);
        instance.beginGameOfLife();
    }
}
