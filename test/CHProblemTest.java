import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

public class CHProblemTest {

	/*	P: SortedStack w/ ASC
	 *  sol: 2 while w/ 2 stacks
	 */
	@Test
	public void testSortedStack() throws Exception {
		int[] number1Data = { 7, 2, 3, 4, 5, 1, 6 };
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int item : number1Data) {
			stack.push( item );
		}

		Stack<Integer> sorted = CHP.sortAsc( stack );
		
		assertEquals( 1, (int)sorted.peek() );
		assertEquals( 1, (int)sorted.pop() );
		assertEquals( 2, (int)sorted.peek() );
	}

	
	/*	P: Queue w/ 2 stacks
	 *  sol: stack1 4 add, stack2 4 pop, if stack2 is empty move stack1 to stack2
	 */
	@Test
	public void testQueueWStacks() throws Exception {
		int[] number1Data = { 7, 2, 3, 4, 5, 1, 6 };
		QueueWStack<Integer> queue = new QueueWStack<Integer>();
		
		for (int item : number1Data) {
			queue.add( item );
		}

		assertEquals( 7, (int)queue.peek() );
		assertEquals( 7, (int)queue.pop() );
		assertEquals( 2, (int)queue.peek() );
	}
	
	/*	P: Hanoi Top Problem
	 *  sol: use stack & buf w/ recursion
	 */
	@Test
	public void testMoveDisks() throws Exception {
		int n = 3;
		Tower[] towers = new Tower[n];
		for (int i = 0; i < n; i++) {
			towers[i] = new Tower(i);
		}
		
		for (int i = n-1; i >= 0; i--) {
			towers[0].add( i );
		}
		
		towers[0].print();
		towers[2].print();
		
		System.out.println( "Move disks from 0 to 2" );
		towers[0].moveDisks( n, towers[2], towers[1] );
		towers[0].print();
		towers[2].print();
	}
	
	/*	P: Make a popAt(int idx) w/ SetOfStacks
	 *  sol: SetOfStacks has stack list as ArrayList()
	 */
	@Test
	public void testPopAt() throws Exception {
		int[] number1Data = { 7, 2, 3, 4, 5, 1, 6 };
		SetOfStacks stack = new SetOfStacks();
		
		for (int item : number1Data) {
			stack.push( item );
		}

		assertEquals( 6, stack.pop() );
		assertEquals( 3, stack.popAt(1) );
		assertEquals( 1, stack.popAt(2) );
		
		stack.push( 9 );	//	72459
		assertEquals( 4, stack.popAt(1) );
		assertEquals( 9, stack.popAt(2) );
		assertEquals( 5, stack.popAt(1) );
	}

	
	/*	P: Make a SetOfStacks
	 *  sol: SetOfStacks has stack list as ArrayList()
	 */
	@Test
	public void testSetOfStacks() throws Exception {
		int[] number1Data = { 7, 2, 3, 4, 5, 1, 6 };
		SetOfStacks stack = new SetOfStacks();
		
		for (int item : number1Data) {
			stack.push( item );
		}

//		while( !stack.isEmpty() )	{
//			System.out.println( stack.pop() );
//		}

		assertEquals( 6, stack.pop() );
		assertEquals( 1, stack.pop() );
	}

	
	/*	P: Make a stack w/ push, pop, min given Ot(1)
	 *  sol: 
	 *  	2. store minValue w/ a stack based on recursion call stack.
	 *  	if fist item of large stacks is min value, 
	 *  		sol1 is Os(n), sol2 is Os(1)
	 */
	@Test
	public void testStackWmin2() throws Exception {
		int[] number1Data = { 7, 2, 3, 4, 5, 1, 6 };
		StackWmin stack = new StackWmin();
		
		for (int item : number1Data) {
			stack.push2( item );
		}

//		while( !stack.isEmpty() )	{
//			System.out.println( stack.pop2() );
//		}

		assertEquals( 1, stack.min2() );
		stack.pop2();	//	6
		stack.pop2();	//	1
		assertEquals( 2, stack.min2() );
	}
	
	/*	P: Make a stack w/ push, pop, min given Ot(1)
	 *  sol: 
	 *  	1. store minValue based on recursion call stack, but Os(n).
	 */
	@Test
	public void testStackWmin() throws Exception {
		int[] number1Data = { 7, 2, 3, 4, 5, 1, 6 };
		StackWmin stack = new StackWmin();
		
		for (int item : number1Data) {
			stack.push( item );
		}

		assertEquals( 1, stack.min() );
		stack.pop();	//	6
		stack.pop();	//	1
		assertEquals( 2, stack.min() );

	}
	
	/*	P: Make 3 stacks w/ a array. 
	 *  sol2: dynamic size
	 *  - if no space, extends space and move data
	 *  - make circular array
	 */
	@Test
	public void testDStackData() throws Exception {
		int[] number1Data = { 7, 1, 2, 3, 4, 5, 6 };
		int[] number2Data = { 1, 2, 3, 4, 5 };
		DStack<Integer> stack = new DStack<Integer>();
		
		for (int item : number1Data) {
			stack.push( 0, item );
		}
		
		for (int item : number2Data) {
			stack.push( 1, item );
		}

		//	for test
//		for(Object item: stack.getBuf() )	{
//			System.out.print( " " + item );
//		}
//		System.out.println("");
		
		System.out.print( "DStack0:" );
		for (int i = 0; i < number1Data.length; i++) {
			System.out.print( " " + stack.pop(0) );
		}
		System.out.println();

		System.out.print( "DStack1:" );
		for (int i = 0; i < number2Data.length; i++) {
			System.out.print( " " + stack.pop(1) );
		}
		System.out.println();
		System.out.println( "===================" );

	}

	
	/*	P: Make 3 stacks w/ a array. 
	 * 	sol1: fixed size
	 * 	- [0, n/3), [n/3, 2n/3), [2n/3, n)
	 */
	@Test
	public void testStackData() throws Exception {
		int[] number1Data = { 0, 1, 2, 3, 4, 5, 6 };
		int[] number2Data = { 1, 2, 3, 4, 5 };
		MyStack2<Integer> stack = new MyStack2<Integer>();
		
		for (int item : number1Data) {
			stack.push( 0, item );
		}
		
		for (int item : number2Data) {
			stack.push( 1, item );
		}

		System.out.print( "Stack0:" );
		for (int i = 0; i < number1Data.length; i++) {
			System.out.print( " " + stack.pop(0) );
		}
		System.out.println();

		System.out.print( "Stack1:" );
		for (int i = 0; i < number2Data.length; i++) {
			System.out.print( " " + stack.pop(1) );
		}
		System.out.println();
		System.out.println( "===================" );

	}
	
	
	/*	P: Check Palindrome(회문). 
	 * 	sol1: reverse check w/ iterative
	 * 		1. find middle point w/ inserting half of list to stack.
	 * 		2. 2ptr(fast, slow) 4 finding middle point
	 * 		3. check if(last == stack.pop)
	 * 	sol2: reverse check w/ recursion
	 * 		...	if( callStack2 [2] == returned callStack1 [2] )
	 * 		[2] 3 2 1 0 | len = 3				callStack2
	 *		3 [2] 1 0 | len = 1, 3 is middle	callStack1
	 */
	@Test
	public void testPalindrome() {
		int[] number1Data = { 0, 1, 2, 3, 2, 1, 0 };
		LinkedListNodeG<Integer> number1 = null;
		
		for (int i : number1Data) {
			number1 = CHP.addNode(number1, i);
		}
		
		assertEquals( true, CHP.isPalindrome(number1) );
		assertEquals( true, CHP.isPalindrome2(number1, number1Data.length) );

		int[] number2Data = { 0, 1, 2, 3, 4, 2, 1, 0 };
		LinkedListNodeG<Integer> number2 = null;
		
		for (int i : number2Data) {
			number2 = CHP.addNode(number2, i);
		}
		
		assertEquals( false, CHP.isPalindrome(number2) );
		
	}
	
	/*	P: get first node in a circular linked list. (find loop in a linked list. (old problem))
	 * 	sol: additional pointers (fastRunner(+2)/slowRunner(+1))
	 *	1. check loop: SP(+1), FP(+2) while(SP != FP)	{ move SP & FP } => if(SP == FP), exist loop
	 *	2. when collision?: 
	 *		- if exist non-loop k nodes, move SP & FP K(k mod LOOP_SIZE) step.
	 *		- And then, SP's position is 0th node in circle, FP's position is Kth node in circle.
	 *		- SP is K steps behind FP in circle, FP is LOOP_SIZE-K steps behind SP
	 *		- FP catch-up 1 step SP each time because FP(-2) + SP(+1) = -1 and SP(-1) + FP(+2) = +1
	 *		=> after LOOP_SIZE-K steps, they will collision.
	 *	3. find loop start: 
	 *		- In LOOP_SIZE-K steps, SP's position is LOOP_SIZE-K.
	 *		=> move SP to Head(because we don't know K) and move SP & FP until they collision. (collision step is K)
	 */
	@Test
	public void testFindFirstOfLoop() {
		//	just for run
		LinkedListNodeG<Integer> loop = null;
		loop = CHP.addNode(loop, 1);
		
		@SuppressWarnings("unused")
		LinkedListNodeG<Integer> head = CHP.findFirstOfLoop( loop );
	}
	
	
	/*	P: add 2 numbers which are made by a linked list. 
	 * 	sol: iterative
	 *	꺼꾸로 처리하면 많은 고려사항들을 자동으로 해결됨 
	 */
	@Test
	public void testAddLists() {
		int[] number1Data = { 1, 7, 1, 6 };	//	6171
		int[] number2Data = { 5, 9, 2 };	//	295

		String result = "6646";	//	6466
		
		LinkedListNodeG<Integer> number1 = null;
		LinkedListNodeG<Integer> number2 = null;
		
		for (int i : number1Data) {
			number1 = CHP.addNode(number1, i);
		}
		
		for (int i : number2Data) {
			number2 = CHP.addNode(number2, i);
		}

		LinkedListNodeG<Integer> sum = CHP.addLists( number1, number2 );
		assertEquals( result, makeStringG(sum) );
		
		sum = CHP.addLists( number2, number1 );
		assertEquals( result, makeStringG(sum) );
	}
	
	/*	P: partition a linked list on the basis of value x, 
	 * 		given nodes(< x) should be located in front of nodes(>= x).
	 * 	sol: use 2 Linked Lists w/ end ptrs.
	 *  sol2: use 2 Linked Lists w/o end ptrs.
	 */
	@Test
	public void testPartition2() {
		int[] testData = { 2, 7, 4, 8, 1, 3, 9 };
		LinkedListNodeG<Integer> head = null;

		for (int i : testData) {
			head = CHP.addNode( head, i );
		}
		
//		for (int i = 0; i < testData.length; i++) {
//			System.out.print( CHP.getNode(head, i).value() + " " );
//		}
//		System.out.println( "" );
		
//		int x = 7;
		int x = 2;
		head = CHP.partition2(head, x);

		System.out.println( "partition2 w/ x :" + x );
		for (int i = 0; i < testData.length; i++) {
			System.out.print( CHP.getNode(head, i).value() + " " );
		}
		System.out.println( "" );
	}
	
	@Test
	public void testPartition() {
		int[] testData = { 2, 7, 4, 8, 1, 3, 9 };
		LinkedListNodeG<Integer> head = null;

		for (int i : testData) {
			head = CHP.addNode( head, i );
		}
		
		for (int i = 0; i < testData.length; i++) {
			System.out.print( CHP.getNode(head, i).value() + " " );
		}
		System.out.println( "" );
		
//		int x = 7;
		int x = 2;
		head = CHP.partition(head, x);

		System.out.println( "partition w/ x :" + x );
		for (int i = 0; i < testData.length; i++) {
			System.out.print( CHP.getNode(head, i).value() + " " );
		}
		System.out.println( "" );
	}

	
	/*	P: del one node which is placed middle position in a single linked list. Note: Can't access head node.
	 * 	sol: node1->node2->node3 
	 * 		=> copy next-node to cur-node: node2->node2->node3 
	 * 		=> cur-node link to next-next-node: node2->(node2)->node3
	 */
	@Test
	public void testDeleteNode() {
		String s1 = "aterbottle";
		LinkedListNode node = new LinkedListNode('w');
		
		for (char c : s1.toCharArray()) {
			node.add( c );
		}
		
		CHP.deleteNode( node );
		
		String expected = "aterbottle";
		assertEquals( expected, makeString(node) );
		
		//	if just one node, can't delete node. => just mark as 'last node' ???
		LinkedListNode oneNode = new LinkedListNode('w');
		CHP.deleteNode( oneNode );
		assertEquals( "w", makeString(oneNode) );
	}

	
	/*	P: find k-th item from last.
	 * 	sol1: recursion travel => just print		Ot(n), Os(n)
	 *  sol2: recursion w/ PosFromLast class		Ot(n), Os(n)
	 *  sol3: iterative w/ 2 Ptrs(w/ k interval)	Ot(n), Os(1)
	 */
	@Test
	public void testNthToLast() {
		String s1 = "waterbottle";
		LinkedListNode node = new LinkedListNode('w');	// how to solve???
		
		for (char c : s1.toCharArray()) {
			node.add( c );
		}
		
		CHP.printNthToLast(node, 3);	//	just print because function can't return 2 value.
		CHP.printNthToLast(node, 5);	//	'o'
		
		char expected = 't';
		assertEquals( expected, CHP.nthToLast(node, 3, new PosFromLast()).data );
		assertEquals( expected, CHP.nthToLastIter(node, 3).data );
	}

	
	
	/*	P: delete duplicate chars in a string which is made by unsorted linked list.
	 * 	sol1: Twice travel w/ LL => Ot(n^2), Os(1) 
	 */
	@Test
	public void testDeleteDupsNoBuf() {
		String s1 = "waterbottle";
		LinkedListNode node = new LinkedListNode('w');	// how to solve???
		
		for (char c : s1.toCharArray()) {
			node.add( c );
		}
		
		CHP.deleteDupsNoBuf(node);
		
		String expected = "waterbol";
		assertEquals( expected, makeString(node) );
	}

	
	/*	P: delete duplicate chars in a string which is made by unsorted linked list.
	 * 	sol1: hashTable as a buffer => Ot(n), Os(n) 
	 */
	@Test
	public void testDeleteDups() {
		String s1 = "waterbottle";
	//	LinkedListNode node = new LinkedListNode();
		LinkedListNode node = new LinkedListNode('w');	// how to solve???
		
		for (char c : s1.toCharArray()) {
			node.add( c );
		}
		
		CHP.deleteDups(node);
		
		String expected = "waterbol";
		assertEquals( expected, makeString(node) );
	//	assertArrayEquals( expected.toCharArray(), makeChars(node, expected.length()) );
	}

	private String makeStringG(LinkedListNodeG<Integer> node) {
		String str = "";
		while( node.next != null )	{
			str += node.value();
			node = node.next;
		}
		
		return str += node.value();
	}
	
	private String makeString(LinkedListNode node) {
		String str = "";
		while( node.next != null )	{
			str += node.data;
			node = node.next;
		}
		
		return str += node.data;
	}
	
	
	@SuppressWarnings("unused")
	private char[] makeChars(LinkedListNode node, int len) {
		char[] str = new char[len];
		int idx = 0;
		while( node.next != null )	{
			str[idx] = node.data;
			idx++;
			node = node.next;
		}
		
		str[idx] = node.data;
		return str;
	}

	/*	P: binomial coefficient(all case selecting Rs items in Ns w/ disorder)
	 * 	sol1: all case w/ recursion
	 *  bino(N, R) = bino(N-1, R-1) + bino(N-1, R)
	 *  bino(0, R) = bini(N, N) = 1
	 */
	@Test
	public void testBino() {
		CHP.initCache();
		assertEquals(6, CHP.bino2(4, 2));
		assertEquals(6, CHP.bino(4, 2));
	}

	
	/*	P: check substring which is rotated string
	 * 	sol1: 
	 *  if s2 is s1's rotated string, s1 = xy, s2 = yx
	 *  and yx is substring of xyxy => s2 is substring of s1s1
	 *  that is, subString( s1s1, s2 )
	 */
	@Test
	public void testIsSubString() {
		String s1 = "waterbottle";
		String s2 = "erbottlewat";
		assertTrue( CHP.isRotation(s1, s2) );
		assertFalse( CHP.isRotation(s1, "erbottlewa1") );
	}
	
	/*	P: At MxN matrix if [i][j] = 0, [i][*] = 0 and [*][j] = 0
	 * 	sol: first check 0 then set 0 because check and replace will become all 0
	 * => Ot(N^M), Os(MN)
	 *  sol2: use bit vector => Os(x<MN)	// not implemented
	 */
	@Test
	public void testSetZero() {
		/*
		 * 00, 02, 03, 04
		 * 05, 06, 00, 08
		 * 09, 10, 11, 12
		 * 13, 14, 15, 16
		 * 		 |
		 * 		 V
		 * 00, 00, 00, 00
		 * 00, 00, 00, 00
		 * 00, 10, 00, 12
		 * 00, 14, 00, 16
		 */
		int[][] matrix = new int[4][4];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = j+(i*matrix.length)+1;
			}
		}
		matrix[0][0] = 0;
		matrix[1][2] = 0;

		CHP.setZero( matrix );
		
		int[] expected = {0, 0, 0, 0};
		assertArrayEquals(expected, matrix[0]);
		assertArrayEquals(expected, matrix[1]);
		assertEquals(0, matrix[2][0]);
		assertEquals(0, matrix[2][2]);
		assertEquals(0, matrix[3][0]);
		assertEquals(0, matrix[3][2]);
		assertNotEquals(0, matrix[3][3]);
	}
		
	
	/*	P: rotate img 90 (4 x 4)
	 * 	sol1: Brute-Force & Ot(n^2), this is best.
	 * 
	 * #. note!!!
	 * 1. left/bottom: reverse change, top/right: normal change
	 * 2. use offset 4 considering layer when layer > 0 
	 * => offset = i-first(layer) => last - offset(i-first)
	 */
	@Test
	public void testRotateImg() {
		/*
		 * 01, 02, 03, 04
		 * 05, 06, 07, 08
		 * 09, 10, 11, 12
		 * 13, 14, 15, 16
		 * 		 |
		 * 		 V
		 * 13, 09, 05, 01
		 * 14, 10, 06, 02
		 * 15, 11, 07. 03
		 * 16, 12, 08, 04
		 */
		int[][] img = new int[4][4];
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img.length; j++) {
				img[i][j] = j+(i*img.length)+1;
			}
		}

		CHP.rotateImg( img, img[0].length );
		
		int[] expected = {13, 9, 5, 1};
		assertArrayEquals(expected, img[0]);
	}
	
	/*	P: compress String, aabccccccccaaa -> a2b1c8a3, if) abc -> a1b1c1 => just ori return
	 * 	sol2: Brute-Force w/ char process instead of StringBuffer. Ot(p+k)
	 */
	@Test
	public void testCompressStrAlter() {
		String s1 = "aabccccccccaaa";
		assertEquals( "a2b1c8a3", CHP.compressAlter(s1) );
		
		String s2 = "abc";
		assertEquals( "abc", CHP.compressAlter(s2) );
	}

	
	/*	P: compress String, aabccccccccaaa -> a2b1c8a3, if) abc -> a1b1c1 => just ori return
	 * 	sol1: Brute-Force w/ StringBuffer. Ot(p+k)
	 */
	@Test
	public void testCompressStrWStrBuf() {
		String s1 = "aabccccccccaaa";
		assertEquals( "a2b1c8a3", CHP.compressSB(s1) );
		
		String s2 = "abc";
		assertEquals( "abc", CHP.compressSB(s2) );
	}

	
	/*	P: compress String, aabccccccccaaa -> a2b1c8a3, if) abc -> a1b1c1 => just ori return
	 * 	sol1: Brute-Force. Ot(p+k^2) because of Java Str copy's Ot(n^2)
	 */
	@Test
	public void testCompressStr() {
		String s1 = "aabccccccccaaa";
		assertEquals( "a2b1c8a3", CHP.compressBF(s1) );
		
		String s2 = "abc";
		assertEquals( "abc", CHP.compressBF(s2) );
	}
	
	/*	P: convert WS -> '%20' in str.
	 * 	sol2: processing from last to first of str.
	 */
	@Test
	public void testReplaceSpaces() {
		char[] strBuf = new char[100];
		String s1 = "Hi  code hack !!! ";
		String s2 = "Hi%20%20code%20hack%20!!!%20";
		
		char[] chars = s1.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			strBuf[i] = chars[i];
		}
		
		CHP.replaceSpaces( strBuf, s1.length() );
		
		char[] actual = new char[s2.length()];
		for (int i = 0; i < s2.length(); i++) {
			actual[i] = strBuf[i];
		}
		
		assertArrayEquals( s2.toCharArray(), actual );
	}
	
	/*	P: Check permutation relation between s1 and s2 => make new str, changing chars ordering(Anagram problem)
	 *  Q: check char set (ASCII or Unicode) => ASCII
	 * 	sol2: check char count.
	 */
	@Test
	public void testPermutation2() {
		String s1 = "dog";
		String s2 = "god";
		assertEquals( true, CHP.permutation2(s1, s2) );
		
		String s11 = "dog1";
		String s22 = "god2";
		assertEquals( false, CHP.permutation2(s11, s22) );
	}

	
	/*	P: Check permutation relation between s1 and s2 => make new str, changing chars ordering(Anagram problem)
	 *  Q: isCheck case sensitive?, how processing white space?
	 *   => check case sensitive, white space is 1 char 
	 * 	sol: sorted chars is same.
	 */
	@Test
	public void testPermutation() {
		String s1 = "dog";
		String s2 = "god";
		assertEquals( true, CHP.permutation(s1, s2) );
		
		String s11 = "dog1";
		String s22 = "god2";
		assertEquals( false, CHP.permutation(s11, s22) );
	}
	
	/*	P: Check unique chars in a String
	 * 	sol: sorting then compare near chars
	 */
	@Test
	public void testCheckUniqueChars2() {
		String inputStr = "inputSr";
		assertEquals( true, CHP.checkUniqueChars2(inputStr) );
		
		String inputStr2 = "inputStrinputStr";
		assertEquals( false, CHP.checkUniqueChars2(inputStr2) );
	}

	
	/*	P: Check unique chars in a String
	 * 	sol: bool[] chars
	 */
	@Test
	public void testCheckUniqueChars() {
		String inputStr = "inputSr";
		assertEquals( true, CHP.checkUniqueChars(inputStr) );
		
		String inputStr2 = "inputStrinputStr";
		assertEquals( false, CHP.checkUniqueChars(inputStr2) );
	}
}

class LinkedListNodeG<T> {

	private T value;
	public LinkedListNodeG<T> next = null;

	public LinkedListNodeG(T value) {
		this.value = value;
	}

	public T value() {
		return this.value;
	}
}
