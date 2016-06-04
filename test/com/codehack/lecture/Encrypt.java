package com.codehack.lecture;

import java.util.Scanner;

public class Encrypt {
    private static Scanner sc;
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        
        while(cases-- > 0) {
        	char[] charArray = sc.next().toCharArray();
        	char[] tmp = new char[charArray.length/2];

        	for (int i = 0; i < charArray.length; i++) {
				if( i%2 == 0 )	System.out.print(charArray[i]);
				else	tmp[i/2] = charArray[i];
			}

        	for (char c : tmp) {
				System.out.print(c);
			}
        	System.out.println("");
        }
	}

}
