package spreadsheet;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class represents the value view set of spreadsheet
 */
public class ValueView extends ViewState {

    ValueView() {
    }

    @Override
    public void clickToggleButton(SpreadSheetView spreadSheet) {
        //current view is value view, set new state to equation view
        spreadSheet.setViewState(new EquationView());
    }

    @Override
    public void setValue(Cell cell, Object value) {
        cell.setValue((String)value);
    }

    @Override
    public Object getValueAt(Cell cell) {
        return cell.getValue();
    }
}
