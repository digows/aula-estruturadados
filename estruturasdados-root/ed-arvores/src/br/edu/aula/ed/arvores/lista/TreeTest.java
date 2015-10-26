package br.edu.aula.ed.arvores.lista;
import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;
/**
 * File GeneralTreeTest.java
 * Created on Jan 17, 2006
 */

public class TreeTest extends TestCase {
    GeneralTree<String> t1, t2, t3, t4, t5, t6, t7;
    
    /**
     * Creates the following GeneralTree (values are Strings):<pre>
     *        t1 =  one
     *            /  |  \
     *           /   |   \ 
     *          /    |    \
     *        two  three  four
     *         |          /  \
     *       five      six  seven</pre>
     *
     * @see junit.framework.TestCase#setUp()
     */

    protected void setUp() throws Exception {
        super.setUp();
        t7 = new GeneralTree<String>("seven");
        t6 = new GeneralTree<String>("six");
        t5 = new GeneralTree<String>("five");
        t4 = new GeneralTree<String>("four", t6, t7);
        t3 = new GeneralTree<String>("three");
        t2 = new GeneralTree<String>("two", t5);
        t1 = new GeneralTree<String>("one", t2, t3, t4);
    }

    /**
     * Test method for 'GeneralTree.GeneralTree(V, GeneralTree...)'
     */
    public void testGeneralTree() {
        assertEquals("one", t1.value);
    }

    /**
     * Test method for 'GeneralTree.firstChild()'
     */
    public void testFirstChild() {
        assertSame(t2, t1.firstChild());
        assertSame(t5, t2.firstChild());
        assertSame(t6, t4.firstChild());
        assertNull(t3.firstChild());
    }

    /**
     * Test method for 'GeneralTree.lastChild()'
     */
    public void testLastChild() {
        assertSame(t4, t1.lastChild());
        assertSame(t5, t2.lastChild());
        assertSame(t7, t4.lastChild());
        assertNull(t3.lastChild());
    }

    /**
     * Test method for 'GeneralTree.numberOfChildren()'
     */
    public void testNumberOfChildren() {
        assertEquals(3, t1.numberOfChildren());
        assertEquals(1, t2.numberOfChildren());
        assertEquals(0, t6.numberOfChildren());
    }

    /**
     * Test method for 'GeneralTree.child(int)'
     */
    public void testChild() {
        assertEquals(t2, t1.child(0));
        assertEquals(t3, t1.child(1));
        assertEquals(t4, t1.child(2));
        try {
            t1.child(-1);
            fail();
        }
        catch (NoSuchElementException e) {}
        try {
            t1.child(3);
            fail();
        }
        catch (NoSuchElementException e) {}
    }

    /**
     * Test method for 'GeneralTree.children()'
     */
    public void testChildren() {
        Iterator<GeneralTree<String>> iter = (Iterator<GeneralTree<String>>)t1.children();
        assertTrue(iter.hasNext());
        assertEquals(t2, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(t3, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(t4, iter.next());
        assertFalse(iter.hasNext());
        
        iter = t6.children();
        assertFalse(iter.hasNext());
    }

    /**
     * Test method for 'GeneralTree.addChild(GeneralTree)'
     */
    public void testAddChildGeneralTree() {
        GeneralTree<String> t8 = new GeneralTree<String>("eight");
        t4.addChild(t8);
        assertEquals(3, t4.numberOfChildren());
        assertEquals(t8, t4.lastChild());
        t5.addChild(t8);
        assertEquals(1, t5.numberOfChildren());
        assertEquals(t8, t5.firstChild());
        assertEquals(t8, t5.lastChild());
        try {
            t8.addChild(t1);
            fail();
        }        
        catch (IllegalArgumentException e) {}

        GeneralTree<String> tb = new GeneralTree<String>("b");
        GeneralTree<String> ta = new GeneralTree<String>("a", tb);
        GeneralTree<String> ty = new GeneralTree<String>("y");
        GeneralTree<String> tx = new GeneralTree<String>("x", ty);
        try {
            ty.addChild(ta);
            tb.addChild(tx);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }

    /**
     * Test method for 'GeneralTree.addChild(int, GeneralTree)'
     */
    public void testAddChildIntGeneralTree() {
        GeneralTree<String> ta = new GeneralTree<String>("a");
        GeneralTree<String> tb = new GeneralTree<String>("b");
        GeneralTree<String> tc = new GeneralTree<String>("c");
        GeneralTree<String> td = new GeneralTree<String>("d");
        
        t3.addChild(0, ta);
        t3.addChild(1, tc);
        t3.addChild(1, tb);
        assertEquals(3, t3.numberOfChildren());
        assertSame(ta, t3.firstChild());
        assertSame(tc, t3.lastChild());
        assertSame(tb, t3.child(1));
        try {
            t3.addChild(4, td);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }

    /**
     * Test method for 'GeneralTree.removeChild(int)'
     * @throws NoSuchElementException if index is out of bounds.
     */
    public void testRemoveChild() throws NoSuchElementException {
        assertEquals(t7, t4.removeChild(1));
        assertEquals(t6, t4.lastChild());
        assertEquals(t6, t4.removeChild(0));
        assertEquals(null, t4.lastChild());
        try {
            t4.removeChild(0);
            fail();
        }
        catch (NoSuchElementException e) {}
        try {
            t1.removeChild(3);
            fail();
        }
        catch (NoSuchElementException e) {}
    }

    /**
     * Test method for 'GeneralTree.hasChildren()'
     */
    public void testHasChildren() {
        assertTrue(t1.hasChildren());
        assertTrue(t2.hasChildren());
        assertFalse(t3.hasChildren());
    }

    /**
     * Test method for 'GeneralTree.equals(Object)'
     */
    public void testEqualsObject() {
        GeneralTree<String> e6 = new GeneralTree<String>("six");
        GeneralTree<String> e7 = new GeneralTree<String>("seven");
        GeneralTree<String> e4 = new GeneralTree<String>("four", e6, e7);
        assertEquals(e6, t6);
        assertEquals(e4, t4);
        assertNotSame(e4, t4);
        
        GeneralTree<String> e44 = new GeneralTree<String>("forty four", e6, e7);
        assertFalse(e4.equals(e44));
        
        GeneralTree<String> f8 = new GeneralTree<String>("eight");
        GeneralTree<String> f7 = new GeneralTree<String>("seven", f8);
        GeneralTree<String> f4 = new GeneralTree<String>("four", e6, f7);
        assertFalse(f4.equals(e4));
    }

    /**
     * Test method for 'GeneralTree.toString()'
     */
    public void testToString() {
        assertEquals("seven\n", t7.toString());
        assertEquals("one\n  two\n    five\n  three\n  four\n    six\n    seven\n",
                     t1.toString());
    }

}
