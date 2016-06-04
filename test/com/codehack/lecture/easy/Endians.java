package com.codehack.lecture.easy;

import java.util.Scanner;

public class Endians {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int cases = sc.nextInt();

		while (cases-- > 0) {
			String hex = Long.toHexString( sc.nextLong() );
			char[] hexStr = hex.toCharArray();
			char[] hexStr2 = new char[8];
			char[] reverse = new char[8];
			
			for (int i = 0; i < 8; i++) {
				hexStr2[i] = '0';
				reverse[i] = '0';
			}
			
			for (int i = 0; i < hexStr.length; i++) {
				hexStr2[8-hexStr.length+i] = hexStr[i];
			}
			
			for (int i = 0; i < 4; i++) {
				int idx = i*2;
				if( hexStr2[idx] == '0' && hexStr2[idx+1] == '0' )	continue;
				reverse[8-1-idx-1] = hexStr2[idx];		//	0->6	2->4	4->2	6->0
				reverse[8-(idx+1)] = hexStr2[idx+1];		//	1->7	3->5	5->3	7->1
			}
			
			System.out.println( Long.valueOf(new String(reverse), 16) );
//			System.out.println( Integer.parseInt(new String(reverse), 16) );	//	just [0-9] not [a-f]
		}
	}
}
