import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 205543317.
 */

public class Num implements Expression {

    private double number; // A number.

    /**
     * Sets the number.
     *
     * @param num a number.
     */
    public Num(double num) {
        this.number = num;
    }

    /**
     * Evaluate the number.
     *
     * @param assignment Mapping var to their values.
     * @return the number.
     * @throws Exception no needed.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.number;
    }

    /**
     * Evaluate the number.
     *
     * @return the number.
     * @throws Exception no needed.
     */
    public double evaluate() throws Exception {
        return this.number;
    }

    /**
     * convert the number to String.
     *
     * @return the number as a String.
     */
    public String toString() {
        return String.valueOf(this.number);
    }

    /**
     * @return empty list so it knows that this expression does not
     * posses any variables.
     */
    public List<String> getVariables() {
        return Collections.emptyList();
    }

    /**
     * Assign the var's given expressions, but not in this case.
     *
     * @param var        a variable.
     * @param expression an Expression.
     * @return the number because its not a variable.
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * differentiating the current expression.
     *
     * @param var a variable.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }


    /**
     * Simplifying the expression.
     *
     * @return the number because its already simplified.
     */
    public Expression simplify() {
        return this;
    }
}
