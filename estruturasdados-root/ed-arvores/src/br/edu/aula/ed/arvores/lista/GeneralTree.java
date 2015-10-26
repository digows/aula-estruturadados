package br.edu.aula.ed.arvores.lista;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements general trees. Each object in the GeneralTree class represents a single
 *  node; however, nodes are linked together, so that any node may be considered
 *  as the "root" of a complete tree.
 *  
 * File GeneralTree.java
 * Created on Jan 17, 2006
 * @param <V> The type of datum to be held in this GeneralTree node.
 */
public class GeneralTree<V> {
    
    /** The data held in this GeneralTree node. */
    public V value;
    
    /** The children of this GeneralTree node. */
    private ArrayList<GeneralTree<V>> children;
    
    /**
     * Creates a GeneralTree node with the given value and zero or more children.
     * The order of the <code>initialChildren</code> parameters is the
     * order in which the children of this GeneralTree node will occur.
     * 
     * @param value The data to put in this individual GeneralTree node.
     * @param initialChildren Nodes to be added to this GeneralTree node as children.
     */
    public GeneralTree(V value, GeneralTree<V>... initialChildren) {
        this.value = value;
        children = new ArrayList<GeneralTree<V>>();
        for (GeneralTree<V> child : initialChildren) {
            children.add(child);
        }
    }
    
    /**
     * Returns the first child of this tree (which may be null).
     * 
     * @return The first child, or <code>null</code> if this node has no children.
     */
    public GeneralTree firstChild() {
        if (children.size() > 0) {
            return children.get(0);
        }
        else return null;
    }
    
    /**
     * Returns the last child of this tree (which may be null).
     * 
     * @return The last child, or <code>null</code> if this node has no children.
     */
    public GeneralTree lastChild() {
        if (children.size() > 0) {
            return children.get(children.size() - 1);
        }
        else return null;
    }
    
    /**
     * Returns the number of (immediate) children of this node.
     * 
     * @return The number of children.
     */
    public int numberOfChildren() {
        return children.size();
    }
    
    /**
     * Returns the index-th child of this tree (counting from zero, as with arrays).
     * Throws a <code>NoSuchElementException</code> if index is less than zero or
     * greater than or equal to the number of children.
     * 
     * @param index The index of the child to be returned.
     * @return The <code>index</code>-th child of this GeneralTree node.
     * @throws NoSuchElementException if the index is out of range.
     */
    public GeneralTree<V> child(int index) throws NoSuchElementException {
        if (index < 0 || index >= children.size()) {
            throw new NoSuchElementException("Call of child(" + index + ")");
        }
        else return children.get(index);
    }
    
    /**
     * Returns an iterator for the children of this tree. The iterator supports
     * <code>remove()</code> as well as <code>hasNext()</code> and <code>next()</code>.
     * 
     * @return An iterator for the children of this tree.
     */
    public Iterator<GeneralTree<V>> children() {
        return children.iterator();
    }
    
    /**
     * Adds newChild as the new last child of this tree, provided that the
     * resultant tree is loop-free.
     * 
     * @param newChild The GeneralTree to be added as the last child of this GeneralTree.
     * @throws IllegalArgumentException if adding <code>newChild</code> would
     *       create a loop in this GeneralTree.
     */
    public void addChild(GeneralTree<V> newChild)
            throws IllegalArgumentException {
        if (search(this, newChild)) {
            throw new IllegalArgumentException();
        }
        children.add(newChild);
    }
    
    /**
     * Adds <code>newChild</code> as the new <code>index</code>-th child of
     * this tree (counting from zero), provided that the resultant tree is
     * valid, that is, free of loops. The child previously at this index,
     * and all subsequent children, are "shifted right" (their index is
     * increased) to make room for the new child.
     * <p>
     * If the <code>index</code> is less than zero or greater than (<i>not</i>
     * greater than or equal to) the current number of children, or if the
     * operation would result in an invalid tree, the tree is unchanged and
     * the method throws an IllegalArgumentException.
     * 
     * @param index The location at which to add the new child GeneralTree.
     * @param newChild The GeneralTree to be added as a child.
     * @throws IllegalArgumentException if <code>index</code> is invalid, or
     *      if adding the child would create a loop in this GeneralTree.
     */
    public void addChild(int index, GeneralTree<V> newChild)
            throws IllegalArgumentException {
        if (index < 0 || index > children.size()) {
            throw new IllegalArgumentException();
        }
        children.add(index, newChild);
    }
    
    /**
     * Removes and returns the <code>index</code>-th child of this tree, or
     * throws a <code>NoSuchElementException</code> if the index is illegal.
     * (This method simply removes the child from the list of children of
     * this tree; no major tree surgery is involved. The removed subtree
     * remains intact.
     * 
     * @param index The index of the child GeneralTree to be removed.
     * @return The removed GeneralTree.
     * @throws NoSuchElementException if the given <code>index</code> is invalid.
     */
    public GeneralTree removeChild(int index)
            throws NoSuchElementException {
        if (index < 0 || index >= children.size()) {
            throw new NoSuchElementException();
        }
        return children.remove(index);
    }
    
    /**
     * Returns <code>true</code> if this node has children.
     * @return code>true</code> if this node has children.
     */
    public boolean hasChildren() {
        return children.size() > 0;
    }
    
    /**
     * Tests whether the parameter is equal to this GeneralTree. In order to be
     * considered equal, the parameter must be a GeneralTree, the value in that
     * GeneralTree node must equal the value in this GeneralTree node, and both Trees
     * must have the same number of children, where the children are
     * pairwise equal.
     * 
     * @param object The object (presumably a GeneralTree) against which to
     * compare this GeneralTree.
     * @return <code>true</code> if the two trees are equal.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeneralTree)) return false;
        GeneralTree that = (GeneralTree)object;
        if (!(this.value.equals(that.value))) return false;
        if (this.children.size() != that.children.size()) return false;
        for (int i = 0; i < children.size(); i++) {
            if (!(this.child(i).equals(that.child(i)))) return false;
        }
        return true;
    }
    
    /**
     * Returns a multiline representation of this GeneralTree. Each line contains
     * the <code>toString()</code> representation of the value in that node,
     * terminated with a newline (<code>\n</code>). Each child is indented
     * two spaces under its parent. 
     * 
     * @return A String representation of the GeneralTree rooted at this GeneralTree node.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toString(this, "");
    }

    private String toString(GeneralTree<V> node, String indent) {
        String result = indent + node.value.toString() + '\n';
        Iterator<GeneralTree<V>> iter = node.children.iterator();
        while (iter.hasNext()) {
            result += toString(iter.next(), indent + "  ");
        }
        return result;
    }
    
    /**
     * Tests whether the given node is in the given tree.
     * 
     * @param node The node to search for.
     * @param tree the tree to be searched.
     * @return <code>true</code> if the node is found, else <code>false</code>.
     */
    private boolean search(GeneralTree<V> node, GeneralTree<V> tree) {
        if (node == tree) return true;
        Iterator<GeneralTree<V>> iter = tree.children();
            while (iter.hasNext()) {
                if (search(node, iter.next())) {
                    return true;
                }
            }
        return false;
    }
}
