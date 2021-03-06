import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;

public class CHP {

	private static final int MAX_CACHE = 100;
	private static int[][] cache = new int[MAX_CACHE][MAX_CACHE];

	/*	P: Check unique chars in a String
	 * 	is ASCII or Unicode
	 * 	if ASCII, just return false when length > 256
	 * 	bool[] chars: Ot(n), Os(1)
	 */
	public static boolean checkUniqueChars(String inputStr) {
		final int MAX = 256;

		if( inputStr.length() > 256 )	return false;
		boolean[] chars = new boolean[MAX];
		int cnt = 0;
		
		while( inputStr.length() > cnt )	{
			if( chars[inputStr.charAt(cnt)] )	return false;
			chars[inputStr.charAt(cnt)] = true;
			cnt++;
		}
		
		return true;
	}

	/*	P: Check unique chars in a String
	 * 	is ASCII or Unicode
	 * 	if ASCII, just return false when length > 256
	 * 	sorting chars then compare near chars: Ot(nlogn), Os(logn)
	 */
	public static boolean checkUniqueChars2(String inputStr) {
		//	not consider Ot because this process isn't related to algorithm.
		ArrayList<Character> chars = new ArrayList<Character>();
		for (int i = 0; i < inputStr.length(); i++) {
			chars.add( inputStr.charAt(i) );
		}

		//	Ot(nlogn), Os(logn)
		Collections.sort(chars);
//		CodeHack.quickSort(list, 0, list.length);
		
		//	Ot(n?), Os(1)
		for (int i = 0; i < chars.size()-1; i++) {
			if( chars.get(i).equals(chars.get(i+1)) )	return false;
		}
		
		return true;
	}

	public static boolean permutation(String s1, String s2) {
		if( s1.length() != s2.length() )	return false;
		return sort(s1).equals(sort(s2));
	}

	private static String sort(String str) {
		char[] content = str.toCharArray();
		Arrays.sort( content );
		return new String( content );
	}

	public static boolean permutation2(String s1, String s2) {
		if( s1.length() != s2.length() )	return false;
		
		int[] letters = new int[256];
		char[] s1Array = s1.toCharArray();
		for (char c : s1Array) {
			letters[c]++;
		}
		
		for (int i = 0; i < s2.length(); i++) {
			int c = s2.charAt(i);
			if( --letters[c] < 0 )	return false;
		}
		
		return true;
	}

	/*
	 * first, scan for checking WS cnt.
	 * second, scan w/ reverse direction for replace %20.
	 */
	public static void replaceSpaces(char[] cs, int length) {
		// scan 4 WS cnt -> use extra array based on new length(ori array + extra array)
		int spaceCnt = 0;
		for (int i = 0; i < length; i++) {
			if( cs[i] == ' ' )	spaceCnt++;
		}
		
		//	reverse direction scan for not considering ori array
		//	because result array will be longer array than ori array.
		int newLength = length + (spaceCnt * 2);
		cs[newLength] = '\0';
		for (int i = length-1; i >= 0; i--) {
			if( cs[i] == ' ' )	{
				cs[newLength-1] = '0';
				cs[newLength-2] = '2';
				cs[newLength-3] = '%';
				newLength -= 3;
			}
			else	{
				cs[newLength-1] = cs[i];
				newLength -= 1;
			}
		}
	}

	public static String compressBF(String str) {
		String compressedStr = "";
		char c = str.charAt(0);
		int charCnt = 1;
		
		for (int i = 1; i < str.length(); i++) {
			if( c == str.charAt(i) )	charCnt++;
			else	{
				compressedStr += (c + "" + charCnt);
				c = str.charAt(i);
				charCnt = 1;
			}
		}
		
		//	caution: append last check result
		if( str.length() > compressedStr.length() )	return compressedStr + (c + "" + charCnt);
		else	return str;
	}

	//	w/ StringBuffer
	public static String compressSB(String str) {
		StringBuffer compStr = new StringBuffer();

		char last = str.charAt(0);
		int charCnt = 1;
		for (int i = 1; i < str.length(); i++) {
			if( str.charAt(i) == last )	charCnt++;
			else	{
				compStr.append(last).append(charCnt);
				last = str.charAt(i);
				charCnt = 1;
			}
		}

		String result = compStr.append(last).append(charCnt).toString();
		if( str.length() < result.length() )	return str;
		else	return result;
	}

	public static String compressAlter(String str) {
		int size = countComp( str );
		if( size > str.length() )	return str;
		
		char[] result = new char[size];
		char last = str.charAt(0);
		int charCnt = 1;
		int index = 0;
		for (int i = 1; i < str.length(); i++) {
			if( last == str.charAt(i) )	charCnt++;
			else	{
				index = setChar( str, result, last, index, charCnt );
				last = str.charAt(i);
				charCnt = 1;
			}
		}
		
		setChar( str, result, last, index, charCnt );
		return String.valueOf(result);
	}

	private static int setChar(String str, char[] result, char last, int index, int charCnt) {
		result[index] = last;
		index++;
		
		char[] cnt = String.valueOf(charCnt).toCharArray();
		for (char c : cnt) {
			result[index] = c;
			index++;
		}
		
		return index;
	}

	private static int countComp(String str) {
		char last = str.charAt(0);
		int totalLen = 0;
		
		for (int i = 0; i < str.length(); i++) {
			if( last != str.charAt(i) )	{
				last = str.charAt(i);
				totalLen += 2;
			}
		}
		
		return totalLen+2;
	}

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
	 * 
	 * #. note!!!
	 * 1. left/bottom: reverse change, top/right: normal change
	 * 2. use offset 4 considering layer when layer > 0 
	 * => offset = i-first(layer) => last - offset(i-first)
	 */
	public static void rotateImg(int[][] img, int n) {
		//	4 -> 4/2=2 repeat
		for( int layer = 0; layer < n/2; layer++ )	{	//	0,1
			int first = layer;
			int last = n-layer-1;	//	for note1
			for( int i = first; i < last; i++ )	{	//	0->0-3, 1->1-2
				int offset = i-first;	//	for note2

				int top = img[first][i];
				// left [i][0] -> top [0][i]
				img[first][i] = img[last-offset][first];
				
				// bottom [3][i] -> left [i][0]
				img[last-offset][first] = img[last][last-offset];
				
				//	right [i][3] -> bottom [3][i]
				img[last][last-offset] = img[i][last];
				
				//	top [0][i] -> right [i][3]
				img[i][last] = top;
			}
		}
	}

	public static void setZero(int[][] matrix) {
		boolean[] row = new boolean[matrix.length];
		boolean[] column = new boolean[matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {	
			for (int j = 0; j < matrix[0].length; j++) {
				if( matrix[i][j] == 0 )	{
					row[i] = true;
					column[j] = true;
				}
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if( row[i] || column[j] )	{
					matrix[i][j] = 0;
				}
			}
		}
	}

	public static boolean isRotation(String s1, String s2) {
		if( s1.length() != s2.length() )	return false;
		if( s1.length() == 0 )	return false;
		
		return s1.concat(s1).contains(s2);
	}

	//	bino(n, r) = bino(n-1, r-1) + bino(n-1, r)
	public static int bino(int n, int r) {
		if( r == 0 || n == r )	return 1;
		
		return bino(n-1, r-1) + bino(n-1, r);
	}

	public static int bino2(int n, int r) {
		if( r == 0 || n == r )	return 1;
		
		if( cache[n][r] != -1 ) return cache[n][r]; 
		
		return cache[n][r] = bino(n-1, r-1) + bino(n-1, r);
	}

	public static void initCache() {
		for (int i = 0; i < MAX_CACHE; i++) {
			for (int j = 0; j < MAX_CACHE; j++) {
				cache[i][j] = -1;
			}
		}
	}

	public static void deleteDups(LinkedListNode node) {
		Hashtable<Character, Boolean> table = new Hashtable<Character, Boolean>();
		LinkedListNode previous = null;
		
		while( node != null )	{
			if( table.containsKey(node.data) )	{
				previous.next = node.next;
			}
			else	{
				table.put(node.data, true);
				previous = node;
			}
			node = node.next;
		}
	}
	
	public static void deleteDupsNoBuf(LinkedListNode head) {
		if( head == null )	return;
		
		LinkedListNode current = head;
		//	N x N travel => Ot(N^2)
		while( current != null )	{
			LinkedListNode runner = current;
			while( runner.next != null )	{
				if( runner.next.data == current.data )	{
					runner.next = runner.next.next;
				}
				else	{
					runner = runner.next;
				}
			}
			current = current.next;
		}
		
	}

	public static int printNthToLast(LinkedListNode head, int k) {
		if( head == null )	return 0;
		
		int posFromLast = printNthToLast(head.next, k) + 1;
		if( posFromLast == k )	System.out.println( k + "-th item: " + head.data );
		
		return posFromLast;
	}

	public static LinkedListNode nthToLast(LinkedListNode head, int k, PosFromLast pos) {
		if( head == null )	return null;
		
		LinkedListNode node = nthToLast(head.next, k, pos );
		pos.value += 1;
		if( pos.value == k )	return head;
		
		return node;
	}

	public static LinkedListNode nthToLastIter(LinkedListNode head, int k) {
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		
		// make k interval between p1 and p2
		for (int i = 0; i < k; i++) {
			if( p2 == null )	return null;
			p2 = p2.next;
		}
		if( p2 == null )	return null;
		
		//	move until p2 == null (last)
		while( p2 != null )	{
			p2 = p2.next;
			p1 = p1.next;
		}
		
		return p1;
	}

	public static boolean deleteNode(LinkedListNode node) {
		if( node == null || node.next == null )	return false;
		LinkedListNode next = node.next;
		node.data = next.data;
		node.next = next.next;
		
		return true;
	}

	public static LinkedListNodeG<Integer> getNode(LinkedListNodeG<Integer> head, int idx) {
		if( head == null )	return null;
		
		LinkedListNodeG<Integer> retNode = head;
		int nodeIdx = 0;
		while( nodeIdx < idx && retNode.next != null )	{
			retNode = retNode.next;
			nodeIdx++;
		}
		
		if( nodeIdx == idx )	return retNode;
		
		return new LinkedListNodeG<Integer>(-1);
	}


	public static LinkedListNodeG<Integer> addNode(LinkedListNodeG<Integer> head2, int value) {
		LinkedListNodeG<Integer> head = head2;
		LinkedListNodeG<Integer> newNode = new LinkedListNodeG<Integer>(value);
		
		if( head2 == null )	{
			head2 = newNode;
			head = newNode;
		}
		else	{
			while( head2.next != null )	{
				head2 = head2.next;
			}
			head2.next = newNode;
		}
		
		return head;
	}

	public static LinkedListNodeG<Integer> partition(LinkedListNodeG<Integer> node, int x) {
		LinkedListNodeG<Integer> beforeStart = null;
		LinkedListNodeG<Integer> beforeEnd = null;
		LinkedListNodeG<Integer> afterStart = null;
		LinkedListNodeG<Integer> afterEnd = null;
		
		while( node != null )	{
			LinkedListNodeG<Integer> next = node.next;
			node.next = null;
			
			if( node.value() < x )	{
				if( beforeStart == null )	{
					beforeStart = node;
					beforeEnd = beforeStart;
				}
				else	{
					beforeEnd.next = node;
					beforeEnd = node;
				}
			}
			else	{
				if( afterStart == null )	{
					afterStart = node;
					afterEnd = afterStart;
				}
				else	{
					afterEnd.next = node;
					afterEnd = node;
				}
			}
			node = next;
		}
		
		if( beforeStart == null )	{
			return afterStart;
		}
		
		beforeEnd.next = afterStart;
		
		return beforeStart;
	}

	public static LinkedListNodeG<Integer> partition2(LinkedListNodeG<Integer> node, int x) {
		if( node == null )	return null;
		
		LinkedListNodeG<Integer> beforeX = null;
		LinkedListNodeG<Integer> afterX = null;
		
		while( node != null )	{
			LinkedListNodeG<Integer> next = node.next;	//	because linked list may be modified during traverse processing 
			if( node.value() < x )	{
				node.next = beforeX;
				beforeX = node;
			}
			else	{	//	[ax]->[] => (nn)->[ax]->[] => [ax]->[]->[]
				node.next = afterX;
				afterX = node;
			}
			
			node = next;
		}

		if( beforeX == null )	return afterX;

		LinkedListNodeG<Integer> head = beforeX;
		while( beforeX.next != null )	{
			beforeX = beforeX.next;
		}
		
		//	move last node(x) to first of afterX
		LinkedListNodeG<Integer> afterNode = afterX;
		while( afterNode.next != null )	{
			afterNode = afterNode.next;
		}
		afterNode.next = afterX;
		afterX = afterNode;
		
		beforeX.next = afterX;
		
		return head;
	}

	public static LinkedListNodeG<Integer> addLists(LinkedListNodeG<Integer> number1,
			LinkedListNodeG<Integer> number2) {
		if( number1 == null && number2 == null )	return null;
		
		LinkedListNodeG<Integer> sumNode = null;
		int sum = 0;

		while( number1 != null || number2 != null )	{
			int num1 = 0;
			int num2 = 0;
			if( number1 != null )	{
				num1 = number1.value();
				number1 = number1.next;
			}
			if( number2 != null )	{
				num2 = number2.value();
				number2 = number2.next;
			}
			
			sum = num1 + num2 + sum/10;
			sumNode = addNode( sumNode, sum%10 );
		}
		
		return sumNode;
	}

	public static LinkedListNodeG<Integer> findFirstOfLoop(LinkedListNodeG<Integer> head) {
		//	SP, FP
		LinkedListNodeG<Integer> slow = head;
		LinkedListNodeG<Integer> fast = head;
		
		//	find collision point(CP)
		while( fast != null && fast.next != null )	{
			slow = slow.next;
			fast = fast.next.next;
			
			if( slow == fast )	break;
		}
		
		//	no collision is no loop
		if( fast == null || fast.next == null )	return null;
		
		slow = head;
		while( slow != fast )	{	//	K step
			slow = slow.next;
			fast = fast.next;
		}
		
		return fast;
	}

	public static boolean isPalindrome(LinkedListNodeG<Integer> head) {
		Stack<Integer> stack = new Stack<Integer>();
		LinkedListNodeG<Integer> fast = head;
		LinkedListNodeG<Integer> slow = head;
		
		//	find middle point
		while( fast != null && fast.next != null )	{
			stack.push( slow.value() );
			slow = slow.next;
			fast = fast.next.next;
		}
		
		//	odd length case, skip middle point
		if( fast != null )	slow = slow.next;
		
		while( slow != null )	{
			int top = stack.pop().intValue();
			if( top != slow.value() )	return false;
			slow = slow.next;
		}
		
		return true;
	}

	public static boolean isPalindrome2(LinkedListNodeG<Integer> head, int size) {
		Result ret = isPalindromeRecurse( head, size );
		return ret.result;
	}

	private static Result isPalindromeRecurse(LinkedListNodeG<Integer> head, int size) {
		//	base condition
		if( head == null || size == 0 )	return new Result( null, true );
		else if( size == 1 )	return new Result( head.next, true );
		//	if case size=2(even case), skip one node
		//	size>=3 => same case size=1
		else if( size == 2 )	return new Result( head.next.next, head.value() == head.next.value() );
		
		/*	#. induction 4 find middle
		 * recurse( Node n, int len )	{
		 * 	if( len == 0 || len == 1 )	return middle;
		 * 	recurse( n.next, len-2 );
		 * }
		 * 	
		 * ...	if( callStack2 [2] == returned callStack1 [2] )
		 * [2] 3 2 1 0 | len = 3				callStack2
		 *   3 [2] 1 0 | len = 1, 3 is middle	callStack1
		 */
		Result res = isPalindromeRecurse( head.next, size-2 );
		if( !res.result || res.node == null )	return res;
		else	{
			res.result = head.value() == res.node.value();
			res.node = res.node.next;
			return res;
		}
	}

	public static Stack<Integer> sortAsc(Stack<Integer> stack) {
		Stack<Integer> sorted = new Stack<Integer>();
		while( !stack.isEmpty() )	{
			int tmp = stack.pop();
			while( !sorted.isEmpty() && sorted.peek() < tmp )	{
				stack.push( sorted.pop() );
			}
			sorted.push( tmp );
		}
		
		return sorted;
	}

}

class Result	{
	LinkedListNodeG<Integer> node;
	boolean result;

	public Result(LinkedListNodeG<Integer> node, boolean result) {
		this.result = result;
		this.node = node;
	}
}

class PosFromLast	{
	int value = 0;
}
