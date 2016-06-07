package com.codehack.lecture.basic;

import java.util.Scanner;

public class XhaeNeung {
    private static Scanner sc;
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        
        while(cases-- > 0) {
        	String aStr = sc.next();
        	String op = sc.next();
        	String bStr = sc.next();
        	sc.next();
        	
        	int retVal = cal( aStr, op, bStr );
        	
        	System.out.println( isRight(retVal, sc.next()) );
        }
	}

	private static String isRight(int retVal, String inputStr) {
		String valStr = itos(retVal);
		char[] valChars = valStr.toCharArray();
		char[] inputChars = inputStr.toCharArray();
		int matchCnt = 0;
		
		if( valChars.length != inputChars.length )	return "No";
		
		for (int i = 0; i < inputChars.length; i++) {
			for (int j = 0; j < valChars.length; j++) {
				if( inputChars[i] == valChars[j] )	{
					inputChars[i] = 0; 
					valChars[j] = '1';
					matchCnt++;
				}
			}
		}

		for (int i = 0; i < inputChars.length; i++) {
			if( inputChars[i] != 0 )	return "No";
		}
		
		if( matchCnt == inputChars.length )	return "Yes";
		else	return "No";

	}
	
	private static int cal(String aStr, String op, String bStr) {
		int a = stoi(aStr);
		int b = stoi(bStr);
		int retVal = 0;
		
		switch (op) {
		case "+":
			retVal = a + b;
			break;
		case "-":
			retVal = a - b;
			break;
		case "*":
			retVal = a * b;
			break;
		}
		
		return retVal;
	}

	private static int stoi(String str) {
		int val = 0;
		switch (str) {
		case "one":
			val = 1;
			break;
		case "two":
			val = 2;
			break;
		case "three":
			val = 3;
			break;
		case "four":
			val = 4;
			break;
		case "five":
			val = 5;
			break;
		case "six":
			val = 6;
			break;
		case "seven":
			val = 7;
			break;
		case "eight":
			val = 8;
			break;
		case "nine":
			val = 9;
			break;
		case "ten":
			val = 10;
			break;
		}
		
		return val;
	}

	private static String itos(int val) {
		String valStr = "No";
		switch (val) {
		case 0:
			valStr = "zero";
			break;
		case 1:
			valStr = "one";
			break;
		case 2:
			valStr = "two";
			break;
		case 3:
			valStr = "three";
			break;
		case 4:
			valStr = "four";
			break;
		case 5:
			valStr = "five";
			break;
		case 6:
			valStr = "six";
			break;
		case 7:
			valStr = "seven";
			break;
		case 8:
			valStr = "eight";
			break;
		case 9:
			valStr = "nine";
			break;
		case 10:
			valStr = "ten";
			break;
		}
		
		return valStr;
	}

	

}
