package spreadsheet;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class represents the state of the spreadsheet
 * each state will override the toggle functionality to handle toggling of view
 */
public abstract class ViewState {

    public abstract void clickToggleButton(SpreadSheetView spreadSheet);
    
    public abstract void setValue(Cell cell,Object value);
    
    public abstract Object getValueAt(Cell cell);
      
}
