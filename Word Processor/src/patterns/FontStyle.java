package patterns;

/**
 * @author shwetan
 * Enum representing font styles
 */
public enum FontStyle {
    BOLD (0),
    REGULAR (1),
    ITALIC (2);

    private final int styeleCode;

    FontStyle(int styeleCode) {
        this.styeleCode = styeleCode;
    }

    public int getStyeleCode() {
        return this.styeleCode;
    }

}
