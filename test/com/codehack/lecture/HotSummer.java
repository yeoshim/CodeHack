package com.codehack.lecture;

import java.util.Scanner;

public class HotSummer {
    private static Scanner sc;
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        
        while(cases-- > 0) {
        	int maxWatt = sc.nextInt();
        	int sum = 0;
        	for (int i = 0; i < 9; i++) {
				sum += sc.nextInt();
			}
        	
        	if( sum > maxWatt )	System.out.println( "NO" );
        	else	System.out.println( "YES" );
        }
	}

}
