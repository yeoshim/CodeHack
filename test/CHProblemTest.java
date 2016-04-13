import static org.junit.Assert.*;

import org.junit.Test;

public class CHProblemTest {

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
