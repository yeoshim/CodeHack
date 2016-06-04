package com.codehack.lecture.basic;

import java.util.HashMap;
import java.util.Scanner;

public class CoinCollect {
    private static Scanner sc;
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int cnt = 0;
        
//        while(cases-- > 0) {
        while(cnt++ < cases) {
        	@SuppressWarnings("unused")
			int boardSize = sc.nextInt();
        	int coinPointNum = sc.nextInt();
        	int pathPointNum = sc.nextInt();
        	
        	HashMap<String, Integer> coinPoint = new HashMap<String, Integer>();
        	
        	for (int i = 0; i < coinPointNum; i++) {
        		coinPoint.put( ""+sc.nextInt()+sc.nextInt(), sc.nextInt() );
			}

        	int prevY = -1;
        	int prevX = -1;
        	int sum = 0;
        	for (int i = 0; i < pathPointNum; i++) {
        		int y = sc.nextInt();
        		int x = sc.nextInt();
        		
        		if( prevY == y )	{
        			//	(y, prevX) => (y, x)
        			for (int j = prevX+1; j <= x; j++) {
        				sum += coinPoint.getOrDefault(""+y+j, 0);
//        				System.out.println( "sum w/ H(" + y + "" + j + "): " + sum );
					}
        		}
        		else if( prevX == x )	{
        			//	(prevY, x) => (y, x)
        			for (int j = prevY+1; j <= y; j++) {
        				sum += coinPoint.getOrDefault(""+j+x, 0);
//        				System.out.println( "sum w/ V(" + j + "" + x + "): " + sum );
					}
        		}
        		else	{
        			sum += coinPoint.getOrDefault(""+y+x, 0);
//        			System.out.println( "sum w/ D(" + y + "" + x + "): " + sum );
        		}
        		
        		prevY = y;
        		prevX = x;
			}
        	
        	System.out.println( "#"+cnt + " " + sum );
        }
	}

}
