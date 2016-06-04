package com.codehack.lecture;

import java.util.Scanner;

public class DrawRect {
    private static Scanner sc;
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        
        while(cases-- > 0) {
        	int[] x = new int[1001];
        	int[] y = new int[1001];
        	int retX = 0;
        	int retY = 0;
        	
        	for (int i = 0; i < 3; i++) {
				x[sc.nextInt()]++;
				y[sc.nextInt()]++;
			}

        	for (int i = 0; i < 1001; i++) {
        		if( x[i] == 1 )	retX = i;
        		if( y[i] == 1 )	retY = i;
			}
        	
        	System.out.println( retX + " " + retY );
        }
	}

}
