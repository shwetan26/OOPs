package patterns;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shwetan
 * This is factory class to create fonts
 * Given font name,style and size it will return font
 */
public class FontFactory {
    private Map<String, Font> fontMap = new HashMap();
    //ensure single factory instance is creates
    private static FontFactory factoryInstance;

    /**
     * @param name
     * @param style
     * @param size
     * @return Font
     */
    public Font getFont(String name,int style,int size){
        String key = name+size+style;
        if(!fontMap.containsKey(key)){
            fontMap.put(key,new Font(name,style,size));
        }
        return fontMap.get(key);
    }

    /**
     * @return single instance of font factory
     */
    public static FontFactory getInstance(){
        if(factoryInstance == null)
            factoryInstance = new FontFactory();
        return factoryInstance;
    }
}
