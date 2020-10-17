
import java.util.Map;

/**
 * 205543317.
 */

public class Mult extends BinaryExpression implements Expression {

    /**
     * Initialize the expression with his format.
     *
     * @param expression1 first expression.
     * @param expression2 second expression.
     */
    public Mult(Expression expression1, Expression expression2) {
        super("(%s * %s)", expression1, expression2);
    }

    /**
     * Evaluate the expression.
     *
     * @param assignment Mapping var to their values.
     * @return the var's
     * @throws Exception no needed.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getExpression1().evaluate(assignment) * super.getExpression2().evaluate(assignment);
    }

    /**
     * Evaluate the expression.
     *
     * @return the var's
     * @throws Exception no needed.
     */
    public double evaluate() throws Exception {
        return super.getExpression1().evaluate() * super.getExpression2().evaluate();
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
        return new Mult(super.getExpression1().assign(var, expression), super.getExpression2().assign(var, expression));
    }

    /**
     * differentiating the current expression.
     *
     * @param var a variable.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Plus(
                new Mult(super.getExpression1().differentiate(var),
                        super.getExpression2().assign(var, getExpression2())),
                new Mult(super.getExpression2().differentiate(var),
                        super.getExpression1().assign(var, getExpression1())));
    }

    /**
     * Simplifying the expression,
     * if its an expression multiply by 1 then its the same expression.
     * if its an expression multiply by 1 then its equal to zero.
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
        if (super.getExpression1().simplify().toString().equals("1")
                || super.getExpression1().simplify().toString().equals("1.0")) {
            return super.getExpression2().simplify();
        }
        if (super.getExpression2().simplify().toString().equals("1")
                || super.getExpression2().simplify().toString().equals("1.0")) {
            return super.getExpression1().simplify();
        }
        if (super.getExpression1().simplify().toString().equals("0")
                || super.getExpression1().simplify().toString().equals("0.0")) {
            return new Num(0.0);
        }
        if (super.getExpression2().simplify().toString().equals("0")
                || super.getExpression2().simplify().toString().equals("0.0")) {
            return new Num(0.0);
        }
        return new Mult(super.getExpression1().simplify(), super.getExpression2().simplify());

    }

}
