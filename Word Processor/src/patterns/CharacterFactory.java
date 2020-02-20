package patterns;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shwetan
 * This is singleton character factory class
 * Given unicode code point it will return Flyweight character
 */
public class CharacterFactory {
    private Map<Character, FlyweightCharacter> charMap = new HashMap<Character, FlyweightCharacter>();
    private static CharacterFactory factoryInstance;

    /**
     * @param unicode
     * @return Flyweight Character for given unicode
     */
    public FlyweightCharacter getCharacter(char unicode){
        if(!charMap.containsKey(unicode)){
            charMap.put(unicode,new FlyweightCharacter(unicode));
        }
        return charMap.get(unicode);
    }

    /**
     * @return single instance of factory
     */
    public static CharacterFactory getFactoryInstance(){
        if(factoryInstance == null){
            factoryInstance = new CharacterFactory();
        }
        return factoryInstance;
    }
}
