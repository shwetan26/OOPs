package patterns;

/**
 * @author shwetan
 * This is flyweight character class
 */
public class FlyweightCharacter implements Flywieght{
    char unicode;

    public FlyweightCharacter(char unicode) {
        this.unicode = unicode;
    }

    @Override
    public void draw() {
        //placeholder to add some functionality which will display characters
    }
}
