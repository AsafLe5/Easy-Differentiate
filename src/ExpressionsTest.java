import java.util.function.DoubleToIntFunction;

/**
 * 205543317.
 */

public class ExpressionsTest {

    /**
     * Initialize a expression and prints it in a few different ways.
     *
     * @param args gets nothing.
     * @throws Exception when evaluation goes wrong.
     */
    public static void main(String[] args) throws Exception {
        Expression ex; // New expression.
        ex = new Plus(new Plus(new Mult(new Num(2), new Var("x")),
                new Sin(new Mult(new Num(4), new Var("y")))), new Pow(new Var("e"), new Var("x")));
        System.out.println(ex.toString()); // Prints expression toString version.
        java.util.Map<String, Double> assignment = new java.util.TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", Math.E);
        System.out.println(ex.evaluate(assignment)); //Evaluate expression with the assignment.
        ex = ex.differentiate("x");
        System.out.println(ex.toString()); // Prints expression toString version after differentiate.
        System.out.println(ex.evaluate(assignment)); // Prints expression evaluate after differentiate.
        System.out.println(ex.simplify()); // Prints expression after differentiate and simplifying.

        System.out.println();
        ex = new Pow(new Plus(new Plus(new Var("x"), new Var("y")),new Plus(new Var("z") ,new Var("w"))),new Num(4));

        ex = ex.differentiate("x");
        System.out.println(ex.simplify());

        //(4.0*(((x+y)+(z+w))^3.0))
        System.out.println(ex.toString());
        System.out.println();

        ex = new Sin(new Pow(new Var("e"), new Div(new Sin(new Var("x")),new Cos(new Var("x")))));
        ex = ex.differentiate("x");
        System.out.println(ex.toString());
        //sin(e^(sin(x)/cos(x))
    }
}
