package com.codehack.lecture;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Lecture {
    private static Scanner sc;
    
    static class Str2	{
		private String str;

		public Str2(char ch1, char ch2) {
			this.str = "" + ch1 + ch2;
		}
    }
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        
        while(cases-- > 0) {
        	String str = sc.next();
        	char[] strArray = str.toCharArray();
        	Str2[] str2Array = new Str2[strArray.length/2];
        	
        	for (int i = 1; i <= strArray.length/2; i++) {
        		str2Array[i-1] = new Str2( strArray[i*2-2], strArray[i*2-1] );
			}
        	
        	Arrays.sort( str2Array, new Comparator<Str2>() {
				@Override
				public int compare(Str2 o1, Str2 o2) {
					return o1.str.compareTo( o2.str );
				}
			});

        	for (Str2 str2 : str2Array) {
				System.out.print( str2.str );
			}
        	System.out.println("");
        }
	}
}

