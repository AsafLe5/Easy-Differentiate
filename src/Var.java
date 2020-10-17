import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 205543317.
 */

public class Var implements Expression {

    private String variable; // A variable.

    /**
     * Sets the variable.
     *
     * @param var a variable.
     */
    public Var(String var) {
        this.variable = var;
    }

    /**
     * Evaluate the variable.
     *
     * @param assignment Mapping var to their values.
     * @return the variable.
     * @throws Exception when the expression contains a variable which isn't in the assignment.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!assignment.containsKey(this.variable)) {
            throw new Exception("The expression contains a variable which isn't in the assignment");
        }
        return assignment.get(this.variable);
    }

    /**
     * can not evaluate the variable without the assignment.
     *
     * @return nothing.
     * @throws Exception because variable has no value.
     */
    public double evaluate() throws Exception {
        throw new Exception("variable without value");
    }

    /**
     * @return the list now containing the current variable.
     */
    public List<String> getVariables() {
        return Collections.singletonList(String.valueOf(this.variable));
    }

    /**
     * @return the variable as is because its already a string.
     */
    public String toString() {
        return this.variable;
    }

    /**
     * Assign the var's given expressions.
     *
     * @param var        a variable.
     * @param expression an Expression.
     * @return the given expression if its equal to the variable,
     * the variable if its not equal then return the Var as is.
     */
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        }
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
        if (this.variable.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    /**
     * @return the variable as is because its already simplified.
     */
    public Expression simplify() {
        return this;
    }
}
