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
	}

	public BinomialHeap() {
		super();
		this.size = 0;
		this.num_trees = 0;
		this.min = null;
		this.last = null;
	}

	public int size;
	public int num_trees;
	public HeapNode last;
	public HeapNode min;

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
		if (x.item.key <= y.item.key) {
			y.next = x.next;
			if (x.child == null) {
				y.next = y;
			} else {
				y.next = x.child.next;
				x.child.next = y;
			}
			x.child = y;
			x.rank *= 2;
			return x;
		} else {
			if (x.next == x) {
				y.next = y;
			}
			if (y.child == null) {
				x.next = x;
			} else {
				x.next = y.child.next;
				y.child.next = x;
			}
			y.child = x;
			y.rank *= 2;
			return y;
		}

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
		;
		return prev;
	}

	/**
	 * 
	 * Delete the minimal item
	 *
	 */
	public void deleteMin() {
		// delete it then meld
		size -= 1;
		HeapNode minNext = this.min.next;
		HeapNode minprev = findPrev(min);
		HeapNode minChild = this.min.child;

		minprev.next = minNext;
		minChild.parent = null;

		int treenum = (int) (Math.log(minChild.rank) / Math.log(2)); // tree num is log2(rank)
		int childrensize = (int) Math.pow(2, minChild.rank);
		BinomialHeap heap2 = new BinomialHeap(childrensize, treenum, minChild, minChild);
		if (min.item.key == last.item.key) {
			last = last.next;
		}
		this.min = minNext;

		System.out.println("*******HEAP2******");
		heap2.printHeap();
		System.out.println("*******THIS******");
		this.printHeap();
		meld(heap2);

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
		return min.item;
	}

	/**
	 * 
	 * pre: 0 < diff < item.key
	 * 
	 * Decrease the key of item by diff and fix the heap.
	 * 
	 */
	public void decreaseKey(HeapItem item, int diff) {
		if (item.key == min.item.key) {
			item.key = item.key - diff;
			return;
		}
		item.key = item.key - diff;
		// its not the root, so we check to heapify-up
		HeapNode node = item.node;
		HeapNode nodeparent = item.node.parent;
		int tmpkey;
		String tmpInfo;

		while (nodeparent != null && nodeparent.item.key >= node.item.key) {
			tmpkey = node.item.key;
			tmpInfo = node.item.info;
			node.item.key = nodeparent.item.key;
			node.item.info = nodeparent.item.info;
			nodeparent.item.key = tmpkey;
			nodeparent.item.info = tmpInfo;
			node = nodeparent;
			nodeparent = nodeparent.parent;

		}
		// no need to check the min, if item becomes the root min will still point at
		// it.
		// ************** I need to check it .
	}

	/**
	 * 
	 * Delete the item from the heap.
	 *
	 */
	public void delete(HeapItem item) {
		int diff = item.key - min.item.key + 1;
		decreaseKey(item, diff); // making item the min node
		deleteMin(); // delete the min- which is item.

	}

	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(BinomialHeap heap2) {
		num_trees = this.num_trees + heap2.num_trees;
		// System.out.println("In meld");
		int rank = this.last.rank;
		HeapNode heap1_prev_node = this.last;
		HeapNode heap1_node = this.last.next;
		HeapNode heap2_node = heap2.last.next;
		// heap2 is no linger cyclic, so we can check easily where it ends.
		heap2.last.next = null;
		while (heap2_node != null) {
			if (heap2_node.item.key < this.min.item.key) {
				this.min = heap2_node;
			}

			if (heap1_node.rank > heap2_node.rank) {
				HeapNode next2 = heap2_node.next;
				heap1_prev_node.next = heap2_node;
				heap2_node.next = heap1_node;
				heap1_prev_node = heap2_node;
				heap2_node = next2;

			} else if (heap1_node.rank < heap2_node.rank) {
				heap1_prev_node = heap1_node;
				heap1_node = heap1_node.next;
			}

			else {
				HeapNode prev1 = heap1_prev_node;
				HeapNode next1 = heap1_node.next;
				HeapNode next2 = heap2_node.next;
				heap2_node = link(heap1_node, heap2_node);
				prev1.next = next1;
				heap1_node = next1;
				if (heap2_node.rank > rank) {
					heap1_node = heap2_node;
					this.last = heap1_node;
					heap2_node = null;
				} else {
					heap2_node.next = next2;
				}
				num_trees--;

			}

			/*
			 * System.out.println(" before if ////// min is= " + min.item.key +
			 * "  heanode= "+heap2_node.item.key);
			 * if (heap2_node.item.key < this.min.item.key) {
			 * System.out.println(" in the if ,,, min is= " + min.item.key);
			 * this.min = heap1_node;
			 * System.out.println(" still in the if ,,, new min is= " + min.item.key);
			 * }
			 */
		}

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
			node = new HeapNode(this, null, null, null, 1);
			node.next = node;
		}

		public HeapNode node;
		public int key;
		public String info;
	}

}
