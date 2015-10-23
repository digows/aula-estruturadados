package br.edu.aula.ed.arvores.learning.linked;
// Created by Lawrence PC Dol.  Released into the public domain.

/**
 * A uniformly weight-balanced linked tree, with a linked list in insertion
 * sequence.
 * <p>
 * This tree balances in the same way as does WeightedLinkedTree, but its linked
 * list is in insertion sequence rather than node-key sequence.
 *
 * @author Lawrence Dol
 * @since 2006.0616.1415
 */
public class InsertionLinkedTree<K, V> extends WeightedLinkedTree<K, V> {

	// *************************************************************************************************
	// INSTANCE PROPERTIES
	// *************************************************************************************************

	private final boolean relink; // whether to relink a node when it is
									// replaced

	// *************************************************************************************************
	// INSTANCE CONSTRUCTORS/INIT/CLOSE/FINALIZE
	// *************************************************************************************************

	/**
	 * Create a new insertion linked tree which always relinks a node when it is
	 * updated (re-added to the tree).
	 */
	public InsertionLinkedTree() {
		this(true);
	}

	/**
	 * Create a new insertion linked tree which optionally relinks a node when
	 * it is updated (re-added to the tree).
	 */
	public InsertionLinkedTree(boolean rlkrpl) {
		super();

		relink = rlkrpl;
	}

	// *************************************************************************************************
	// INSTANCE METHODS - ACCESSORS
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - TREE TRAVERSAL
	// *************************************************************************************************

	/**
	 * Returns the b-tree node at the top of the linked list and moves that node
	 * to the bottom of the linked list.
	 */
	public Node<K, V> getAndPutFirstNode() {
		Node<K, V> get = first; // node to get

		if (first != last) {
			linkNode(get, null, null, get);
		} // relink at end
		return get;
	}

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (SEARCH)
	// *************************************************************************************************

	/**
	 * Operation not supported.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public Node<K, V> findLastNodeLE(Object key) {
		throw new UnsupportedOperationException(
				"Cannot use findLastNodeLE() on InsertionLinkedTree because its list is not in key sequence.");
	}

	/**
	 * Operation not supported.
	 * 
	 * @throws UnsupportedOperationException
	 */
	public Node<K, V> findFirstNodeGE(Object key) {
		throw new UnsupportedOperationException(
				"Cannot use findLastNodeLE() on InsertionLinkedTree because its list is not in key sequence.");
	}

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (ADD)
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (DELETE)
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - BASE LEVEL RECURSIVE NODE OPERATIONS
	// *************************************************************************************************

	/**
	 * Note LE and GE not permitted for this tree implementation - find can be
	 * made more efficient for just a little extra code.
	 */
	Node<K, V> find(Object key, boolean le, boolean ge) {
		Node<K, V> cn; // current node
		int cr; // compare result

		cn = root;
		while (cn != null) {
			cr = cn.compareKey(key);
			if (cr < 0) {
				cn = cn.right;
			} else if (cr > 0) {
				cn = cn.left;
			} else /* (cr==0) */ {
				return cn;
			}
		}
		return null;
	}

	void linkNode(Node<K, V> old, Node<K, V> prv, Node<K, V> nxt, Node<K, V> put) {
		if (old != null) {
			if (!relink) {
				// LINK THE NEW NODE IN PLACE OF THE OLD ONE
				if (old != put) {
					put.prev = old.prev; // link put into node list
					put.next = old.next; // link put into node list
					if (old.prev == null) {
						first = put;
					} else {
						old.prev.next = put;
					}
					if (old.next == null) {
						last = put;
					} else {
						old.next.prev = put;
					}
					old.prev = null; // the replaced node no longer links into
										// the tree
					old.next = null; // the replaced node no longer links into
										// the tree
				}
				return;
			}

			// UNLINK THE OLD NODE (BEFORE RELINKING IT AT END OF THE LIST)
			if (old.prev == null) {
				first = old.next;
			} else {
				old.prev.next = old.next;
			}
			if (old.next == null) {
				last = old.prev;
			} else {
				old.next.prev = old.prev;
			}
			old.prev = null; // the replaced node no longer links into the tree
			old.next = null; // the replaced node no longer links into the tree
		}

		// LINK THE NEW OR REPLACED NODE AT END OF THE LIST
		put.prev = last; // put's previous is now the old last node
		put.next = null; // put's next is now nothing
		if (first == null) {
			first = put;
		} // if there is no existing first node, put is now it.
		else {
			last.next = put;
		} // otherwise put is now the existing last node's next
		last = put; // and put is now the last node
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
