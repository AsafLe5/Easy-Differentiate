import java.util.List;
import java.util.Map;

/**
 * 205543317.
 */

public interface Expression {

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment.
     *
     * @param assignment Mapping var to their values.
     * @return the result.
     * @throws Exception when expression contains a variable which is not in the assignment.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Same like the method above but uses an empty assignment.
     *
     * @return the result.
     * @throws Exception no needed.
     */
    double evaluate() throws Exception;

    /**
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * @param var        a variable.
     * @param expression an expression.
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     */
    Expression assign(String var, Expression expression);

    /**
     * @param var a variable.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}