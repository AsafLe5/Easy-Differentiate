

/**
 * 205543317.
 */

public class BaseExpression {

    private String format; // format of toString

    /**
     * Sets the format of the expression.
     *
     * @param format a String format.
     */
    protected BaseExpression(String format) {
        this.format = format;
    }

    /**
     * Gets the String format.
     *
     * @return String format.
     */
    public String getFormat() {
        return this.format;
    }
}
