package patterns;

/**
 * @author shwetan
 * This class is for non-flyweight character
 */
public class UnsharedCharacter {
    char unicode;
    Font font;

    public UnsharedCharacter(char unicode, Font font) {
        this.unicode = unicode;
        this.font = font;
    }
}
