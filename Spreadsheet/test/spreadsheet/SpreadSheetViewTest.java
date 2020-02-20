package spreadsheet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import spreadsheet.exception.CyclicDependencyException;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620 
 * Email - shwetanazarkar@gmail.com
 * This class has unit test for spreadsheet functionalities
 * It tests setting value at cell
 * getting value from cell
 * undo operation
 * cyclic dependency detection
 */
public class SpreadSheetViewTest {

    private SpreadSheetModel model;
    private SpreadSheetView sheet;
    Cell[][] cells = new Cell[1][9];

    @Before
    public void Setup() {
        for (int i = 0; i < 9; i++) {
            cells[0][i] = new Cell();
        }
        ValueView initialState = new ValueView();
        sheet = new SpreadSheetView(initialState, cells);
        sheet.createValueGUI(cells);
    }

    /**
     * Test method to test if value is set in cell when value is set in cell,
     * the cell updated event be triggered check the event count after setting
     * value at cell
     */
    @Test
    public void testSetValueAt() {
        ModelTableListener mockListerner = new ModelTableListener();
        model = sheet.getModel();
        model.addTableModelListener(mockListerner);
        model.setValueAt("1.0", 0, 1);
        Assert.assertEquals("Event count", 1, mockListerner.getEventCount());
    }

    /**
     * Test method to fetch value from cell here spreadsheet is in value view
     */
    @Test
    public void testGetValueAt() {
        ModelTableListener mockListerner = new ModelTableListener();
        model = sheet.getModel();
        model.setValueAt("1.0", 0, 0);
        String expectedVal = "1.0";
        Assert.assertEquals(expectedVal, model.getValueAt(0, 0));
    }

    /**
     * Test method to check if expression is set in cell in value view result of
     * formula should be displayed
     */
    @Test
    public void testSetExpressionValueAt() {
        model = sheet.getModel();
        model.setValueAt("1", 0, 0);
        model.setValueAt("2", 0, 1);
        model.setState(new EquationView());
        model.setValueAt("$A $B +", 0, 2);
        String expectedVal = "3.0";
        model.setState(new ValueView());
        Assert.assertEquals(expectedVal, model.getValueAt(0, 2));
    }

    /**
     * Test method to test undo mechanism in spreadsheet edit first cell thrice
     * test unlimited undo
     */
    @Test
    public void testUndo() {
        model = sheet.getModel();
        model.setValueAt("1", 0, 0);
        model.setValueAt("2", 0, 0);
        model.setValueAt("3", 0, 0);
        model.undo();
        String expectedValue = "2";
        Assert.assertEquals(expectedValue, model.getValueAt(0, 0));
        model.setState(new EquationView());
        model.setValueAt("$B $C +", 0, 0);
        model.setValueAt("$D", 0, 0);
        model.undo();
        Assert.assertEquals("$B $C +", model.getValueAt(0, 0));
    }

    /**
     * This method will test toggle view functionality Initially view is value
     * view and value is set at first column Then view is changed to equation
     * view Expression is set at second column Again view is toggled by changing
     * model state fetch the value of cell from value view
     */
    @Test
    public void clickToggleButtonTest() {
        model = sheet.getModel();
        model.setValueAt("1.0", 0, 0);
        model.setState(new EquationView());
        model.setValueAt("$A", 0, 1);
        model.setState(new ValueView());
        String expected = "1.0";
        Assert.assertEquals(expected, model.getValueAt(0, 0));
    }

    @Test
    public void cyclicDependencyTest() {
        model = sheet.getModel();
        model.setView(sheet);
        model.setState(new EquationView());
        model.setValueAt("$B", 0, 0);
        model.setValueAt("$A", 0, 1);
        boolean[] visitedColumns = new boolean[9];
        boolean[] recStack = new boolean[9];
        assertThrows(CyclicDependencyException.class, () -> model.isCyclic(0, 1, visitedColumns, recStack));
    }
}
