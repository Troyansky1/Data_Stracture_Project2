
/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers.
 * Based on exercise from previous semester.
 */
public class BinomialHeap {

	public BinomialHeap(int size, int num_trees, BinomialHeap.HeapNode last, BinomialHeap.HeapNode min) {
		super();
		this.size = size;
		this.num_trees = num_trees;
		this.last = last;
		this.min = min;
		this.num_of_links = 0;

	}

	public BinomialHeap() {
		super();
		this.size = 0;
		this.num_trees = 0;
		this.min = null;
		this.last = null;
		this.num_of_links = 0;
	}

	public int size;
	public int num_trees;
	public HeapNode last;
	public HeapNode min;
	public int num_of_links;

	/**
	 * 
	 * pre: key > 0
	 *
	 * Insert (key,info) into the heap and return the newly generated HeapItem.
	 *
	 */
	public HeapItem insert(int key, String info) {
		HeapItem heap_item = new HeapItem(key, info);
		if (size == 0) {
			num_trees = 1;
			last = heap_item.node;
			min = heap_item.node;
			size = 0;
		} else {
			BinomialHeap heap = new BinomialHeap(1, 1, heap_item.node, heap_item.node);

			meld(heap);
		}
		size++;

		return heap_item;

	}

	/**
	 * 
	 * pre: key x != key y
	 * *
	 * Link (x,y)- link the trees and return the root.
	 * 
	 * @return
	 *
	 */
	public BinomialHeap.HeapNode link(HeapNode x, HeapNode y) {
		this.num_of_links++;
		if (x.item == null) {
			System.out.println("null x" + x.rank + y.rank);
		}
		if (y.item == null) {
			System.out.println("null y");
		}
		if (x.item.key <= y.item.key) {
			HeapNode tmp = x;
			x = y;
			y = tmp;
		}
		if (y.child == null) {
			x.next = x;
		} else {
			x.next = y.child.next;
			y.child.next = x;
		}
		y.child = x;
		x.parent = y;
		y.rank++;
		return y;

	}

	/**
	 * 
	 * Return the previous HeapNode to node2.
	 *
	 */
	public HeapNode findPrev(HeapNode node2) {
		HeapNode prev = node2;
		while (prev.next.item.key != node2.item.key) {
			prev = prev.next;
		}
		return prev;
	}

	/**
	 * 
	 * updates the min Node and last node to be the one with the highest rank.
	 *
	 */
	public void updateMin_last() {
		HeapNode newmin = last;
		HeapNode newlast = last;
		HeapNode prev = last.next;
		while (prev.item.key != last.item.key) {
			if (prev.rank > newlast.rank)
				newlast = prev;
			if (prev.item.key < newmin.item.key)
				newmin = prev;
			prev = prev.next;
		}
		this.min = newmin;
		this.last = newlast;
	}

	/**
	 * 
	 * Delete the minimal item
	 *
	 */
	public void deleteMin() {
		// delete it then meld
		HeapNode minChild = this.min.child;
		HeapNode minNext = this.min.next;
		HeapNode minprev = findPrev(min);
		if (minChild == null) {
			if (minNext == min) {
				// min has No chidren,No Next --it was just the min
				this.size = 0;
				this.num_trees = 0;
				this.last = null;
				this.min = null;
				return;
			}
			// min has No chidren,has Next
			minprev.next = minNext;
			this.num_trees -= 1;
			size -= 1;
			updateMin_last();
			return;
		}

		minprev.next = minNext;
		minChild.parent = null;

		int treenum = min.rank;
		int childrensize = (int) Math.pow(2, min.rank) - 1;

		BinomialHeap heap2 = new BinomialHeap(childrensize, treenum, minChild, minChild);
		if (min.item.key == last.item.key) {
			last = last.next;
		}

		heap2.updateMin_last();

		if (min.next == min) {
			// means that the min doesnt have next. so its just the children
			this.size = heap2.size;
			this.num_trees = heap2.num_trees;
			this.last = heap2.last;
			this.min = heap2.min;

		} else {
			// min has chidren and Next
			updateMin_last();
			this.last.parent = null;
			heap2.last.parent = null;
			size -= 1;
			meld(heap2);
		}

	}

	public void printHeap() {
		if (empty()) {
			System.out.println("Heap is empty");
			return;
		}
		System.out.println("Binomial Heap:");
		HeapNode currentRoot = last;
		HeapNode stopNode = last.next; // Stop condition for circular list of roots
		boolean stop = false;

		do {
			System.out.println("Root: " + currentRoot.item.key);
			printTree(currentRoot, 0, currentRoot); // Print the tree rooted at current root
			currentRoot = currentRoot.next;
			if (currentRoot == stopNode) {
				stop = true; // We've visited all roots
			}
		} while (!stop);
	}

	private void printTree(HeapNode node, int depth, HeapNode initialRoot) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append("  "); // Adjust spacing for depth
		}
		sb.append(node.item.key).append(" [").append(node.rank).append("]");

		System.out.println(sb.toString());

		if (node.child != null) {
			printTree(node.child, depth + 1, node.child); // Print child recursively
		}

		if (node.next != node.parent && node.next != null && node.next != initialRoot) {
			printTree(node.next, depth, initialRoot); // Print sibling recursively until we reach the initial root
		}
	}

	/**
	 * 
	 * Return the minimal HeapItem
	 *
	 */
	public HeapItem findMin() {
		if (min == null)
			return null;
		return min.item;
	}

	/**
	 * 
	 * pre: 0 < diff < item.key
	 * 
	 * Decrease the key of item by diff and fix the heap.
	 * 
	 */
	public void decreaseKey(HeapItem item2, int diff) {
		item2.key = item2.key - diff;
		if (item2.key == this.min.item.key) {
			return;
		}
		// its not the root, so we check to heapify-up
		HeapNode node = item2.node;
		HeapNode nodeparent = item2.node.parent;

		int tmpkey;
		String tmpInfo;

		while (nodeparent != null && nodeparent.item.key >= node.item.key) {
			// System.out.println("In while :))))))))))))");
			tmpkey = node.item.key;
			tmpInfo = node.item.info;
			node.item.key = nodeparent.item.key;
			node.item.info = nodeparent.item.info;
			nodeparent.item.key = tmpkey;
			nodeparent.item.info = tmpInfo;
			node = nodeparent;
			nodeparent = nodeparent.parent;

		}
		if (min.item.key >= node.item.key)
			min = node;

	}

	/**
	 * 
	 * Delete the item from the heap.
	 *
	 */
	public void delete(HeapItem item) {
		int diff = (int) Double.POSITIVE_INFINITY;
		decreaseKey(item, diff); // making item the min node
		deleteMin(); // delete the min- which is item.
	}

	private HeapNode getNext(HeapNode node) {
		HeapNode nextNode = new HeapNode(null, null, null, null, -1);
		int this_rank = node.rank;
		// System.out.println("in GetNext ");
		// if(node.next==null)
		// System.out.println("error here :node.next==null");
		// int next_rank = node.next.rank;
		// if (node.next != null && next_rank > this_rank) {
		// nextNode = node.next;
		// }
		if (node.next != null) {
			int next_rank = node.next.rank;
			if (next_rank > this_rank) {
				nextNode = node.next;
			}
		}

		return nextNode;
	}

	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(BinomialHeap heap2) {
		if (this.last == null) {
			// added this for the test
			this.size = heap2.size;
			this.num_trees = heap2.num_trees;
			this.last = heap2.last;
			this.min = heap2.min;
			return;
		}
		if (heap2.last == null)
			return;

		num_trees = 0;
		// System.out.println("In meld");
		HeapNode prev_node = new HeapNode(null, null, null, null, -1);
		HeapNode nextNode = this.last.next;
		HeapNode heap1_node = this.last.next;
		HeapNode heap2_node = heap2.last.next;
		HeapNode res = new HeapNode(null, null, null, null, -1);
		HeapNode first = new HeapNode(null, null, null, null, -1);
		HeapNode thisNode = new HeapNode(null, null, null, null, -1);
		// System.out.println("heap1_node= " + heap1_node.item.key + " rank is= " +
		// heap1_node.rank);
		// System.out.println("heap2_node= " + heap2_node.item.key + " rank is= " +
		// heap2_node.rank);

		// System.out.println("**");

		int max_rank = Math.max(this.last.rank, heap2.last.rank);
		int meld_rank = 0;
		// Iterate over the heaps to meld as long as it's not the maximum rank or when
		// it is and there is a residue to meld.
		while (max_rank >= meld_rank || res.rank == meld_rank) {
			// If there are no items in this rank in both heaps.
			if (heap1_node.rank != meld_rank && heap2_node.rank != meld_rank) {
				// Check if there is a residue and connect it as needed.
				if (res.rank == meld_rank) {
					// Can't be first node in this condition.
					thisNode = res;
					nextNode = heap1_node;
				}
			}

			else {
				// if there is an item in heap1 but no item is Heap2
				if (heap1_node.rank == meld_rank && heap2_node.rank != meld_rank) {

					/////// System.out.println("in here 1 ");
					nextNode = getNext(heap1_node);
					// Check if there is a residue and link with heap 1 node
					if (res.rank == meld_rank) {
						res = link(heap1_node, res);
						heap1_node = nextNode;
					}
					// Advance the pointer in heap1
					else {
						thisNode = heap1_node;
					}
				}

				// else if there is an item in heap 2 but there is no item in heap 1
				else if (heap1_node.rank != meld_rank && heap2_node.rank == meld_rank) {
					// Check if there is a residue and link with heap2 node
					if (res.rank == meld_rank) {
						HeapNode next2 = getNext(heap2_node);
						res = link(res, heap2_node);
						heap2_node = next2;
					}
					// Advance the pointer in heap2
					else {
						thisNode = heap2_node;

						/////// System.out.println("in here 2");
						heap2_node = getNext(heap2_node);
					}
				}

				// else if there are items in both heaps
				else if (heap1_node.rank == meld_rank && heap2_node.rank == meld_rank) {
					/////// System.out.println("in here 3");
					nextNode = getNext(heap1_node);

					/////// System.out.println("in here 4 ");
					HeapNode next2 = getNext(heap2_node);
					// Check if there is a residue and make it the current node.
					if (res.rank == meld_rank) {
						thisNode = res;
						res = link(heap1_node, heap2_node);
						heap2_node = next2;
					} else {
						res = link(heap1_node, heap2_node);
						heap2_node = next2;
						heap1_node = nextNode;
					}

				}

			}
			// If there is a node in this rank of the tree, connect it to the previos node
			// and the next node.
			if (thisNode.rank == meld_rank) {
				num_trees++;
				// If this is the first, save the pointer. If not, make this the next of the
				// previous.
				if (first.rank == -1) {
					first = thisNode;
				} else {
					prev_node.next = thisNode;
				}

				// Advance the prev and next nodes in the current heap.
				prev_node = thisNode;

				// Check if minimal node, update if it is.
				if (min.item.key > thisNode.item.key) {
					min = thisNode;
				}
				// Update last
				last = thisNode;
				// Advance the pointer in heap1.
				heap1_node = nextNode;

			}
			meld_rank++;
		}
		last.next = first;
		return;
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 * 
	 */
	public int size() {
		return size;
	}

	/**
	 * 
	 * The method returns true if and only if the heap
	 * is empty.
	 * 
	 */
	public boolean empty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees() {
		return num_trees;
	}

	/**
	 * Class implementing a node in a Binomial Heap.
	 * 
	 */
	public class HeapNode {

		public HeapNode(BinomialHeap.HeapItem item, BinomialHeap.HeapNode child, BinomialHeap.HeapNode next,
				BinomialHeap.HeapNode parent, int rank) {
			super();
			this.item = item;
			this.child = child;
			this.next = next;
			this.parent = parent;
			this.rank = rank;

		}

		public HeapItem item;
		public HeapNode child;
		public HeapNode next;
		public HeapNode parent;
		public int rank;

	}

	/**
	 * Class implementing an item in a Binomial Heap.
	 * 
	 */
	public class HeapItem {
		public HeapItem(int key, String info) {
			this.info = info;
			this.key = key;
			node = new HeapNode(this, null, null, null, 0);
			node.next = node;
		}

		public HeapNode node;
		public int key;
		public String info;
	}

}
