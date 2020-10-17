
import java.util.Map;

/**
 * 205543317.
 */

public class Div extends BinaryExpression implements Expression {

    public static final double EPSILON = Math.pow(10, -14); // Checks equality, accuracy level 10 to the power of -14.

    /**
     * Initialize the expression with his format.
     *
     * @param expression1 first expression.
     * @param expression2 second expression.
     */
    public Div(Expression expression1, Expression expression2) {
        super("(%s / %s)", expression1, expression2);
    }

    /**
     * Evaluate the Expression recursively,
     * if it is dividing by zero that it throws an expression.
     *
     * @param assignment Mapping var to their values.
     * @return expression's value.
     * @throws Exception if divided by zero.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (Math.abs(super.getExpression2().evaluate(assignment)) < EPSILON) {
            throw new Exception("Math Error - dividing with zero");
        }
        return super.getExpression1().evaluate(assignment) / super.getExpression2().evaluate(assignment);
    }

    /**
     * Evaluate the Expression recursively,
     * if it is dividing by zero that it throws an expression.
     *
     * @return expression's value.
     * @throws Exception if divided by zero.
     */
    public double evaluate() throws Exception {
        if (Math.abs(super.getExpression2().evaluate()) < EPSILON) {
            throw new Exception("Math Error - dividing with zero");
        }
        return super.getExpression1().evaluate() / super.getExpression2().evaluate();
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
        return new Div(super.getExpression1().assign(var, expression), super.getExpression2().assign(var, expression));
    }

    /**
     * differentiating the current expression.
     *
     * @param var a variable.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Div(
                new Minus(
                        new Mult(super.getExpression1().differentiate(var),
                                super.getExpression2().assign(var, getExpression2())),
                        new Mult(super.getExpression2().differentiate(var),
                                super.getExpression1().assign(var, getExpression1()))),
                new Pow(super.getExpression2().assign(var, getExpression2()), new Num(2)));
    }

    /**
     * Simplifying the expression,
     * if its divided by one then its the same number (first expression).
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
        if (super.getExpression2().simplify().toString().equals("1")
                || super.getExpression2().simplify().toString().equals("1.0")) {
            return super.getExpression1().simplify();
        }
        if (super.getExpression1().simplify().toString().equals(super.getExpression2().toString())) {
            return new Num(1);
        }
        return new Div(super.getExpression1().simplify(), super.getExpression2().simplify());
    }
}
