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

//    /**
//     * Test of beginGameOfLife method, of class CellGrid.
//     */
//    @Test
//    public void testBeginGameOfLife() {
//        System.out.println("beginGameOfLife");
//        CellGrid instance = new CellGrid();
//        instance.setRowNumber(10);
//        instance.setColumnNumber(15);
//        instance.setNumGeneration(10);
//        int[][] testCellGrid = new int[10][15];
//        testCellGrid[4][3] = 1;
//        testCellGrid[4][4] = 1;
//        testCellGrid[4][5] = 1;
//        testCellGrid[4][6] = 1;
//        testCellGrid[4][7] = 1;
//        testCellGrid[4][8] = 1;
//        testCellGrid[4][9] = 1;
//        testCellGrid[4][10] = 1;
//        testCellGrid[4][11] = 1;
//        testCellGrid[4][12] = 1;
//        instance.setCells(testCellGrid);
//        instance.beginGameOfLife();
//    }
    /**
     * Test of beginGameOfLife method, of class CellGrid.
     */
    @Test
    public void testBeginGameOfLife() {
        System.out.println("beginGameOfLife");
        CellGrid instance = new CellGrid();
        instance.setRowNumber(3);
        instance.setColumnNumber(3);
        instance.setNumGeneration(7);
        int[][] testCellGrid = new int[3][3];
        testCellGrid[1][0] = 1;
        testCellGrid[1][1] = 1;
        testCellGrid[1][2] = 1;
        instance.setCells(testCellGrid);
        instance.beginGameOfLife();
    }
}
