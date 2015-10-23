package br.edu.aula.ed.arvores.learning;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
  * @author ycoppel@google.com (Yohann Coppel)
  * 
  * @param <T>
  *          Object's type in the tree.
*/
public class ArvoreMapa<T> {

  private T head;

  private ArrayList<ArvoreMapa<T>> leafs = new ArrayList<ArvoreMapa<T>>();

  private ArvoreMapa<T> parent = null;

  private HashMap<T, ArvoreMapa<T>> locate = new HashMap<T, ArvoreMapa<T>>();

  public ArvoreMapa(T head) {
    this.head = head;
    locate.put(head, this);
  }

  public void addLeaf(T root, T leaf) {
    if (locate.containsKey(root)) {
      locate.get(root).addLeaf(leaf);
    } else {
      addLeaf(root).addLeaf(leaf);
    }
  }

  public ArvoreMapa<T> addLeaf(T leaf) {
    ArvoreMapa<T> t = new ArvoreMapa<T>(leaf);
    leafs.add(t);
    t.parent = this;
    t.locate = this.locate;
    locate.put(leaf, t);
    return t;
  }

  public ArvoreMapa<T> setAsParent(T parentRoot) {
    ArvoreMapa<T> t = new ArvoreMapa<T>(parentRoot);
    t.leafs.add(this);
    this.parent = t;
    t.locate = this.locate;
    t.locate.put(head, this);
    t.locate.put(parentRoot, t);
    return t;
  }

  public T getHead() {
    return head;
  }

  public ArvoreMapa<T> getTree(T element) {
    return locate.get(element);
  }

  public ArvoreMapa<T> getParent() {
    return parent;
  }

  public Collection<T> getSuccessors(T root) {
    Collection<T> successors = new ArrayList<T>();
    ArvoreMapa<T> tree = getTree(root);
    if (null != tree) {
      for (ArvoreMapa<T> leaf : tree.leafs) {
        successors.add(leaf.head);
      }
    }
    return successors;
  }

  public Collection<ArvoreMapa<T>> getSubTrees() {
    return leafs;
  }

  public static <T> Collection<T> getSuccessors(T of, Collection<ArvoreMapa<T>> in) {
    for (ArvoreMapa<T> tree : in) {
      if (tree.locate.containsKey(of)) {
        return tree.getSuccessors(of);
      }
    }
    return new ArrayList<T>();
  }

  @Override
  public String toString() {
    return printTree(0);
  }

  private static final int indent = 2;

  private String printTree(int increment) {
    String s = "";
    String inc = "";
    for (int i = 0; i < increment; ++i) {
      inc = inc + " ";
    }
    s = inc + head;
    for (ArvoreMapa<T> child : leafs) {
      s += "\n" + child.printTree(increment + indent);
    }
    return s;
  }
}