package spreadsheet;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class represents the state of spreadsheet in the form of equation view
 */
public class EquationView extends ViewState {
    
    EquationView() {
    }

    @Override
    public void clickToggleButton(SpreadSheetView spreadSheet) {
        //current view is equation view change it to value view
        spreadSheet.setViewState(new ValueView());
    }

    @Override
    public void setValue(Cell cell, Object value) {
        cell.setFormula((String) value);
    }

    @Override
    public Object getValueAt(Cell cell) {
        return cell.getExpression();
    }
}
