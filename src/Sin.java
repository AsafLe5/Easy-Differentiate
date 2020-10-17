
import java.util.Map;

/**
 * 205543317.
 */

public class Sin extends UnaryExpression implements Expression {

    /**
     * Initialize the expression with his format.
     *
     * @param expression an Expression.
     */
    public Sin(Expression expression) {
        super("sin(%s)", expression);
    }

    /**
     * Evaluate the Expression recursively,
     * if it is known angle then return its value right away.
     *
     * @param assignment Mapping var to their values.
     * @return expression's value.
     * @throws Exception no needed.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (super.getExpression().evaluate(assignment) % 180 == 0) {
            return 0;
        }
        if (super.getExpression().evaluate(assignment) % 360 == 90) {
            return 1;
        }
        if (super.getExpression().evaluate(assignment) % 360 == 270) {
            return -1;
        }
        return Math.sin(Math.toRadians(super.getExpression().evaluate(assignment)));
    }

    /**
     * Evaluate the Expression recursively,
     * if it is known angle then return its value right away.
     *
     * @return expression's value.
     * @throws Exception no needed.
     */
    public double evaluate() throws Exception {
        if (super.getExpression().evaluate() % 180 == 0) {
            return 0;
        }
        if (super.getExpression().evaluate() % 360 == 90) {
            return 1;
        }
        if (super.getExpression().evaluate() % 360 == 270) {
            return -1;
        }
        return Math.sin(Math.toRadians(super.getExpression().evaluate()));
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
        return new Sin(super.getExpression().assign(var, expression));
    }

    /**
     * differentiating the current expression.
     *
     * @param var a variable.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Mult(super.getExpression().differentiate(var), new Cos(super.getExpression()));
    }

    /**
     * Simplifying the expression.
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        if (this.getVariables().isEmpty()) {
            try {
                return new Num(this.evaluate());
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        return new Sin(super.getExpression().simplify());
    }
}
