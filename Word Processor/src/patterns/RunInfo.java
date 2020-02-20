package patterns;

/**
 * @author shwetan
 * This class represents Run information
 */
public class RunInfo {
    private int startIndex;
    private int endIndex;
    Font font;

    public RunInfo(int startIndex, int endIndex, Font font) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

    /**
     * This method will check if given index is
     * present in run or not
     * @param index
     * @return boolean
     */
    public boolean contains(int index){
        return (index >= startIndex && index <= endIndex);
    }
}
