import static org.junit.Assert.*;

import org.junit.Test;

public class CHProblemTest {

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
	
	
	/*	P: delete duplicate chars in string w/ unsorted linked list
	 * 	sol1: 
	 *  if s2 is s1's rotated string, s1 = xy, s2 = yx
	 *  and yx is substring of xyxy => s2 is substring of s1s1
	 *  that is, subString( s1s1, s2 )
	 */
/*	@Test
	public void testDeleteDups() {
		String s1 = "hellohello";
		String s2 = "helo";
		
		LLNode<Character> llNode = new LLNode<Character>();
		for (char c : s1.toCharArray()) {
			System.out.println( "c: " + c );
			llNode.appendToTail(c);
		}
		
		for (int i = 0; i < s1.length(); i++) {
			System.out.print( llNode.get(i) );
		}
		
//		LLNode<Character> llNode2 = CHP.deleteDups(llNode);
//		for (int i = 0; i < s2.length(); i++) {
//			System.out.print( llNode2.get(i) );
//		}
		
//		assertEquals( s2, CHP.deleteDups(llNode) );
	}
*/
	
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
