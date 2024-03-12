public class Tests extends TestHelper {
    public static void main(String[] args) {
        // Test0();
        // Test1();
        // Test2();
        // Test3();
        // Test3_1();
        Test4();
        // Test6();

    }

    // Testing very baic insertion. Sanity check.
    public static void Test0() {
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[] { 1, 2, 3, 4, 5, 6 }, false);
        insertKeyArray(heap, new int[] { 7, 8, 9, 10 }, false);
    }

    // Checking more basic insertions.
    public static void Test1() {
        // shold be 9-> 1
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, false);
        heap.printHeap();
        // Should be 1-> 3
        BinomialHeap heap2 = new BinomialHeap();
        insertKeyArray(heap2, new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 }, false);
        // heap2.printHeap();

    }

    // Checking "random" order insertions to find edge cases
    public static void Test2() {
        // Should be 1-> 2
        BinomialHeap heap3 = new BinomialHeap();
        insertKeyArray(heap3, new int[] { 7, 8, 4, 5, 6, 3, 2, 9, 1, 10 }, false);
    }

    // Checking basic meld
    public static void Test3() {
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, false);
        BinomialHeap heap2 = new BinomialHeap();
        heap.printHeap();
        insertKeyArray(heap2, new int[] { 30, 29, 28, 27, 26, 25, 24, 23, 22, 21 }, true);
        heap2.printHeap();
        heap.meld(heap2);
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);

    }

    // Checking basic meld
    public static void Test3_1() {
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[] { 53, 63, 73 }, false);
        BinomialHeap heap2 = new BinomialHeap();
        insertKeyArray(heap2, new int[] { 23, 33, 43 }, false);
        heap.meld(heap2);
        heap.printHeap();
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);

    }
    
    // checking delete min
    public static void Test4() {
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[] { 13, 43, 23, 33, 63, 53, 73 }, false);
        Test4helper(heap);

    }

    public static void Test4helper(BinomialHeap heap) {
        System.out.println(" <<<<<<<<<<<<now in test helper>>>>>>>>>>>>>>>>>>");
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        System.out.println("min: " + heap.findMin().key + " Rank " + heap.findMin().node.rank);
        System.out.println(" heap before delete*****************");
        heap.printHeap();
        heap.deleteMin();
        System.out.println("min: " + heap.findMin().key + " Rank " + heap.findMin().node.rank);
        heap.printHeap();
    }

    public static void Test5() {
        BinomialHeap heap = new BinomialHeap();
        BinomialHeap heap2 = new BinomialHeap();
        // insertKeyArray(heap, new int[] {13, 43, 23, 33, 63, 53, 73});
        insertKeyArray(heap, new int[] { 23, 33, 43 }, false);
        insertKeyArray(heap2, new int[] { 63, 53, 73 }, false);
        heap.meld(heap2);
        heap.printHeap();

        System.out.println("*************");
        // heap.deleteMin();
        // heap.printHeap();

    }

    // testing decreaseKey
    public static void Test6() {
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[] { 13, 43, 23, 33, 63, 53, 73 }, false);
        heap.printHeap();
        int key = 43;
        // BinomialHeap.HeapItem item2 =heap.findMin();

        // BinomialHeap.HeapItem item3 =heap.findMin();

        // System.out.println("min is="+item3.key);

        System.out.println("child.child is " + heap.min.child.child.item.key);
        System.out.println("child.child.parent is " + heap.min.child.parent.item.key);

        heap.decreaseKey(heap.min.child.child.item, 23);
        System.out.println("new min is=" + heap.findMin().key);
        System.out.println("*****");
        heap.printHeap();

    }

}

abstract class TestHelper {
    static void insertKeyArray(BinomialHeap heap, int[] keys, boolean prints) {
        for (int key : keys) {
            heap.insert(key, String.valueOf(key));
            if (prints == true) {
                System.out.println("Inserted: " + String.valueOf(key));
                System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
                System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
            }

        }
        System.out.println("Initialized heap");
        if (prints == false) {
            System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
            System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        }
    }
}