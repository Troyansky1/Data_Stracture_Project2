import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Part2Test {
	 public static void main(String[] args) {
		 for(int i=1;i<=6;i++)
		 {
			 int n=Get_n(i);
			 System.out.println("************");
			 System.out.println("now running for i= "+i+"n is= "+n );
			 BinomialHeap heap1 = new BinomialHeap();
			 BinomialHeap heap2 = new BinomialHeap();
			 BinomialHeap heap3 = new BinomialHeap();
			 BinomialHeap heap2_2 = new BinomialHeap();
			 long start_time1 = System.currentTimeMillis();
			 Order_Insert(heap1,n);
			 long end_time1 = System.currentTimeMillis();
			 long difference1 = end_time1-start_time1;
			 System.out.println("for heap 1: ");
			 System.out.println("time is "+difference1+"  num of links= "+heap1.num_of_links+"  num of trees= "+heap1.num_trees);
			 System.out.println("");
			 
			 long start_time2 = System.currentTimeMillis();
			 int rank2=test_for_heap2(heap2,n);
			 long end_time2 = System.currentTimeMillis();
			 long difference2 = end_time2-start_time2;
			 System.out.println("for heap 2: ");
			 System.out.println("time is "+difference2+"  num of links= "+heap2.num_of_links+"  num of trees= "+heap2.num_trees+" rank is "+rank2);
			 System.out.println("");
			 
			 long start_time2_2 = System.currentTimeMillis();
			 int rank2_2=test_for_heap2(heap2_2,n);
			 long end_time2_2 = System.currentTimeMillis();
			 long difference2_2 = end_time2_2-start_time2_2;
			 System.out.println("for heap 2_2, option 2: ");
			 System.out.println("time is "+difference2_2+"  num of links= "+heap2_2.num_of_links+"  num of trees= "+heap2_2.num_trees+" rank is "+rank2_2);
			 System.out.println("");
			 
			 long start_time3 = System.currentTimeMillis();
			 int rank3=test_for_heap3(heap3,n);
			 long end_time3 = System.currentTimeMillis();
			 long difference3 = end_time3-start_time3;
			 System.out.println("for heap 3: ");
			 System.out.println("time is "+difference3+"  num of links= "+heap3.num_of_links+"  num of trees= "+heap3.num_trees+" rank is "+rank3);
			 System.out.println("************");
			 
		 }
		  
	 }
	 
	 public static int Get_n(int i) {
		 int n=(int)(Math.pow(3, i+5)-1);
	        return n;
	    }
	 
	 public static void Order_Insert(BinomialHeap heap,int n) {
		 //inserts in order 1...n
	     for(int k=1;k<=n;k++)
	     {
	    	 heap.insert(k, String.valueOf(k));
	     }
	 }
	 public static void Reverse_Order_Insert(BinomialHeap heap,int n) {
		 //inserts in order  n...1
	     for(int k=n;k<=1;k--)
	     {
	    	 heap.insert(k, String.valueOf(k));
	     }
	 }
		
	 
	 public static void Random_Insert(BinomialHeap heap,int n) {
		 //inserting  1...n in random order using random generator 
		 
		 int [] arr=new int [n];
		 Random rand = new Random();
		 int rand_num;
		 for(int k=1;k<=n;k++)
		 {
			 
			 rand_num = rand.nextInt(n);// Generate random integers in range 0 to n-1
			 while(arr[rand_num]!=0) // already in heap
			 {
				 rand_num = rand.nextInt(n);
				 
			 }
			 arr[rand_num]=1;
			 heap.insert(rand_num+1, String.valueOf(rand_num+1));
			
		 }
	 }
	 public static void Random_Insert_option2(BinomialHeap heap,int n) {
		 //inserting  1...n in random order using array shuffle
		 Integer[] arr=new Integer [n];
		 for(int k=0;k<n;k++)
	     {
	    	 arr[k]=k+1;
	     }
		 List<Integer> intList = Arrays.asList(arr);
		 Collections.shuffle(intList);
		 intList.toArray(arr);
		 for(int k=0;k<n;k++)
	     {
			 heap.insert(arr[k],String.valueOf(arr[k]));
	     }	 
		 
	 }
	 
	 public static int test_for_heap2(BinomialHeap heap2,int n) {
		 Random_Insert(heap2,n);
		 int rank=0;
		 for(int k=0;k<n/2;k++)
		 {
			 rank+=heap2.min.rank;
			 heap2.deleteMin();
		 }  
		 return rank;
	 }
	 
	 public static int test_for_heap3(BinomialHeap heap3,int n) {
		 Reverse_Order_Insert(heap3,n);
		 int stop=n-(int)Math.pow(2, 5)+1; 
		 int rank=0;
		 for(int k=0;k<stop;k++)
		 {
			 rank+=heap3.min.rank;
			 heap3.deleteMin();
		 }  
		 return rank;
	 }
	 
		 
		 
}
	 
	 
			 
	

