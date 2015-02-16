package tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for representing simple arithmetic expressions.
 * @author Nicki Hoffman
 * @version Feb 16, 2015
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
        //expressionTree.print();
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
    	if ("+".equals(tree.getValue()) || "*".equals(tree.getValue())) {
    		if (tree.getNumberOfChildren() < 2) return false;
    	} else if ("-".equals(tree.getValue()) || "/".equals(tree.getValue())) {
    		if (tree.getNumberOfChildren() != 2) return false;
    	} else if (isUnsignedInt(tree.getValue())) {
    		if (tree.getNumberOfChildren() != 0) return false;
    	} else {
    		return false;
    	}
    	Iterator<Tree<String>> iter = tree.iterator();
    	while (iter.hasNext()) {
    		if (!valid(iter.next())) return false;
    	}
        return true;
    }
    
    /**Tests whether string s can be evaluated as an unsigned integer.
     * @param s The string to test.
     * @return boolean true if string is a nonnegative integer, else false
     */
    private boolean isUnsignedInt(String s) {
    	int n;
    	try {
    		n = (int) Integer.parseInt(s); //if I wanted long int, this wouldn't suffice, I know
    	} catch(Exception e) {
    		return false;
    	}
    	return n >= 0;
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
    	if (tree.getNumberOfChildren() == 0) return (int) Integer.parseInt(tree.getValue());
    	Iterator<Tree<String>> iter = tree.iterator();
    	int result = evaluate(iter.next());
    	while (iter.hasNext()) {
    		if ("*".equals(tree.getValue())) result *= evaluate(iter.next());
    		else if ("+".equals(tree.getValue())) result += evaluate(iter.next());
    		else if ("-".equals(tree.getValue())) result -= evaluate(iter.next());
    		else if ("/".equals(tree.getValue())) result /= evaluate(iter.next());
    	}
        return result; // TODO test
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /**Returns this expression as a one-line, formatted string.
     * @return String representation of the tree
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toString(expressionTree);
    }
    
    /**Returns this expression as a one-line, formatted string.
     * @param tree Tree to convert to string
     * @return String representation of the tree
     */
    private static String toString(Tree<String> tree) {
        // Helper method for toString()
    	if (tree.getNumberOfChildren() == 0) return tree.getValue();
    	Iterator<Tree<String>> iter = tree.iterator();
    	String result = "(" + toString(iter.next()).trim();
    	while (iter.hasNext()) {
    		result += " " + tree.getValue() + " " + toString(iter.next()).trim();
    	}
        return result + ")";      
    }
}
