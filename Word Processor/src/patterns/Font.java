package patterns;

/**
 * @author shwetan
 * This class represents Font
 */
public class Font {

    private String name;
    private int style;
    private int size;

    public Font(String name, int style, int size) {
        this.name = name;
        this.style = style;
        this.size = size;
    }

    //this is just another operation on font data
    //we are not using this operation in the assignment
    public boolean isBold(){
        if(style == FontStyle.BOLD.getStyeleCode()){
            return true;
        }
            return false;
    }
}
