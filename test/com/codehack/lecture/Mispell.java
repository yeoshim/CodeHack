package com.codehack.lecture;

import java.util.Scanner;

public class Mispell {
    private static Scanner sc;
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int cnt = 0;
        
        while(cnt < cases) {
        	cnt++;
        	int delIdx = sc.nextInt();
        	char[] chArray = sc.next().toCharArray();
        	
        	System.out.print( cnt + " " );
        	for (int i = 0; i < chArray.length; i++) {
        		if( i == delIdx-1 )	continue;
				System.out.print(chArray[i]);
			}
        	System.out.println("");
        }
	}

}
