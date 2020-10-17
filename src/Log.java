
import java.util.Map;

/**
 * 205543317.
 */

public class Log extends BinaryExpression implements Expression {

    public static final double EPSILON = Math.pow(10, -14); // Checks equality, accuracy level 10 to the power of -14.

    /**
     * Initialize the expression with his format.
     *
     * @param expression1 first expression.
     * @param expression2 second expression.
     */
    public Log(Expression expression1, Expression expression2) {
        super("log(%s, %s)", expression1, expression2);
    }

    /**
     * Evaluate the Expression recursively,
     * if its base is 1, 0 or above or log's value 0 or above - throws an exception.
     *
     * @param assignment Mapping var to their values.
     * @return expression's value.
     * @throws Exception if its base is 1, 0 or above or log's value 0 or above.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (super.getExpression2().evaluate(assignment) <= 0) {
            throw new Exception("Math Error - log 0 or above");
        }
        if (super.getExpression1().evaluate(assignment) <= 0) {
            throw new Exception("Math Error - log base = 0 or above");
        }
        if (Math.abs(super.getExpression1().evaluate(assignment) - 1) < EPSILON) {
            throw new Exception("Math Error - log base = 1");
        }
        return Math.log(super.getExpression2().evaluate(assignment))
                / Math.log(super.getExpression1().evaluate(assignment));
    }

    /**
     * Evaluate the Expression recursively,
     * if its base is 1, 0 or above or log's value 0 or above - throws an exception.
     *
     * @return expression's value.
     * @throws Exception if its base is 1, 0 or above or log's value 0 or above.
     */
    public double evaluate() throws Exception {
        if (super.getExpression2().evaluate() <= 0) {
            throw new Exception("Math Error - log 0 or above");
        }
        if (super.getExpression1().evaluate() <= 0) {
            throw new Exception("Math Error - log base = 0 or above");
        }
        if (Math.abs(super.getExpression1().evaluate() - 1) < EPSILON) {
            throw new Exception("Math Error - log base = 1");
        }
        return Math.log(super.getExpression2().evaluate()) / Math.log(super.getExpression1().evaluate());
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
        return new Log(super.getExpression1().assign(var, expression), super.getExpression2().assign(var, expression));
    }

    /**
     * differentiating the current expression.
     *
     * @param var a variable.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        Var exp = new Var("e");
        exp.assign("e", new Num(Math.E));
        return new Div(new Num(1),
                new Mult(
                        new Log(exp, super.getExpression1().assign(var, getExpression1())),
                        super.getExpression2().assign(var, getExpression2())));
    }

    /**
     * Simplifying the expression,
     * if its the log and the base are the same numbers then return 1.
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
        if (super.getExpression1().simplify().toString().equals(super.getExpression2().simplify().toString())) {
            return new Num(1);
        }
        if (this.getVariables().isEmpty()) {
            if (Math.abs(Double.parseDouble(super.getExpression1().simplify().toString())
                    - Double.parseDouble(super.getExpression2().simplify().toString())) < EPSILON) {
                return new Num(1);
            }
        }
        return new Log(super.getExpression1().simplify(), super.getExpression2().simplify());
    }
}
