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
			size = 1;
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
		if (x.item.key < y.item.key) {
			y.next = x.child;
			x.child = y;
			x.rank *= 2;
			return x;
		} else if (x.item.key > y.item.key) {
			y.next = x.next;
			if (x.next == x) {
				y.next = y;
			}
			x.next = y.child;
			y.child = x;
			y.rank *= 2;
			return y;
		}

		// Does not handle the case where they are equal.
		return x;
	}

	/**
	 * 
	 * Delete the minimal item
	 *
	 */
	public void deleteMin() {
		size -= 1;
		return; // should be replaced by student code

	}

	/**
	 * 
	 * Return the minimal HeapItem
	 *
	 */
	public HeapItem findMin() {
		return null; // should be replaced by student code
	}

	/**
	 * 
	 * pre: 0 < diff < item.key
	 * 
	 * Decrease the key of item by diff and fix the heap.
	 * 
	 */
	public void decreaseKey(HeapItem item, int diff) {
		return; // should be replaced by student code
	}

	/**
	 * 
	 * Delete the item from the heap.
	 *
	 */
	public void delete(HeapItem item) {
		size -= 1;
		return; // should be replaced by student code
	}

	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(BinomialHeap heap2) {
		System.out.println("In meld");
		int rank = this.last.rank;
		HeapNode heap1_prev_node = this.last;
		HeapNode heap1_node = this.last.next;
		HeapNode heap2_node = heap2.last.next;
		// heap2 is no linger cyclic, so we can check easily where it ends.
		heap2.last.next = null;
		while (heap2_node != null) {
			if (heap1_node.rank > heap2_node.rank) {
				HeapNode next2 = heap2_node.next;
				heap1_prev_node.next = heap2_node;
				heap2_node.next = heap1_node;
				heap1_prev_node = heap2_node;
				heap2_node = next2;
				num_trees++;

			} else if (heap1_node.rank < heap2_node.rank) {
				heap1_prev_node = heap1_node;
				heap1_node = heap1_node.next;
				num_trees++;
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

			}
			if (heap1_node.item.key < this.min.item.key) {
				this.min = heap1_node;
			}
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
		return false; // should be replaced by student code
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
