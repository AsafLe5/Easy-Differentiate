
import java.util.Map;

/**
 * 205543317.
 */

public class Neg extends UnaryExpression implements Expression {

    /**
     * Initialize the expression with his format.
     *
     * @param expression an expression.
     */
    public Neg(Expression expression) {
        super("(-%s)", expression);
    }

    /**
     * Evaluate the Expression recursively.
     *
     * @param assignment Mapping var to their values.
     * @return expression's value.
     * @throws Exception no needed.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -super.getExpression().evaluate(assignment);
    }

    /**
     * Evaluate the Expression recursively.
     *
     * @return expression's value.
     * @throws Exception no needed.
     */
    public double evaluate() throws Exception {
        return -super.getExpression().evaluate();
    }


    /**
     * Assign the var's given expressions.
     *
     * @param var        a variable.
     * @param expression an Expression.
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Neg(super.getExpression().assign(var, expression));
    }

    /**
     * differentiating the current expression.
     *
     * @param var a variable.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Neg(super.getExpression().differentiate(var));
    }

    /**
     * Simplifying the expression.
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        return new Neg(super.getExpression().simplify());
    }

}
