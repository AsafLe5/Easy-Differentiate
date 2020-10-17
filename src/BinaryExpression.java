import java.util.ArrayList;
import java.util.List;

/**
 * 205543317.
 */

public class BinaryExpression extends BaseExpression {

    private Expression expression1; // First expression.
    private Expression expression2; // Second expression.

    /**
     * Initialize a new BinaryExpression.
     *
     * @param format      String format of the expression.
     * @param expression1 First expression.
     * @param expression2 Second expression.
     */
    public BinaryExpression(String format, Expression expression1, Expression expression2) {
        super(format); // format of toString.
        this.expression1 = expression1; //First expression.
        this.expression2 = expression2; //Second expression.
    }

    /**
     * @return First expression.
     */
    public Expression getExpression1() {
        return expression1;
    }

    /**
     * @return Second expression.
     */
    public Expression getExpression2() {
        return expression2;
    }

    /**
     * @return the String's format.
     */
    public String toString() {
        return String.format(super.getFormat(), expression1, expression2);
    }

    /**
     * @return all the variables in the expression.
     */
    public List<String> getVariables() {
        ArrayList<String> variables = new ArrayList<>();
        variables.addAll(expression1.getVariables());
        variables.addAll(expression2.getVariables());
        return variables;
    }
}
