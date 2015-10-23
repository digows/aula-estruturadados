package br.edu.aula.ed.arvores.learning.linked;
// Created by Lawrence PC Dol.  Released into the public domain.

/**
 * A uniformly weight-balanced linked tree.
 * <p>
 * Relative to RandomLinkedTree this tree provides better performance for
 * searching (1.42:1), with poorer performance for put (1:1.30) and remove
 * (1:1.33).
 * <p>
 * Use this tree when searches exceed the combined total of puts and removes.
 *
 * @author Lawrence Dol
 * @since 2006.0217.1454
 */
public class WeightedLinkedTree<K, V> extends LinkedTree<K, V> {

	// *************************************************************************************************
	// INSTANCE PROPERTIES
	// *************************************************************************************************

	// *************************************************************************************************
	// INSTANCE CONSTRUCTORS/INIT/CLOSE/FINALIZE
	// *************************************************************************************************

	/**
	 * Create a new weight-balanced linked tree.
	 */
	public WeightedLinkedTree() {
		super();
	}

	// *************************************************************************************************
	// INSTANCE METHODS - ACCESSORS
	// *************************************************************************************************

	/**
	 * Returns the current number of elements in the tree.
	 */
	public int size() {
		return (root == null ? 0 : root.balancer);
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
			put.balancer = 1;
			linkNode(nod, prv, nxt, put);
			return put;
		} else if ((cr = nod.compareKey(put.getKey())) < 0) {
			nod.right = insert(nod, nod.right, nod, nod.next, put, rpl);
			nod.balancer = nodeSize(nod);
			return rebalance(par, nod);
		} else if (cr > 0) {
			nod.left = insert(nod, nod.left, nod.prev, nod, put, rpl);
			nod.balancer = nodeSize(nod);
			return rebalance(par, nod);
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
			nod.balancer = nodeSize(nod);
			return rebalance(par, nod);
		} else if (cr > 0) {
			nod.left = remove(nod, nod.left, key, rqd);
			nod.balancer = nodeSize(nod);
			return rebalance(par, nod);
		} else if (nod.left != null || nod.right != null) { // cr==0
			if (nod.left == null) {
				nod = promoteRight(nod);
			} else if (nod.right == null) {
				nod = promoteLeft(nod);
			} else if (nod.left.balancer < nod.right.balancer) {
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
			return null;
		}
	}

	Node<K, V> promoteLeft(Node<K, V> nod) {
		Node<K, V> lft; // left node (nod=parent node)

		lft = super.promoteLeft(nod);
		nod.balancer = nodeSize(nod); // now inferior
		lft.balancer = nodeSize(lft); // now superior
		lft.right = rebalance(lft, lft.right); // rebalance recursively down
		return lft;
	}

	Node<K, V> promoteRight(Node<K, V> nod) {
		Node<K, V> rgt; // right node (nod=parent node)

		rgt = super.promoteRight(nod);
		nod.balancer = nodeSize(nod); // now inferior
		rgt.balancer = nodeSize(rgt); // now superior
		rgt.left = rebalance(rgt, rgt.left); // rebalance recursively down
		return rgt;
	}

	// *************************************************************************************************
	// INSTANCE METHODS - PRIVATE
	// *************************************************************************************************

	private Node<K, V> rebalance(Node<K, V> par, Node<K, V> nod) {
		if (par != null) {
			if (nod.left != null && nod.right == null && nod == par.right) {
				return promoteLeft(nod);
			}
			if (nod.right != null && nod.left == null && nod == par.left) {
				return promoteRight(nod);
			}
		}

		int ths = imbalance(nod);

		if (ths != 0) {
			int lft = imbalanceLeft(nod);
			int rgt = imbalanceRight(nod);

			if (ths > lft || ths > rgt) {
				if (lft > rgt) {
					return promoteRight(nod);
				} else {
					return promoteLeft(nod);
				}
			}
		}
		return nod;
	}

	private int nodeSize(Node<K, V> nod) {
		int siz = 1;

		if (nod.left != null) {
			siz += nod.left.balancer;
		}
		if (nod.right != null) {
			siz += nod.right.balancer;
		}
		return siz;
	}

	/** Balance delta for specified node. */
	private int imbalance(Node<K, V> nod) {
		int dlt = 0;

		if (nod.left != null) {
			dlt -= nod.left.balancer;
		}
		if (nod.right != null) {
			dlt += nod.right.balancer;
		}
		return Math.abs(dlt);
	}

	/** Balance delta if left node were promoted. */
	private int imbalanceLeft(Node<K, V> nod) {
		/*
		 * [4] [2] / \ / \ [2] [5] will become [1] [4] / \ / \ [1] [3] [3] [5]
		 */
		int dlt = +1; // [4]

		if (nod.right != null) {
			dlt += nod.right.balancer;
		} // [5]
		if (nod.left != null) {
			if (nod.left.right != null) {
				dlt += nod.left.right.balancer;
			} // [3]
			if (nod.left.left != null) {
				dlt -= nod.left.left.balancer;
			} // [1]
		}
		return Math.abs(dlt);
	}

	/** Balance delta if right node were promoted. */
	private int imbalanceRight(Node<K, V> nod) {
		/*
		 * [2] [4] / \ / \ [1] [4] will become [2] [5] / \ / \ [3] [5] [1] [3]
		 *
		 */
		int dlt = -1; // [2]

		if (nod.left != null) {
			dlt -= nod.left.balancer;
		} // [1]
		if (nod.right != null) {
			if (nod.right.left != null) {
				dlt -= nod.right.left.balancer;
			} // [3]
			if (nod.right.right != null) {
				dlt += nod.right.right.balancer;
			} // [5]
		}
		return Math.abs(dlt);
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
