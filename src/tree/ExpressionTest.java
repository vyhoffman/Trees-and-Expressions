package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**Tests the Expression class.
 * @author Nicki Hoffman
 *
 */
public class ExpressionTest {
	Expression e1, e2, e3, e4, e5;

    @Before
    public void setUp() throws Exception {
    	e1 = new Expression("1");
    	e2 = new Expression("+(1 1)");
    	e3 = new Expression("*(4 /(6 3) 1)");
    	e4 = new Expression("-(1 1)");
    	e5 = new Expression("/(1 1)");
    }

    /**
     * Test method for {@link tree.Expression#Expression(java.lang.String)}
     */
    @Test
    public final void testExpression() {
    	e1 = new Expression("1");
    	e2 = new Expression("+(1 1)");
    	e3 = new Expression("*(4 /(6 3) 1)");
    	e4 = new Expression("-(1 1)");
    	e5 = new Expression("/(1 1)");
    }

    /**
     * Exception test method for {@link tree.Expression#Expression(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testExpressionException1() {
    	new Expression("+(1)");
    }

    /**
     * Exception test method for {@link tree.Expression#Expression(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testExpressionException2() {
    	new Expression("*(4)");
    }

    /**
     * Exception test method for {@link tree.Expression#Expression(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testExpressionException3() {
    	new Expression("/(1 1 1)");
    }

    /**
     * Exception test method for {@link tree.Expression#Expression(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testExpressionException4() {
    	new Expression("-(1 1 1)");
    }

    /**
     * Exception test method for {@link tree.Expression#Expression(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testExpressionException5() {
    	new Expression("1(1)");
    }

    /**
     * Exception test method for {@link tree.Expression#Expression(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testExpressionException6() {
    	new Expression("one");
    }

    /**
     * Exception test method for {@link tree.Expression#Expression(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testExpressionException7() {
    	new Expression("(1)");
    }

    /**
     * Test method for {@link tree.Expression#evaluate()}
     */
    @Test
    public final void testEvaluate() {
    	assertEquals(1, e1.evaluate());
    	assertEquals(2, e2.evaluate());
    	assertEquals(8, e3.evaluate());
    	assertEquals(0, e4.evaluate());
    	assertEquals(1, e5.evaluate());
    }

    /**
     * Test method for {@link tree.Expression#toString()}
     */
    @Test
    public final void testToString() {
    	assertEquals("1", e1.toString());
    	assertEquals("(1 + 1)", e2.toString());
    	assertEquals("(4 * (6 / 3) * 1)", e3.toString());
    	assertEquals("(1 - 1)", e4.toString());
    	assertEquals("(1 / 1)", e5.toString());
    }

}
