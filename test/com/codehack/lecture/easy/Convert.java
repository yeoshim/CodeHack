package com.codehack.lecture.easy;

import java.util.Scanner;

public class Convert {
    private static Scanner sc;
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int cnt = 0;
        while(cnt < cases) {
        	cnt++;
        	float amount = sc.nextFloat();
        	String type = sc.next();
        	
        	double unit = 0;
        	String unitStr = "";
        	
        	switch (type) {
			case "kg":
				unit = 2.2046;
				unitStr = " lb";
				break;
			case "l":
				unit = 0.2642;
				unitStr = " g";
				break;
			case "lb":
				unit = 0.4536;
				unitStr = " kg";
				break;
			case "g":
				unit = 3.7854;
				unitStr = " l";
				break;
			}
        	
        	System.out.print( cnt + " " );
        	System.out.printf( "%.4f", amount*unit );
        	System.out.print( unitStr );
        	System.out.println( "" );
        }
	}

}
