import static org.junit.Assert.*;

import org.junit.Test;

public class CHProblemTest {

	
	
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
