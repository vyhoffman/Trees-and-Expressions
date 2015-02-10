package tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for representing simple arithmetic expressions.
 * @author <Your name goes here>
 * @version Feb 10, 2015
 */
public class Expression {
    Tree<String> expressionTree;
    
    /**
     * Constructs a Tree<String> representing the given arithmetic expression,
     * then verifies that the newly created Tree is valid as an expression.
     * If the Tree is invalid, throws an IllegalArgumentException.<br>
     * Here are the validity rules:<ul>
     * <li>The value of each node must be one of "+", "-", "*", "/",
     *     or a String representing an unsigned integer.</li>
     * <li>If a node has value "+" or "*", it must have two or more children.</li>
     * <li>If a node has value "-" or "/", it must have exactly two children.</li>
     * <li>If a node contains a numeric string, it must be a leaf.</li></ul>
     * Note that the input parameter uses prefix notation, for example:
     * "+ (5 10 -( *(15 20) 25) 30)"
     * @param expression The String representation of the expression to be constructed.
     */
    public Expression(String expression) {
        expressionTree = Tree.parse(expression);
        if (!valid(expressionTree)) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }

    /**
     * Tests whether the given Tree represents a valid Expression.
     * @param tree The input tree.
     * @return <code>true</code> iff the Tree is a valid Expression.
     */
    private boolean valid(Tree<String> tree) {
        return false; // TODO Replace with correct result
    }
    
    /**
     * Evaluates this Expression.
     * @return The value of this Expression.
     */
    public int evaluate() {
        return evaluate(expressionTree);
    }
    
    /**
     * Evaluates the given Tree, which must represent a valid Expression.
     * @return The value of this Expression.
     */
    private int evaluate(Tree<String> tree) {
        // Helper method for evaluate()
        return -1; // TODO Replace with correct result
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toString(expressionTree);
    }
    
    private static String toString(Tree<String> tree) {
        // Helper method for toString()
        return null; // TODO Replace with correct result        
    }
}
