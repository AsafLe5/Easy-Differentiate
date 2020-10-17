
import java.util.Map;

/**
 * 205543317.
 */

public class Pow extends BinaryExpression implements Expression {

    /**
     * Initialize the expression with his format.
     *
     * @param expression1 first expression.
     * @param expression2 second expression.
     */
    public Pow(Expression expression1, Expression expression2) {
        super("(%s^%s)", expression1, expression2);
    }

    /**
     * Evaluate the Expression recursively.
     *
     * @param assignment Mapping var to their values.
     * @return expression's value.
     * @throws Exception when the power of negative number is below one.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (super.getExpression2().evaluate(assignment) < 1 && super.getExpression1().evaluate(assignment) < 0) {
            throw new Exception("Math Error - power of negative number is below one.");
        }
        return Math.pow(super.getExpression1().evaluate(assignment), super.getExpression2().evaluate(assignment));
    }

    /**
     * Evaluate the Expression recursively.
     *
     * @return expression's value.
     * @throws Exception when the power of negative number is below one.
     */
    public double evaluate() throws Exception {
        if (super.getExpression2().evaluate() < 1 && super.getExpression1().evaluate() < 0) {
            throw new Exception("Math Error - power of negative number is below one.");
        }
        return Math.pow(super.getExpression1().evaluate(), super.getExpression2().evaluate());
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
        return new Pow(super.getExpression1().assign(var, expression), super.getExpression2().assign(var, expression));
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
        return
                new Mult(
                        new Pow(
                                super.getExpression1().assign(var, getExpression1()),
                                super.getExpression2().assign(var, getExpression2())),
                        new Plus(
                                new Mult(super.getExpression1().differentiate(var),
                                        new Div(super.getExpression2().assign(var, getExpression2()),
                                                super.getExpression1().assign(var, getExpression1()))),
                                new Mult(super.getExpression2().differentiate(var),
                                        new Log(exp, super.getExpression1().assign(var, getExpression1())))));
    }

    /**
     * Simplifying the expression,
     * if its power is zero then its value is one.
     * if its base is zero then it's value is zero no matter what's the power.
     * if its base is one, its stays one, no matter whats the expression in the power.
     * if its power is one then it stays the same expression.
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
        if (super.getExpression1().simplify().toString().equals("0")
                || super.getExpression1().simplify().toString().equals("0.0")) {
            return new Num(0);
        }
        if (super.getExpression2().simplify().toString().equals("0")
                || super.getExpression2().simplify().toString().equals("0.0")) {
            return new Num(1);
        }
        if (super.getExpression1().simplify().toString().equals("1")
                || super.getExpression1().simplify().toString().equals("1.0")) {
            return new Num(1);
        }
        if (super.getExpression2().simplify().toString().equals("1")
                || super.getExpression2().simplify().toString().equals("1.0")) {
            return this.getExpression1().simplify();
        }
        return new Pow(super.getExpression1().simplify(), super.getExpression2().simplify());
    }
}
