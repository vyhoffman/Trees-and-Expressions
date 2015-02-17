package tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


/**Tests the Tree class.
 * @author Nicki Hoffman
 *
 */
public class TreeTest {
	Tree<String> node1, tree1, tree2;

    @Before
    public void setUp() throws Exception {
    	node1 = new Tree("one");
    	tree1 = new Tree("two", node1);
    	tree2 = new Tree("four", node1, tree1, node1);

    }
    
//    @Test
//    public final void testPrint() { //commented out b/c IO but was easy way to check
//    	node1.print();
//    	tree1.print();
//    	tree2.print();
//    }

    /**
     * Test method for {@link tree.Tree#hashCode()}
     */
    @Test
    public final void testHashCode() {
    	Tree<String> node1b = new Tree("one");
    	Tree<String> tree1b = new Tree("two", node1);
    	Tree<String> tree2b = new Tree("four", node1, tree1, node1);
    	
    	assertEquals(node1.hashCode(), node1b.hashCode());
    	assertEquals(tree1.hashCode(), tree1b.hashCode());
    	assertEquals(tree2.hashCode(), tree2b.hashCode());
    }

    /**
     * Test method for Tree constructor
     */
    @Test
    public final void testTree() { //trees were constructed in setup, check they're as expected
    	assertEquals("two", tree1.getValue());
    	assertEquals(node1, tree1.getChild(0));
    	assertEquals("four", tree2.getValue());
    	assertEquals(tree1, tree2.getChild(1));
    }

//    /**
//     * exception Test method for Tree constructor
//     * commenting out because instructions don't say check for valid value input
//     * so I don't
//     */
//    @Test(expected=NullPointerException.class)
//    public final void testTreeException2() {
//    	new Tree(null);
//    }

    /**
     * Null child input exception Test method for Tree constructor
     */
    @Test(expected=NullPointerException.class)
    public final void testTreeNullChild() {
    	new Tree("value", null);
    }
    
// null or empty value should probably fail too but it didn't say so in instructions
    // issue came up sort of in piazza @154 but was not really resolved -
    // no official update to the assignment was made, just says null nodes aren't
    // allowed, and attempting to add them does fail. I'm trying to follow the 
    // instructions that we were actually given.

    /**
     * Test method for {@link tree.Tree#setValue(Object)}
     */
    @Test
    public final void testSetValue() {
    	node1.setValue("three");
    	assertEquals("three", node1.getValue());
    	Tree<Integer> tree = new Tree(0);
    	tree.setValue((Integer) 5);
    	assertEquals((Integer) 5, tree.getValue());
    }

//    /**
//     * Null input exception test method for setValue - I think it should throw NPexception
//     * and it did when I was checking for type match but wasn't sure I should do that
//     * and again, instructions don't say validate value input
//     */
//    @Test(expected=NullPointerException.class)
//    public final void testSetValueNull() {
//    	node1.setValue(null);
//    }

    /**
     * Test method for {@link tree.Tree#getValue()}
     */
    @Test
    public final void testGetValue() {
        assertEquals("one", node1.getValue());
    }

    /**
     * Test method for addChild()
     */
    @Test
    public final void testAddChildIntTreeOfV() {
    	tree2.addChild(2, tree1);
    	assertEquals(node1, tree2.getChild(0));
    	assertEquals(tree1, tree2.getChild(1));
    	assertEquals(tree1, tree2.getChild(2));
    	assertEquals(node1, tree2.getChild(3));
    	tree2.addChild(2, node1);
    	assertEquals(node1, tree2.getChild(0));
    	assertEquals(tree1, tree2.getChild(1));
    	assertEquals(node1, tree2.getChild(2));
    	assertEquals(tree1, tree2.getChild(3));
    	assertEquals(node1, tree2.getChild(4));
    }
    
    /**
     * Illegal argument exception test method for indexed addChild()
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testAddChildIntTreeOfVException() {
    	node1.addChild(0, tree1);
    }

    /**
     * Null input exception Test method for indexed addChild()
     */
    @Test(expected=NullPointerException.class)
    public final void testAddChildIntTreeOfVNull() {
    	node1.addChild(0, null);
    }

    /**
     * Test method for addChild()
     */
    @Test
    public final void testAddChildTreeOfV() {
    	tree2.addChild(2, tree1);
    	tree2.addChild(2, node1);
    	assertEquals(node1, tree2.getChild(0));
    	assertEquals(tree1, tree2.getChild(1));
    	assertEquals(node1, tree2.getChild(2));
    	assertEquals(tree1, tree2.getChild(3));
    	assertEquals(node1, tree2.getChild(4));
    }

    /**
     * Null input exception test method for addChild()
     */
    @Test(expected=NullPointerException.class)
    public final void testAddChildTreeOfVNull() {
    	node1.addChild(null);
    }
    
    /**
     * Illegal argument exception test method for addChild()
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testAddChildException() {
    	node1.addChild(tree1);
    }

    /**
     * Test method for addChildren()
     */
    @Test
    public final void testAddChildren() {
    	tree2.addChildren(tree1, tree1, node1, tree1);
    	assertEquals(node1, tree2.getChild(0)); // checking it didn't mess up the first ones
    	assertEquals(tree1, tree2.getChild(1));
    	assertEquals(node1, tree2.getChild(2));
    	assertEquals(tree1, tree2.getChild(3)); // checking it added correctly
    	assertEquals(tree1, tree2.getChild(4));
    	assertEquals(node1, tree2.getChild(5));
    	assertEquals(tree1, tree2.getChild(6));
    }

    /**
     * Null input exception Test method for addChildren
     */
    @Test(expected=NullPointerException.class)
    public final void testAddChildrenNull() {
    	node1.addChildren(null);
    	//Ohhhkay these throw correctly because they try to check whether the child (null) contains the parent. 
    }
    
    /**
     * Illegal argument exception test method for addChildren()
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testAddChildrenException() {
    	node1.addChildren(tree1, tree2);
    }

    /**
     * Test method for getNumberOfChildren()
     */
    @Test
    public final void testGetNumberOfChildren() {
    	assertEquals(0, node1.getNumberOfChildren());
    	assertEquals(1, tree1.getNumberOfChildren());
    	assertEquals(3, tree2.getNumberOfChildren());
    }

    /**
     * Test method for {@link tree.Tree#getChild(int)}
     */
    @Test
    public final void testGetChild() {
    	assertEquals(node1, tree1.getChild(0));
    	assertEquals(tree1, tree2.getChild(1));
    	assertEquals(node1, tree2.getChild(2));
    }

//    @Test(expected=IndexOutOfBoundsException.class) // kind of pointless since it's runtime
//    public final void testGetChildException1() {
//        node1.getChild(0);
//    }
//
//    @Test(expected=IndexOutOfBoundsException.class)
//    public final void testGetChildException2() {
//        tree2.getChild(3);
//    }
//
//    @Test(expected=IndexOutOfBoundsException.class)
//    public final void testGetChildException3() {
//        node1.getChild(-1);
//    }

    /**
     * Test method for {@link tree.Tree#iterator()}
     */
    @Test
    public final void testIterator() {
    	Iterator<Tree<String>> iter = tree2.iterator();
    	for (int i = 0; i < tree2.getNumberOfChildren(); i++) {
    		assertTrue(iter.hasNext());
    		assertEquals(tree2.getChild(i), iter.next());
    	}
    }

    /**
     * Test method for contains()
     */
    @Test
    public final void testContains() {
    	assertTrue(tree1.contains(node1));
    	assertTrue(tree2.contains(tree1));
    	assertTrue(tree2.contains(node1));
    	assertFalse(node1.contains(tree1));
    	assertFalse(node1.contains(tree2));
    	assertFalse(tree1.contains(tree2));
    }

    /**
     * Test method for toString()
     */
    @Test
    public final void testToString() {
    	String expected1 = "one ";
    	String expected2 = "two(one ) ";
    	String expected3 = "four(one two(one ) one ) ";
    	assertEquals(expected1, node1.toString());
    	assertEquals(expected2, tree1.toString());
    	assertEquals(expected3, tree2.toString());
    }

    /**
     * Test method for equals()
     */
    @Test
    public final void testEqualsObject() {
    	Tree<String> node1b = new Tree("one");
    	Tree<String> tree1b = new Tree("two", node1);
    	Tree<String> tree2b = new Tree("four", node1, tree1, node1);
    	
    	assertEquals(node1b, node1);
    	assertFalse(node1b == node1);
    	assertEquals(tree1b, tree1);
    	assertFalse(tree1b == tree1);
    	assertEquals(tree2b, tree2);
    	assertFalse(tree2b == tree2);
    	
    	assertFalse(node1.equals(null));
    	
    	assertNotEquals(node1, tree1);
    	assertNotEquals(tree1, tree2);
    	node1b.setValue("seventeen");
    	assertNotEquals(node1, node1b);
    }

    /**
     * Test method for {@link tree.Tree#parse(java.lang.String)}
     */
    @Test
    public final void testParseString() {
    	assertEquals(node1, Tree.parse("one"));
    	//Tree.parse("one").print();
    	assertEquals(tree1, Tree.parse("two( one )"));
    	assertEquals(tree2, Tree.parse("four( one two( one) one)"));
//    	PushbackStringTokenizer t = new PushbackStringTokenizer(tree2.toString().trim());
//    	while (t.hasNext()) System.out.printf("%s ", t.next());
//    	System.out.printf("\n%s\n", tree2.toString());
    	// was testing out results from tokenizer above. it turned trailing whitespace into null token
    	assertEquals(tree2, Tree.parse(tree2.toString().trim()));
    	assertTrue(Tree.parse("") == null);
    }
    
    /**
     * Illegal argument exception test method for {@link tree.Tree#parse(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testParseStringException1() {
    	Tree.parse("(parentless)");
    }
    
    /**
     * Illegal argument exception test method for {@link tree.Tree#parse(java.lang.String)}
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testParseStringException2() {
    	Tree.parse("a(missing");
    }

}
