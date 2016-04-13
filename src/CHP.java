import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CHP {

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

}
