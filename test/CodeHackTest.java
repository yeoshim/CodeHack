import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class CodeHackTest {

	@Test
	public void testBinarySearchRec() {
		int[] list = { 1, 4, 5, 2, 8, 9 };
		CodeHack.quickSort(list, 0, list.length-1);
		
		assertEquals( 4, CodeHack.binarySearchRec(list, 8, 0, list.length-1) );
		assertEquals( 2, CodeHack.binarySearchRec(list, 4, 0, list.length-1) );
	}
	
	@Test
	public void testBinarySearch() {
		int[] list = { 1, 4, 5, 2, 8, 9 };
		CodeHack.quickSort(list, 0, list.length-1);
		
		assertEquals( 4, CodeHack.binarySearch(list, 8) );
		assertEquals( 2, CodeHack.binarySearch(list, 4) );
	}
	
	@Test
	public void testQuickSort() {
		int[] list = { 1, 4, 5, 2, 8, 9 };
		int[] sortedList = { 1, 2, 4, 5, 8, 9 };
		CodeHack.quickSort( list, 0, list.length-1 );
		
		assertEquals( 2, list[1] );
		assertEquals(true, Arrays.equals(list, sortedList) );
	}
	
	@Test
	public void testMergeSort() {
		int[] list = { 1, 4, 5, 2, 8, 9 };
		int[] sortedList = { 1, 2, 4, 5, 8, 9 };
		CodeHack.mergeSort( list, 0, list.length-1 );
		
		assertEquals( 2, list[1] );
		assertEquals(true, Arrays.equals(list, sortedList) );
	}
	
	@Test
	public void testsieveOfEratosthenes() {
		assertEquals( 3, CodeHack.SOE(5) );
		assertEquals( 4, CodeHack.SOE(7) );
		assertEquals( 25, CodeHack.SOE(100) );
		assertEquals( 46, CodeHack.SOE(199) );
		assertEquals( 168, CodeHack.SOE(1000) );
		
		assertEquals( 3, CodeHack.SOE2(5) );
		assertEquals( 4, CodeHack.SOE2(7) );
		assertEquals( 25, CodeHack.SOE2(100) );
		assertEquals( 46, CodeHack.SOE2(199) );
		assertEquals( 168, CodeHack.SOE2(1000) );
	}
	
	@Test
	public void testisPrime() {
		assertEquals( true, CodeHack.isPrime(17) );
		assertEquals( false, CodeHack.isPrime(4) );
		assertEquals( true, CodeHack.isPrime(2) );
		assertEquals( true, CodeHack.isLittleBetterPrime(17) );
		assertEquals( true, CodeHack.isLittleBetterPrime(2) );
		assertEquals( false, CodeHack.isLittleBetterPrime(4) );
	}
	
	@Test
	public void testBFS() {
		String[] nodeRels = {"1 2", "1 3", "2 4", "2 5", "4 8", "5 8", "3 6", "3 7", "6 8", "7 8"};
		for( String rel : nodeRels )	{
			int x = Integer.parseInt(rel.split(" ")[0]);
			int y = Integer.parseInt(rel.split(" ")[1]);
			CodeHack.gmap2[x][y] = CodeHack.gmap2[y][x] = 1;
		}
		
//		System.out.println( CodeHack.bfs(1) );
		assertEquals( "12345678", CodeHack.bfs(1) );
	}

	
	@Test
	public void testDFS() {
//		Scanner sc = new Scanner( System.in );
//		System.out.println( "input init data!!! (#MaxNode startPos)" );
//		String[] nodeNStart = sc.nextLine().split(" ");
//		
////		int maxNode = Integer.parseInt( nodeNStart[0] );
//		int start = Integer.parseInt( nodeNStart[1] );
		
		
//		System.out.println( "input rel!!! (#node #node, ... )" );
//		String[] nodeRels = sc.nextLine().split(",");
		String[] nodeRels = {"1 2", "1 3", "2 4", "2 5", "4 8", "5 8", "3 6", "3 7", "6 8", "7 8"};
		for( String rel : nodeRels )	{
			int x = Integer.parseInt(rel.split(" ")[0]);
			int y = Integer.parseInt(rel.split(" ")[1]);
			CodeHack.gmap[x][y] = CodeHack.gmap[y][x] = 1;
		}
		
		CodeHack.dfs(1);
		
//		sc.close();
//		1 2,1 3,2 4,2 5,4 8,5 8,3 6,3 7,6 8,7 8
	}
	
	@Test
	public void testQueue() {
		MyQueue qu = new MyQueue();
		
		qu.enqueue( 1 );
		qu.enqueue( 2 );
		
		assertEquals( 1, qu.dequeue() );
		assertEquals( 2, qu.dequeue() );
	}
	
	@Test
	public void testStack() {
		MyStack st = new MyStack();
		st.push( 1 );
		st.push( 2 );
		
		assertEquals( 2, st.pop() );
		assertEquals( 1, st.pop() );
	}

	
	@Test
	public void testLinkedListDel() {
		LLNode llnode = new LLNode();	//	how??? strange idx...
		llnode.appendToTail(2);
		
		assertEquals( -1, llnode.get(0).value() );
		assertEquals( 2, llnode.get(1).value() );
		
		llnode.delNode(llnode, 2);
		assertEquals( -1, llnode.get(0).value() );
		assertEquals( -1, llnode.get(1).value() );
	}
	
	@Test
	public void testLinkedListAdd() {
		LLNode llnode = new LLNode(1);
		llnode.appendToTail(2);
		
		assertEquals( 1, llnode.get(0).value() );
		assertEquals( 2, llnode.get(1).value() );
	}
	
	@Test
	public void testStringBuffer() {
		String[] words = { "code", "hack", "!!!" };
		String joinWords = CodeHack.joinWords( words );
		
		assertNotNull( joinWords );
		assertEquals( true, joinWords.contains(words[1]) );
	}
		
	
	@Test
	public void testArrayList() {
		String[] words = { "book", "code", "hack" };
		String[] more = { "1day", "pre", "1problem" };
		ArrayList<String> sentence = CodeHack.merge( words, more );
		
		assertNotNull( sentence );
		assertEquals( words[1], sentence.get(1) );
	}
	
	@Test
	public void testHashTable() {
		Student[] students = { new Student(1), new Student(2), new Student(3) };
		HashMap<Integer, Student> hashTable = CodeHack.buildHashTable(students);
		
		assertNotNull(hashTable);
		assertEquals(new Student(1), hashTable.get(new Student(1).getId()));
	}

}
