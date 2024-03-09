public class Tests extends TestHelper {
    public static void main(String[] args) {
        // Test0();
        Test1();
        Test2();
        // Test3();
        // Test4();

    }

    public static void Test0() {
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[] { 1, 2, 3, 4, 5, 6 }, false);
        insertKeyArray(heap, new int[] { 7, 8, 9, 10 }, false);
    }

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

    public static void Test2() {
        // Should be 1-> 2
        BinomialHeap heap3 = new BinomialHeap();
        insertKeyArray(heap3, new int[] { 7, 8, 4, 5, 6, 3, 2, 9, 1, 10 }, false);
    }

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

    public static void Test4() {
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[] { 13, 43, 23, 33, 63, 53, 73 }, false);
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        System.out.println("min: " + heap.findMin().key + " Rank " + heap.findMin().node.rank);
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