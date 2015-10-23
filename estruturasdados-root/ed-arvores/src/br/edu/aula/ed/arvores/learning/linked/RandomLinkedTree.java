package br.edu.aula.ed.arvores.learning.linked;
// Created by Lawrence PC Dol.  Released into the public domain.

//
// Source is licensed for any use, provided this copyright notice is retained.
// No warranty for any purpose whatsoever is implied or expressed.  The author
// is not liable for any losses of any kind, direct or indirect, which result
// from the use of this software.

//package <your-package-here>;
import java.util.Random;

/**
 * A random-balanced linked tree (a linked tree-heap).
 * <p>
 * Relative to WeightedLinkedTree this tree provides poorer performance for
 * searching (1:1.42), with better performance for put (1.30:1) and remove
 * (1.33:1).
 * <p>
 * Use this tree when the combined total of puts and removes exceed searches.
 *
 * @author Lawrence Dol
 * @since 2006.0217.1454
 */
public class RandomLinkedTree<K, V> extends LinkedTree<K, V> {

	// *************************************************************************************************
	// INSTANCE PROPERTIES
	// *************************************************************************************************

	private Random random; // random value for ordering
	int size;

	// *************************************************************************************************
	// INSTANCE CONSTRUCTORS/INIT/CLOSE/FINALIZE
	// *************************************************************************************************

	/**
	 * Create a new tree heap. The supplied comparator is used to provide the
	 * application with specific control over how the node values are
	 * interpreted.
	 */
	public RandomLinkedTree() {
		this(new Random()); // can init with specific value to fix tree
							// structure, for testing, debugging, etc
	}

	/**
	 * Create a new tree heap. The supplied comparator is used to provide the
	 * application with specific control over how the node values are
	 * interpreted.
	 */
	public RandomLinkedTree(Random rnd) {
		super();
		random = rnd; // can init with specific value to fix tree structure, for
						// testing, debugging, etc
	}

	// *************************************************************************************************
	// INSTANCE METHODS - ACCESSORS
	// *************************************************************************************************

	/**
	 * Returns the current number of elements in the tree.
	 */
	public int size() {
		return size;
	}

	// *************************************************************************************************
	// INSTANCE METHODS - TREE TRAVERSAL
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (SEARCH)
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (ADD)
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (DELETE)
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - BASE LEVEL RECURSIVE NODE OPERATIONS
	// *************************************************************************************************
	Node<K, V> insert(Node<K, V> par, Node<K, V> nod, Node<K, V> prv, Node<K, V> nxt, Node<K, V> put, boolean rpl) {
		int cr; // compare result

		if (nod == null) {
			put.left = null;
			put.right = null;
			put.balancer = random.nextInt();
			size++;
			linkNode(nod, prv, nxt, put);
			return put;
		} else if ((cr = nod.compareKey(put.getKey())) < 0) {
			nod.right = insert(nod, nod.right, nod, nod.next, put, rpl);
			if (nod.balancer > nod.right.balancer) {
				nod = promoteRight(nod);
			}
			return nod;
		} else if (cr > 0) {
			nod.left = insert(nod, nod.left, nod.prev, nod, put, rpl);
			if (nod.balancer > nod.left.balancer) {
				nod = promoteLeft(nod);
			}
			return nod;
		} else /* cr==0 */ {
			if (!rpl) {
				throw new Escape(Escape.OBJNOTFND, "Node<K,V> already exists in the tree (" + nod + ")");
			}
			put.left = nod.left;
			put.right = nod.right;
			put.balancer = nod.balancer;
			linkNode(nod, prv, nxt, put);
			return put;
		}
	}

	Node<K, V> remove(Node<K, V> par, Node<K, V> nod, Object key, boolean rqd) {
		Node<K, V> nsn; // new sub node
		int cr; // comparator result

		if (nod == null) {
			if (rqd) {
				throw new Escape(Escape.OBJNOTFND, "Node<K,V> does not exist in the tree (" + key + ")");
			}
			return null;
		} else if ((cr = nod.compareKey(key)) < 0) {
			nod.right = remove(nod, nod.right, key, rqd);
			return nod;
		} else if (cr > 0) {
			nod.left = remove(nod, nod.left, key, rqd);
			return nod;
		} else if (nod.left != null || nod.right != null) { // cr==0
			if (nod.left == null) {
				nod = promoteRight(nod);
			} else if (nod.right == null) {
				nod = promoteLeft(nod);
			} else if (nod.left.balancer > nod.right.balancer) {
				nod = promoteRight(nod);
			} else {
				nod = promoteLeft(nod);
			}
			return remove(par, nod, key, rqd);
		} else { // cr==0
			if (nod.prev == null) {
				first = nod.next;
			} // my next is now first
			else {
				nod.prev.next = nod.next;
			} // my previous's next is now my next
			if (nod.next == null) {
				last = nod.prev;
			} // my previous is now last
			else {
				nod.next.prev = nod.prev;
			} // my next's previous is now my previous
			nod.left = nod.right = null; // unlink node from tree
			nod.prev = nod.next = null; // unlink node from list
			size--;
			return null;
		}
	}

	// *************************************************************************************************
	// INSTANCE INNER CLASSES
	// *************************************************************************************************

	// *************************************************************************************************
	// STATIC NESTED CLASSES
	// *************************************************************************************************

	// *************************************************************************************************
	// STATIC PROPERTIES
	// *************************************************************************************************

	// *************************************************************************************************
	// STATIC INIT & MAIN
	// *************************************************************************************************

	// *************************************************************************************************
	// STATIC METHODS
	// *************************************************************************************************

} /* END PUBLIC CLASS */
