package patterns;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author shwetan
 * This class stores the extrinsic state i.e font information
 */
public class RunArray {
    int endIndex;
    private ArrayList<RunInfo> runArray = new ArrayList<>();

    /**
     * Adds new run to runarray
     * @param startIndex
     * @param length
     * @param font
     */
    public void addRun(int startIndex, int length, Font font){
        endIndex = (startIndex+length)-1;
        runArray.add(new RunInfo(startIndex,endIndex,font));
    }

    /**
     * Appends run to runarray
     * @param length
     * @param font
     */
    public void append(int length,Font font){
        int startIndex = endIndex + 1;
        endIndex = (startIndex+length)-1;
        runArray.add(new RunInfo(startIndex,endIndex,font));
    }

    /**
     * Given index gets font for that index
     * @param index
     * @return Font
     */
    public Font getFont(int index){
        Iterator<RunInfo> runArrayIterator = runArray.iterator();
        while(runArrayIterator.hasNext()){
            RunInfo run = runArrayIterator.next();
            if(run.contains(index)){
                return run.getFont();
            }
        }
        return null;
    }
}
