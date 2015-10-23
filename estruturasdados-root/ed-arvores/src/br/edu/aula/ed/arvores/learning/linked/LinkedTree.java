package br.edu.aula.ed.arvores.learning.linked;
// Created by Lawrence PC Dol.  Released into the public domain.

//
// Source is licensed for any use, provided this copyright notice is retained.
// No warranty for any purpose whatsoever is implied or expressed.  The author
// is not liable for any losses of any kind, direct or indirect, which result
// from the use of this software.

//package <your-package-here>;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A self-balancing combination linked list / binary tree. Provides direct
 * access by key as well as doubly linked list node traversal. In addition, this
 * provides searching for less the or equal to key and greater than or equal to
 * key, allowing database-like positioning between nodes.
 * <p>
 * At some future point this class will add support for the Collection and List.
 * <p>
 * Threading Design : [x] Single Threaded [ ] Threadsafe [ ] Immutable [ ]
 * Isolated
 *
 * @author Lawrence Dol
 * @since 2006.0217.2115
 */
public abstract class LinkedTree<K, V> extends Object implements Iterable<LinkedTree.Node<K, V>> {

	// *************************************************************************************************
	// INSTANCE PROPERTIES
	// *************************************************************************************************

	/** The root node of the tree; null when empty. */
	Node<K, V> root;

	/** The first node of the list; null when empty. */
	Node<K, V> first;

	/** The last node of the list; null when empty. */
	Node<K, V> last;

	// *************************************************************************************************
	// INSTANCE CONSTRUCTORS/INIT/CLOSE/FINALIZE
	// *************************************************************************************************

	/**
	 * Create a new linked tree.
	 */
	LinkedTree() {
		root = null;
		first = null;
		last = null;
	}

	/**
	 * Closes the tree, deleting the list of objects. This doesn't actually
	 * invalidate the tree and is identical to calling <code>clear()</code>, but
	 * is provided as a convenience, and to implement the Closeable interface.
	 */
	public void close() {
		clear();
	}

	/**
	 * Clears the tree.
	 */
	public void clear() {
		root = null;
		first = null;
		last = null;
	}

	// *************************************************************************************************
	// INSTANCE METHODS - ACCESSORS
	// *************************************************************************************************

	/**
	 * Returns the current number of elements in the b-tree.
	 */
	public abstract int size();

	/**
	 * Calculates the maximum depth of the b-tree by recursing through all
	 * elements. This method is primarily of interest when debugging.
	 * <p>
	 * If the supplied recursion limit is reached, then -1 is returned.
	 */
	public int maximumDepth(int lmt) {
		return nodeMaximumDepth(root, lmt);
	}

	/**
	 * Calculates the average depth of the b-tree by recursing through all
	 * elements. This method is primarily of interest when debugging.
	 *
	 * If the supplied recursion limit is reached, then -1 is returned.
	 */
	public int averageDepth(int lmt) {
		return nodeAverageDepth(root, lmt);
	}

	// *************************************************************************************************
	// INSTANCE METHODS - TREE TRAVERSAL
	// *************************************************************************************************

	/**
	 * Returns the root node to the caller. This is typically only used for
	 * debugging or processing the tree in an unordered sequence. Note that the
	 * root node can change with any addition or removal from the tree, so this
	 * should not be retained for later access.
	 */
	public Node<K, V> getRootNode() {
		return root;
	}

	/**
	 * Returns the b-tree node that is first in the sequence.
	 */
	public Node<K, V> getFirstNode() {
		return first;
	}

	/**
	 * Returns the b-tree node that is last in the sequence.
	 */
	public Node<K, V> getLastNode() {
		return last;
	}

	/**
	 * Calculates the maximum depth of the b-tree node by recursing through all
	 * elements. This method is primarily of interest when debugging.
	 * <p>
	 * If the supplied recursion limit is reached, then -1 is returned.
	 */
	public int nodeMaximumDepth(Node<K, V> node, int lmt) {
		return nodeMaximumDepth(node, 0, lmt);
	}

	private int nodeMaximumDepth(Node<K, V> node, int depth, int lmt) {
		int d1, d2; // depth 1 and 2

		if (depth > lmt) {
			return -1;
		}
		if (node == null) {
			return depth;
		}

		if ((d1 = nodeMaximumDepth(node.getLeftNode(), (depth + 1), lmt)) == -1) {
			return -1;
		}
		if ((d2 = nodeMaximumDepth(node.getRightNode(), (depth + 1), lmt)) == -1) {
			return -1;
		}
		return (d1 > d2 ? d1 : d2);
	}

	/**
	 * Calculates the average depth of the b-tree node by recursing through all
	 * elements. This method is primarily of interest when debugging.
	 *
	 * If the supplied recursion limit is reached, then -1 is returned.
	 */
	public int nodeAverageDepth(Node<K, V> node, int lmt) {
		int[] ttlcnt;

		ttlcnt = new int[2];
		nodeAverageDepth(node, 0, lmt, ttlcnt);
		return (ttlcnt[1] == 0 ? 0 : ttlcnt[0] / ttlcnt[1]);
	}

	private boolean nodeAverageDepth(Node<K, V> node, int depth, int lmt, int[] ttlcnt) {
		int d1, d2; // depth 1 and 2

		if (depth > lmt) {
			return false;
		}

		if (node == null) {
			ttlcnt[0] += depth;
			ttlcnt[1]++;
		} else {
			if (!nodeAverageDepth(node.getLeftNode(), (depth + 1), lmt, ttlcnt)) {
				return false;
			}
			if (!nodeAverageDepth(node.getRightNode(), (depth + 1), lmt, ttlcnt)) {
				return false;
			}
		}
		return true;
	}

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (SEARCH)
	// *************************************************************************************************

	/**
	 * Returns the Node that matches the key supplied.
	 *
	 * How the supplied key is interpreted is determined by the node's
	 * `compareKey()` method. The value supplied to this method may be just the
	 * key, or an entire object, depending on how the node's `compareKey()`
	 * method is coded.
	 * <p>
	 * 
	 * @param key
	 *            The object to be used as the key; not used by the tree code,
	 *            passed to the node's `compareKey()` method as supplied.
	 * @return The Node, or null if not found.
	 */
	public Node<K, V> findNode(Object key) {
		return find(key, false, false);
	}

	/**
	 * Returns the value of the Node that matches the key supplied. This is a
	 * convenience method; it is loosely equivalent to
	 * <code>findNode().getValue()</code>, but avoids the NullPointerException
	 * that would occur if the value is not found.
	 * <p>
	 * How the supplied key is interpreted is determined by the node's
	 * `compareKey()` method. The value supplied to this method may be just the
	 * key, or an entire object, depending on how the node's `compareKey()`
	 * method is coded.
	 * <p>
	 * 
	 * @param key
	 *            The object to be used as the key; not used by the tree code,
	 *            passed to the node's `compareKey()` method as supplied.
	 * @return The value of the Node as returned by getValue(), or null if not
	 *         found.
	 */
	public Object findNodeValue(Object key) {
		Node<K, V> tn;

		if ((tn = find(key, false, false)) != null) {
			return tn.getValue();
		}
		return null;
	}

	/**
	 * Returns the last Node of the set of nodes that are equal to or less than
	 * the key supplied.
	 * <p>
	 * How the supplied key is interpreted is determined by the node's
	 * `compareKey()` method. The value supplied to this method may be just the
	 * key, or an entire object, depending on how the node's `compareKey()`
	 * method is coded.
	 * <p>
	 * 
	 * @param key
	 *            The object to be used as the key; not used by the tree code,
	 *            passed to the node's `compareKey()` method as supplied.
	 * @return The Node, or null if not found.
	 */
	public Node<K, V> findLastNodeLE(Object key) {
		return find(key, true, false);
	}

	/**
	 * Returns the first Node of the set of nodes that are equal to or greater
	 * than the key supplied.
	 * <p>
	 * How the supplied key is interpreted is determined by the node's
	 * `compareKey()` method. The value supplied to this method may be just the
	 * key, or an entire object, depending on how the node's `compareKey()`
	 * method is coded.
	 * <p>
	 * 
	 * @param key
	 *            The object to be used as the key; not used by the tree code,
	 *            passed to the node's `compareKey()` method as supplied.
	 * @return The Node, or null if not found.
	 */
	public Node<K, V> findFirstNodeGE(Object key) {
		return find(key, false, true);
	}

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (ADD)
	// *************************************************************************************************

	/**
	 * Adds a new node to the tree. The supplied value is stored as the
	 * reference; the application must ensure that the contents referred to are
	 * not subsequently changed.
	 * <p>
	 * If the data is changed while stored within the tree, the results are
	 * unpredictable.
	 * <p>
	 * If the value already exists within the tree the method throws an
	 * exception.
	 * <p>
	 * 
	 * @param nod
	 *            The node to put.
	 */
	public void putNode(Node<K, V> nod) {
		putNode(nod, false);
	}

	/**
	 * Adds a new node to the tree. The supplied value is stored as a reference;
	 * the application must ensure that the contents referred to are not
	 * subsequently changed.
	 * <p>
	 * If the data is changed while stored within the tree, the results are
	 * unpredictable.
	 * <p>
	 * 
	 * @param nod
	 *            The node to put.
	 * @param rpl
	 *            Whether to replace the object if it exists, or throw an
	 *            exception.
	 *            <p>
	 * @throws Escape
	 *             OBJEXISTS: If the object already exists in the tree and rpl
	 *             is false.
	 */
	public void putNode(Node<K, V> nod, boolean rpl) {
		if (root == null) {
			root = insert(null, root, null, null, nod, rpl);
		} else {
			root = insert(null, root, root.prev, root.next, nod, rpl);
		}
	}

	// *************************************************************************************************
	// INSTANCE METHODS - PUBLIC NODE OPERATIONS (DELETE)
	// *************************************************************************************************

	/**
	 * Removes a node from the tree.
	 * <p>
	 * If a node identified by the key does not exist within the tree, then an
	 * exception is thrown.
	 * <p>
	 * 
	 * @throws Escape
	 *             Code OBJNOTFND: The object does not exist in the tree.
	 */
	public void removeNode(Object key) {
		removeNode(key, true);
	}

	/**
	 * Removes a node from the tree.
	 * <p>
	 * If a node identified by the key does not exist within the tree, then an
	 * exception is thrown if rqd is true.
	 * <p>
	 * 
	 * @param key
	 *            The key for the node to remove.
	 * @param rqd
	 *            Whether the value is required to exist.
	 * @throws Escape/OBJNOTFND
	 *             If the key does not exist in the tree.
	 */
	public void removeNode(Object key, boolean rqd) {
		root = remove(null, root, key, rqd);
	}

	// *************************************************************************************************
	// INSTANCE METHODS - BASE LEVEL RECURSIVE NODE OPERATIONS
	// *************************************************************************************************

	/**
	 * Note le and ge are mutually exclusive.
	 */
	Node<K, V> find(Object key, boolean le, boolean ge) {
		for (Node<K, V> nod = root; nod != null;) {
			int nodcmpkey = nod.compareKey(key);
			if (nodcmpkey < 0) {
				if (nod.right == null) {
					if (le) {
						return nod;
					}
					if (ge) {
						return nod.next;
					}
				}
				nod = nod.right;
			} else if (nodcmpkey > 0) {
				if (nod.left == null) {
					if (le) {
						return nod.prev;
					}
					if (ge) {
						return nod;
					}
				}
				nod = nod.left;
			} else { // equal
				if (le) {
					for (Node<K, V> nxt; (nxt = nod.next) != null && nxt.compareKey(key) == 0;) {
						nod = nxt;
					}
				}
				if (ge) {
					for (Node<K, V> prv; (prv = nod.prev) != null && prv.compareKey(key) == 0;) {
						nod = prv;
					}
				}
				return nod;
			}
		}
		return null;
	}

	abstract Node<K, V> insert(Node<K, V> par, Node<K, V> nod, Node<K, V> prv, Node<K, V> nxt, Node<K, V> put,
			boolean rpl);

	abstract Node<K, V> remove(Node<K, V> par, Node<K, V> nod, Object key, boolean rqd);

	// *************************************************************************************************
	// INSTANCE METHODS - SUBCLASS UTILITIES
	// *************************************************************************************************

	/**
	 * For InsertionLinkedTree to override so insert does not violate DRY.
	 */
	void linkNode(Node<K, V> old, Node<K, V> prv, Node<K, V> nxt, Node<K, V> put) {
		if (put != old) { // if replacing with the same node, nothing to do
			if (old == null) {
				// INSERTING NEW NODE
				put.prev = prv;
				put.next = nxt;
			} else {
				// REPLACING EXISTING NODE
				put.prev = old.prev;
				old.prev = null;
				put.next = old.next;
				old.next = null;
				old.prev = null; // ensure the replaced node no longer links
									// into the tree
				old.next = null; // ensure the replaced node no longer links
									// into the tree
			}

			if (put.prev == null) {
				first = put;
			} // I am now first
			else {
				put.prev.next = put;
			} // my previous's next is now me
			if (put.next == null) {
				last = put;
			} // I am now last
			else {
				put.next.prev = put;
			} // my next's previous is now me
		}
	}

	/**
	 * Swap the parent node with its left child, effectively promoting the child
	 * and demoting the parent. This method can be called only if the parent has
	 * a left child.
	 * 
	 * <pre>
	 *     [4]                     [2]
	 *     / \                     / \
	 *   [2] [5]   will become   [1] [4]
	 *   / \                         / \
	 * [1] [3]                     [3] [5]
	 * </pre>
	 */
	Node<K, V> promoteLeft(Node<K, V> nod) {
		Node<K, V> lft; // left node (nod=parent node)

		lft = nod.left;
		nod.left = lft.right;
		lft.right = nod;
		return lft;
	}

	/**
	 * Swap the parent node with its right child, effectively promoting the
	 * child and demoting the parent. This method can be called only if the
	 * parent has a right child.
	 * 
	 * <pre>
	 *   [2]                         [4]
	 *   / \                         / \
	 * [1] [4]     will become     [2] [5]
	 *     / \                     / \
	 *   [3] [5]                 [1] [3]
	 * </pre>
	 */
	Node<K, V> promoteRight(Node<K, V> nod) {
		Node<K, V> rgt; // right node (nod=parent node)

		rgt = nod.right;
		nod.right = rgt.left;
		rgt.left = nod;
		return rgt;
	}

	// *************************************************************************************************
	// INSTANCE METHODS - COLLECTIONS IMPLEMENTATIONS: COLLECTION
	// *************************************************************************************************

	/**
	 * Return an iterator over the nodes of this LinkedTree.
	 * <p>
	 * Iterator.remove() is supported.
	 */
	public Iterator<Node<K, V>> iterator() {
		return new NodeIterator();
	}

	/**
	 * Return an iterator over the keys of this LinkedTree.
	 * <p>
	 * Iterator.remove() is supported.
	 */
	public Iterator<K> keyIterator() {
		return new KeyIterator();
	}

	/**
	 * Return an iterator over the values of this LinkedTree.
	 * <p>
	 * Iterator.remove() is supported.
	 */
	public Iterator<V> valueIterator() {
		return new ValueIterator();
	}

	/**
	 * Return a Map.Entry iterator over the nodes of this LinkedTree.
	 * <p>
	 * <b>Note:</b> Very importantly, the Map.Entry objects returned by this
	 * iterator are valid only for the specific iteration cycle in which they
	 * are returned. This is different from the standard Map contract which at
	 * least implies that entry objects are valid until the conclusion of
	 * iteration, and/or until the map is subsequently modified.
	 * <p>
	 * Map.Entry.getKey() returns LinkedTree.Node.getKey(). Map.Entry.getValue()
	 * returns LinkedTree.Node.getValue(). Map.Entry.setValue() is supported
	 * only if the underlying node supports Node.setValue(). Iterator.remove()
	 * is supported.
	 */
	public Iterator<Map.Entry<K, V>> mapEntryIterator() {
		return new MapEntryIterator();
	}

	// *************************************************************************************************
	// INSTANCE METHODS - COLLECTIONS IMPLEMENTATIONS: LIST
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - COLLECTIONS IMPLEMENTATIONS: MAP
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE METHODS - DEBUGGING
	// *************************************************************************************************

	public void dump(PrintWriter wtr) {
		dump(wtr, "");
	}

	public void dump(PrintWriter wtr, String pfx) {
		for (LinkedTree.Node tn = getFirstNode(); tn != null; tn = tn.getNextNode()) {
			wtr.println(pfx + tn);
		}
		wtr.println(pfx + "--");
	}

	public void dumpTree(PrintWriter wtr) {
		dumpTree(wtr, "");
	}

	public void dumpTree(PrintWriter wtr, String pfx) {
		dumpTree(wtr, pfx, getRootNode());
	}

	private void dumpTree(PrintWriter wtr, String pfx, LinkedTree.Node tn) {
		if (tn == null) {
			return;
		}

		dumpTree(wtr, (pfx + "  "), tn.getLeftNode());
		wtr.println(pfx + tn);
		dumpTree(wtr, (pfx + "  "), tn.getRightNode());
	}

	// *************************************************************************************************
	// INSTANCE CLASSES - NODE ITERATOR
	// *************************************************************************************************

	private class NodeIterator extends Object implements java.util.Iterator<Node<K, V>> {
		private Node<K, V> curNode, nxtNode; // current node, next node

		NodeIterator() {
			super();

			curNode = null;
			nxtNode = LinkedTree.this.getFirstNode();
		}

		public boolean hasNext() {
			return (nxtNode != null);
		}

		public Node<K, V> next() {
			if (nxtNode == null) {
				throw new NoSuchElementException("No more elements available from LinkedTree iterator");
			}

			curNode = nxtNode;
			nxtNode = nxtNode.getNextNode();
			return curNode;
		}

		public void remove() {
			if (curNode == null) {
				throw new IllegalStateException(
						"Cannot invoke an iterator's remove() a second time before next() is invoked.");
			}
			LinkedTree.this.removeNode(curNode.getKey(), true);
			curNode = null;
		}

		/** Sets the current node's value. */
		public V set(V val) {
			if (curNode == null) {
				throw new IllegalStateException(
						"Cannot invoke an iterator's set() after remove() before next() is invoked.");
			}
			return curNode.setValue(val);
		}
	} // END INNER CLASS

	// *************************************************************************************************
	// INSTANCE CLASSES - MAP ENTRY ITERATOR
	// *************************************************************************************************

	private class MapEntryIterator extends Object implements java.util.Iterator<Map.Entry<K, V>> {
		private NodeIterator nodeItr;

		MapEntryIterator() {
			super();

			nodeItr = new NodeIterator();
		}

		public boolean hasNext() {
			return nodeItr.hasNext();
		}

		public Map.Entry<K, V> next() {
			return nodeItr.next();
		}

		public void remove() {
			nodeItr.remove();
		}
	} // END INNER CLASS

	// *************************************************************************************************
	// INSTANCE CLASSES - KEY ITERATOR
	// *************************************************************************************************

	private class KeyIterator extends Object implements java.util.Iterator<K> {
		private NodeIterator nodeItr;

		KeyIterator() {
			super();

			nodeItr = new NodeIterator();
		}

		public boolean hasNext() {
			return nodeItr.hasNext();
		}

		public K next() {
			return nodeItr.next().getKey();
		}

		public void remove() {
			nodeItr.remove();
		}
	} // END INNER CLASS

	// *************************************************************************************************
	// INSTANCE INNER CLASSES - VALUE ITERATOR
	// *************************************************************************************************

	private class ValueIterator extends Object implements java.util.Iterator<V> {
		private NodeIterator nodeItr;

		ValueIterator() {
			super();

			nodeItr = new NodeIterator();
		}

		public boolean hasNext() {
			return nodeItr.hasNext();
		}

		public V next() {
			return nodeItr.next().getValue();
		}

		public void remove() {
			nodeItr.remove();
		}
	} // END INNER CLASS

	// *************************************************************************************************
	// STATIC CLASSES - ABSTRACT NODE
	// *************************************************************************************************

	/**
	 * The abstract base for a tree node. This class is extended to create a
	 * node which can be stored in a tree. This is done instead of including a
	 * "value" object in the node in order to conserve memory for trees with a
	 * large number of nodes. By having the code which uses tree supply the
	 * concrete node an additional 16 byte overhead (the overhead for an object
	 * and a reference to it) can be eliminated; this is not insignificant if
	 * the tree contains, for example, 1,000,000 nodes or more.
	 * <p>
	 * The overhead for a LinkedTree node is one Object + 1 int + 4 references -
	 * on a 32 bit system this is 12+4+16=32 bytes.
	 */
	static public abstract class Node<K, V> extends Object implements Map.Entry<K, V> {
		Node<K, V> left; // left node
		Node<K, V> right; // right node
		Node<K, V> prev; // previous node by key (creates linked list)
		Node<K, V> next; // next node by key (creates linked list)

		int balancer; // balancer code

		/**
		 * Create a LinkedTree node.
		 */
		protected Node() {
			left = null;
			right = null;
			prev = null;
			next = null;

			balancer = 0;
		}

		/**
		 * Return a string representation of this node value.
		 */
		public String toString() {
			K key = getKey();
			V val = getValue();
			if (key == this && val == this) {
				return nodeString();
			} else if (key == this) {
				return nodeString() + "->" + val;
			} else if (val == this) {
				return key + "->" + nodeString();
			} else {
				return key + "->" + val;
			}
		}

		public int hashCode() {
			K key = getKey();
			V val = getValue();

			return ((key == null ? 0 : key.hashCode()) ^ (val == null ? 0 : val.hashCode()));
		}

		public boolean equals(Object obj) {
			if (!(obj instanceof Map.Entry)) {
				return false;
			}

			Map.Entry oth = (Map.Entry) obj;
			Object othkey = oth.getKey();
			Object othval = oth.getValue();
			Object thskey = getKey();
			Object thsval = getValue();

			return ((thskey == null ? othkey == null : thskey.equals(othkey))
					&& (thsval == null ? othval == null : thsval.equals(othval)));
		}

		/**
		 * Return a string representation of this node's linkage.
		 */
		public String nodeString() {
			return ("LinkedTree.Node[This=" + System.identityHashCode(this) + ", Left=" + System.identityHashCode(left)
					+ ", Right=" + System.identityHashCode(right) + ", Prev=" + System.identityHashCode(prev)
					+ ", Next=" + System.identityHashCode(next) + ", Balancer=" + balancer + "]");
		}

		/**
		 * Returns a reference to this node's left node. This is used to process
		 * the list in unordered sequence, typically only for debugging.
		 */
		public Node<K, V> getLeftNode() {
			return left;
		}

		/**
		 * Returns a reference to this node's left node. This is used to process
		 * the list in unordered sequence, typically only for debugging.
		 */
		public Node<K, V> getRightNode() {
			return right;
		}

		/**
		 * Returns a reference to this node's previous node in ordered sequence.
		 */
		public Node<K, V> getPrevNode() {
			return prev;
		}

		/**
		 * Returns a reference to this node's next node in ordered sequence.
		 */
		public Node<K, V> getNextNode() {
			return next;
		}

		/**
		 * Matches this node's key to another key. The supplied key may be the
		 * key of a node already in the tree or an arbitrary key supplied to one
		 * of the various public LinkedTree methods.
		 * <p>
		 * The default implementation defers to <code>compareKey</code> for a
		 * return of 0.
		 *
		 * @return True if the object matches the key according to the objects's
		 *         matching rules.
		 */
		public boolean matchesKey(Object key) {
			return compareKey(key) == 0;
		}

		/**
		 * Compares this node's key to another key. The supplied key may be the
		 * key of a node already in the tree or an arbitrary key supplied to one
		 * of the various public LinkedTree methods.
		 *
		 * @return A negative integer, zero, or a positive integer as this
		 *         node's key is less than, equal to, or greater than the
		 *         supplied key.
		 */
		public abstract int compareKey(Object key);

		/**
		 * Return the reference to this node's key (which may be the same object
		 * as the value).
		 * <p>
		 * <b>NOTE:</b> If any mutable properties of the object returned affect
		 * result of compareTo(), then the caller of this method must clone
		 * value returned; otherwise the LinkedTree will be corrupted if these
		 * fields are changed.
		 */
		public abstract K getKey();

		/**
		 * Return the reference to this node's value (which may be the same
		 * object as the key).
		 * <p>
		 * <b>NOTE:</b> If any mutable properties of the object returned affect
		 * result of compareTo(), then the caller of this method must clone
		 * value returned; otherwise the LinkedTree will be corrupted if these
		 * fields are changed.
		 */
		public abstract V getValue();

		/**
		 * Set the reference to this node's value. This operation is optional
		 * and the default implementation throws an
		 * OperationNotSupportedException.
		 * <p>
		 * <b>NOTEL</b>Nodes for which the value is also the key <b>must</b>
		 * reject this operation, since changing the value implicitly changes
		 * the key, and therefore the node's location in the tree and/or linked
		 * list.
		 * 
		 * @return The previous node value.
		 * @throws UnsupportedOperationException
		 *             If the node cannot support changing its value without
		 *             being removed and readded to the tree.
		 */
		public V setValue(V val) {
			throw new UnsupportedOperationException("LinkedTree.Node.setValue() not supported by " + getClass());
		}
	} /* END PUBLIC CLASS */

	// *************************************************************************************************
	// STATIC CLASSES - SIMPLE NODE WITH VALUE
	// *************************************************************************************************

	/**
	 * A node object for a very simple LinkedTree where the key and value are
	 * the same Comparable object.
	 * <p>
	 * Some applications will want to use a customized node which is at once the
	 * node, key and value in order to conserve memory. This should be done by
	 * extending Node rather than this or any other concrete Node
	 * implementation.
	 */
	static public class ValueNode<T extends Comparable<T>> extends Node<T, T> {
		protected T value; // node value

		public ValueNode(T val) {
			value = val;
		}

		public T getKey() {
			return value;
		}

		public T getValue() {
			return value;
		}

		public int compareKey(Object key) {
			T cmp = uncheckedCast(key);
			return value.compareTo(cmp);
		}
	} /* END PUBLIC CLASS */

	// *************************************************************************************************
	// STATIC CLASSES - SIMPLE MAP ENTRY NODE WITH VALUE
	// *************************************************************************************************

	/**
	 * A node object for a very simple LinkedTree with a comparable key and
	 * separate value.
	 * <p>
	 * This kind of node is ideally suited to a java.util.Map-style
	 * implementation.
	 * <p>
	 * Some applications will want to use a customized node which is at once the
	 * node, key and value in order to conserve memory. This should be done by
	 * extending Node rather than this or any other concrete Node
	 * implementation.
	 */
	static public class MapNode<K extends Comparable<K>, V> extends Node<K, V> {
		protected K key; // node key
		protected V value; // node value

		public MapNode(K key, V val) {
			this.key = key;
			this.value = val;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V val) {
			V prv = value;
			value = val;
			return prv;
		}

		public int compareKey(Object key) {
			K cmp = uncheckedCast(key);
			return this.key.compareTo(cmp);
		}
	} /* END PUBLIC CLASS */

	// *************************************************************************************************
	// STATIC NESTED CLASSES - ESCAPE (COULD DROP THIS FOR RUNTIME EXCEPTION
	// *************************************************************************************************

	static public class Escape extends RuntimeException {
		int code; // associated specific error code

		Escape(int cod, String txt) {
			super(txt);

			code = cod;
		}

		static public final int OBJEXISTS = 1; // error codes
		static public final int OBJNOTFND = 2; // error codes
	}

	// *************************************************************************************************
	// STATIC PROPERTIES
	// *************************************************************************************************

	// *************************************************************************************************
	// STATIC INIT & MAIN
	// *************************************************************************************************

	// *************************************************************************************************
	// STATIC METHODS
	// *************************************************************************************************

	@SuppressWarnings("unchecked")
	static private <T> T uncheckedCast(final Object obj) {
		return (T) obj;
	}

} /* END PUBLIC CLASS */
