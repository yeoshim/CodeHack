import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class CodeHack {
	private static final int MAX_NODE_NUM = 30;
	//	4 DFS
	public static int[][] gmap = new int[MAX_NODE_NUM+1][MAX_NODE_NUM+1];
	private static int[] visit = new int[MAX_NODE_NUM+1];

	//	4 BFS
	public static int[][] gmap2 = new int[MAX_NODE_NUM+1][MAX_NODE_NUM+1];
	private static int[] visit2 = new int[MAX_NODE_NUM+1];

	
	public static HashMap<Integer, Student> buildHashTable(Student[] students) {
		HashMap<Integer, Student> map = new HashMap<Integer, Student>();
		for ( Student std :students )	{
			map.put(std.getId(), std);
		}
		return map;
	}

	public static ArrayList<String> merge(String[] words, String[] more) {
		ArrayList<String> sentence = new ArrayList<String>();
		for( String w : words )	sentence.add(w);
		for( String w : more )	sentence.add(w);
		
		return sentence;
	}

	public static String joinWords(String[] words) {
		StringBuffer sentence = new StringBuffer();
		for( String word : words )	sentence.append(word);
		
		return sentence.toString();
	}


	public static void dfs(int v) {
		visit[v] = 1;
		for (int i = 1; i < gmap.length; i++) {
			if( gmap[i][v] == 1 && visit[i] == 0 )	{
				System.out.println( "move from " + v + " to " + i );
				dfs(i);
			}
		}
	}
	
	public static String bfs(int v) {
		String result = "";
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(v);
		visit2[v] = 1;
		System.out.println( "Start from " + v );
		result += v;
		int prevNode = v;

		while( !queue.isEmpty() )	{
			int root = queue.poll();
			for (int i = 0; i < gmap2.length; i++) {
				if( gmap2[root][i] == 1 && visit2[i] == 0 )	{
					System.out.println( "move from " + prevNode + " to " + i );
					result += i;
					prevNode = i;
					visit2[i] = 1;
					queue.offer(i);
				}
			}
		}
		
		return result;
	}

	public static boolean isPrime(int n) {
		if( n < 2 )	return false;
		for (int i = 2; i < n-1; i++) {
			if( n%i == 0 )	return false;
		}
		
		return true;
	}

	public static boolean isLittleBetterPrime(int n) {
		if( n < 2 )	return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if( n%i == 0 )	return false;
		}
		
		return true;
	}

	private static int nextPrime(boolean[] flags, int prime) {
		int next = prime + 1;
		for (int i = next; i < flags.length; i++) {
			if( flags[next] )	return next;
		}
		return next;
	}

	private static void crossOff(boolean[] flags, int prime) {
		for (int i = prime*prime; i < flags.length; i += prime) {
			flags[i] = false;
		}
	}
	
	public static int SOE(int max) {
		boolean[] flags = new boolean[max+1];
		for (int i = 2; i < flags.length; i++) {
			flags[i] = true;	//	0,1 false / others true
		}
		
		int prime = 2;
		while( prime <= max )	{
			crossOff( flags, prime );
			prime = nextPrime( flags, prime );
			if( prime > flags.length )	break;
		}
		
		int pnumCnt = 0;
		for (int i = 0; i < flags.length; i++) {
			if( flags[i] )	{
				System.out.print(i + ",");
				pnumCnt++;
			}
		}
		System.out.println( " Prime Cnt: " + pnumCnt );
		
		return pnumCnt;
	}


	public static int SOE2(int n) {
		boolean[] flags = new boolean[n/2+2];
		for (int i = 2; i < flags.length; i++) {
			//	1 2 3 4 5 6
			//	1 3 5 7 9 11	i*2-1 
			flags[i] = true;
		}

		int primeCnt = 1;	//	assume 2
		
		int prime = 3;
		while( prime <= n )	{
			crossOff2( flags, prime );
			prime = nextPrime2( flags, prime );
			if( prime > flags.length )	break;
		}

		System.out.print("2,");
		for (int i = 2; i < flags.length; i++) {
			if( flags[i] && i*2-1 <= n )	{
//				System.out.print(i+",");
				System.out.print(i*2-1+",");
				primeCnt++;
			}
		}
		System.out.println( "Adv Prime Cnt: " + primeCnt );

//		for (int i = 1; i < flags.length; i++) {
//			System.out.println( i+": " + (i*2-1) );
//		}
		
		return primeCnt;
	}

	private static void crossOff2(boolean[] pNums, int prime) {
		for (int i = prime*prime; i < pNums.length*2; i+=prime) {
			if( i%2 != 0 && i/2+1 < pNums.length )	{	//	just odd number
				pNums[i/2+1] = false;
			}
		}		
	}
	
	private static int nextPrime2(boolean[] pNums, int prime) {
		int next = prime + 2;	//	3 5 7 9 11
		int idx = next/2+1;		//	2 3 4 5 6
		for (int i = idx; i < pNums.length; i++) {
			if( pNums[idx] )	return idx*2-1;
		}
		return next;
	}

	public static void mergeSort(int[] list, int low, int high) {
		if( low < high )	{
			int mid = (low + high) / 2;
			mergeSort( list, low, mid );
			mergeSort( list, mid+1, high );
			merge( list, low, mid, high );
		}
	}

	private static void merge(int[] list, int low, int mid, int high) {
		int[] helper = new int[high+1];
		for( int i = low; i <= high; i++ )	{
			helper[i] = list[i];
		}
		
		int helperLeft = low;
		int helperRight = mid+1;
		int current = low;
		
		while( helperLeft <= mid && helperRight <= high )	{
			if( helper[helperLeft] < helper[helperRight] )	{
				list[current] = helper[helperLeft];
				helperLeft++;
			}
			else	{
				list[current] = helper[helperRight];
				helperRight++;
			}
			current++;
		}
		
		int remaining = mid - helperLeft;
		for( int i = 0; i < remaining; i++ )	{
			list[current+i] = helper[helperLeft+1];
		}
		//	skip right because they are already sorted(in left sorting process)
	}

	public static void quickSort(int[] list, int left, int right) {
		int idx = partition( list, left, right );	//	select pivot & swap
		if( left < idx-1 )	quickSort( list, left, idx-1 );
		if( right > idx )	quickSort( list, idx, right );
	}

	private static int partition(int[] list, int left, int right) {
		int pivot = list[(left+right)/2];
		while( left <= right )	{
			while( list[left] < pivot )	left++;
			while( list[right] > pivot )	right--;
			
			if( left <= right )	{
				swap( list, left, right );
				left++;
				right--;
			}
		}
		
		return left;
	}

	private static void swap(int[] list, int left, int right) {
		int tmp = list[left];
		list[left] = list[right];
		list[right] = tmp;
	}

	public static int binarySearch(int[] list, int x) {
		int low = 0;
		int high = list.length - 1;
		
		while( low <= high )	{
			int mid = (low + high) / 2;
			if( list[mid] < x )	low = mid + 1;
			else if( list[mid] > x )	high = mid - 1;
			else	return mid;
		}
		
		return -1;
	}

	public static int binarySearchRec(int[] list, int x, int low, int high) {
		if( low > high )	return -1;

		int mid = (low + high) / 2;
		if( list[mid] < x )	return binarySearchRec(list, x, mid+1, high);
		else if( list[mid] > x )	return binarySearchRec(list, x, low, mid-1);
		else	return mid;
	}
	
	
}
