public class Tests extends TestHelper {
    public static void main(String[] args) {
        TestInsert();
    }

    /**
     * @pre existence of an empty constructor in BinomialHeap
     */
    public static void TestInsert() {
        BinomialHeap heap = new BinomialHeap();
        insertKeyArray(heap, new int[]{15, 35, 20, 31, 40, 43, 45});
        PrintHeap.printHeap(heap, true);
        // test for
        // - size
        // - rank
    }
}

abstract class TestHelper {
    static void insertKeyArray(BinomialHeap heap, int[] keys) {
        for (int key : keys) {
            heap.insert(key, String.valueOf(key));
        }
    }
}