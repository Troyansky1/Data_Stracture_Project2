import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Part2Test {
	 public static void main(String[] args) {
		 int runs=10;
		 for(int i=1;i<=6;i++)
		 {
			// int n=Get_n(i);
			 int n=20;
			 System.out.println("************");
			 System.out.println("now running for i= "+i+" n is= "+n );
			 double [] rank_diff_links_trees1=new double [4];
			 double [] rank_diff_links_trees2=new double [4];
			 double [] rank_diff_links_trees3=new double [4];
			 double [] tmp1=new double [4];
			 double [] tmp2=new double [4];
			 double [] tmp3=new double [4];
			 for(int k=0; k<runs;k++)
			 {
				 BinomialHeap heap1 = new BinomialHeap();
				 BinomialHeap heap2 = new BinomialHeap();
				 BinomialHeap heap3 = new BinomialHeap();
				 tmp1=test_for_heap1(heap1,n);
				 tmp2=test_for_heap2(heap2,n);
				 tmp3=test_for_heap3(heap3,n);
				 for(int index=0;index<4;index++)
				 {
					 rank_diff_links_trees1[index]+=tmp1[index];
					 rank_diff_links_trees2[index]+=tmp2[index];
					 rank_diff_links_trees3[index]+=tmp3[index];
				 
				 }
			 }
			 
			 for(int index=0;index<4;index++)
			 {
				 //getting the avg
				 rank_diff_links_trees1[index]=rank_diff_links_trees1[index]/runs;
				 rank_diff_links_trees2[index]=rank_diff_links_trees2[index]/runs;
				 rank_diff_links_trees3[index]=rank_diff_links_trees3[index]/runs;
			 
			 }	 
		/*	 System.out.println("for heap 1: ");
			 System.out.println("time is "+rank_diff_links_trees1[1]+"  num of links= "+rank_diff_links_trees1[2]+"  num of trees= "+rank_diff_links_trees1[3]+" rank ="+rank_diff_links_trees1[0]);
			 System.out.println("");
			*/ 
			 System.out.println("for heap 2: ");
			 System.out.println("time is "+rank_diff_links_trees2[1]+"  num of links= "+rank_diff_links_trees2[2]+"  num of trees= "+rank_diff_links_trees2[3]+" rank ="+rank_diff_links_trees2[0]);
			 System.out.println("");
			/* System.out.println("for heap 3: ");
			 System.out.println("time is "+rank_diff_links_trees3[1]+"  num of links= "+rank_diff_links_trees3[2]+"  num of trees= "+rank_diff_links_trees3[3]+" rank ="+rank_diff_links_trees3[0]);
			 System.out.println("");
			 */
		 } 
		  
	 }
	 public static double [] test_for_heap1(BinomialHeap heap1,int n) {
		 double start_time = System.currentTimeMillis();
		 Order_Insert(heap1,n);
		 double end_time = System.currentTimeMillis();
		 double difference = end_time-start_time;
		 double [] rank_diff_links_trees= {0 , difference, heap1.num_of_links, heap1.num_trees};
		 
		 return rank_diff_links_trees;
	 }
	 public static double [] test_for_heap2(BinomialHeap heap2,int n) {
		 double start_time = System.currentTimeMillis();
		 Random_Insert(heap2,n);
		 int rank=0;
		 for(int k=0;k<n/2;k++)
		 {
			 rank+=heap2.min.rank;
			 heap2.deleteMin();
		 }  
		 double end_time = System.currentTimeMillis();
		 double difference = end_time-start_time;
		 double [] rank_diff_links_trees= {rank , difference, heap2.num_of_links, heap2.num_trees};
		 
		 return rank_diff_links_trees;
	 }
	 
	 public static double [] test_for_heap3(BinomialHeap heap3,int n) {
		 double start_time = System.currentTimeMillis();
		 Reverse_Order_Insert(heap3,n);
		 int stop=n-(int)Math.pow(2, 5)+1; 
		 int rank=0;
		 for(int k=0;k<stop;k++)
		 {
			 rank+=heap3.min.rank;
			 heap3.deleteMin();
		 }
		 double end_time = System.currentTimeMillis();
		 double difference = end_time-start_time;
		 double [] rank_diff_links_trees= {rank , difference, heap3.num_of_links, heap3.num_trees};
		 
		 return rank_diff_links_trees;
		 
	 }
	 public static int Get_n(int i) {
		 int n=(int)(Math.pow(3, i+5)-1);
	        return n;
	    }
	 
	 public static void Order_Insert(BinomialHeap heap1,int n) {
		 //inserts in order 1...n
	     for(int k=1;k<=n;k++)
	     {
	    	 heap1.insert(k, String.valueOf(k));
	     }
	 }
	 public static void Reverse_Order_Insert(BinomialHeap heap3,int n) {
		 //inserts in order  n...1
	     for(int k=n;k>=1;k--)
	     {
	    	 heap3.insert(k, String.valueOf(k));
	     }
	 }
		
	 public static void Random_Insert(BinomialHeap heap2,int n) {
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
			 heap2.insert(arr[k],String.valueOf(arr[k]));
	     }	 
		 
	 }
	 
	 
	 
		 
		 
}
	 
	 
			 
	

