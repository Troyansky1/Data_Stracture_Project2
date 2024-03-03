public class Tests extends TestHelper {
    public static void main(String[] args) {
        BinomialHeap heap = new BinomialHeap();
        BinomialHeap heap2 = new BinomialHeap();
        
        // Test1(heap);
        // Test2(heap);
        //Test3(heap);
        Test5(heap,heap2);

    }

    public static void Test1(BinomialHeap heap) {
        heap.insert(11, String.valueOf(1));
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        heap.insert(41, String.valueOf(2));
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        heap.insert(21, String.valueOf(3));
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        heap.insert(31, String.valueOf(3));
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        // PrintHeap.printHeap(heap, true);
    }

    public static void Test2(BinomialHeap heap) {
        heap.insert(22, String.valueOf(1));
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        heap.insert(32, String.valueOf(2));
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        heap.insert(12, String.valueOf(3));
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        heap.insert(42, String.valueOf(3));
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        // PrintHeap.printHeap(heap, true);
    }

    public static void Test3(BinomialHeap heap) {
        insertKeyArray(heap, new int[] { 13, 43, 23, 33, 63, 53, 73 });
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
       // PrintHeap.printHeap(heap, true);
    }
    public static void Test4(BinomialHeap heap) {
        insertKeyArray(heap, new int[] { 13, 43, 23, 33, 63, 53, 73});
        System.out.println("First: " + heap.last.next.item.key + " Rank " + heap.last.next.rank);
        System.out.println("Last: " + heap.last.item.key + " Rank " + heap.last.rank);
        System.out.println("min: " + heap.findMin().key + " Rank " + heap.findMin().node.rank);
        heap.deleteMin();
        System.out.println("min: " + heap.findMin().key + " Rank " + heap.findMin().node.rank);
       // PrintHeap.printHeap(heap, true);
    }
    public static void Test5(BinomialHeap heap,BinomialHeap heap2) {
        //insertKeyArray(heap, new int[] {13, 43, 23, 33, 63, 53, 73});
        insertKeyArray(heap, new int[] {23, 33, 43});
    	insertKeyArray(heap2, new int[] {63,53,73});
    	heap2.meld(heap);
        heap.printHeap();
       
        System.out.println("*************");
        //heap.deleteMin();
        //heap.printHeap();
        
    }
    
    
    
    
    
    
}

abstract class TestHelper {
    static void insertKeyArray(BinomialHeap heap, int[] keys) {
        for (int key : keys) {
            heap.insert(key, String.valueOf(key));
        }
    }
}