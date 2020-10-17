import java.util.ArrayList;
import java.util.List;

/**
 * 205543317.
 */

public class UnaryExpression extends BaseExpression {

    private Expression expression; // An expression.

    /**
     * Initialize a new BinaryExpression.
     *
     * @param format     String format of the expression.
     * @param expression An expression.
     */
    public UnaryExpression(String format, Expression expression) {
        super(format);
        this.expression = expression;
    }

    /**
     * @return the expression.
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * @return all the variables in the expression.
     */
    public List<String> getVariables() {
        return new ArrayList<>(expression.getVariables());

    }

    /**
     * @return the String's format.
     */
    public String toString() {
        return String.format(super.getFormat(), expression);
    }

}
