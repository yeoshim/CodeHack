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

}
