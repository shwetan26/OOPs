package spreadsheet;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * @author shweta Nazarkar 
 * RED ID - 823812620
 * Email - shwetanazarkar@gmail.com
 * 
 * This class catches table events and stores them in events list
 * This class will be useful for unit testing JTable used for storing cells
 */
public class ModelTableListener implements TableModelListener{
    private List events = new ArrayList();
    
    @Override
    public void tableChanged(TableModelEvent e) {
        this.events.add(e);
    }
    
    public int getEventCount(  ) {
        return this.events.size(  );
    }

    public List getEvents(  ) {
        return this.events;
    }
}
