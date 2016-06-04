package com.codehack.lecture.basic;

import java.util.Scanner;

public class URI {
    private static Scanner sc;
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        
        while(cases-- > 0) {
        	char[] inputChars = sc.next().toCharArray();
        	String code = "";
        	boolean isCode = false;
        	
        	for (char ch : inputChars) {
        		if( ch == '%' || isCode )	{
        			code += ch;

        			if( code.length() == 3 )	{
        				switch (code) {
        				case "%20":
        					System.out.print(" ");
        					break;
	    				case "%21":
	    					System.out.print("!");
	    					break;
        				case "%24":
        					System.out.print("$");
        					break;
	    				case "%25":
	    					System.out.print("%");
	    					break;
        				case "%28":
        					System.out.print("(");
        					break;
	    				case "%29":
	    					System.out.print(")");
	    					break;
	    				case "%2a":
	    					System.out.print("*");
	    					break;
	    				}
        				
        				isCode = false;
        				code = "";
        			}
        			else	{
        				isCode = true;
        			}
        		}
        		else	System.out.print(ch);
        		
			}
        	System.out.println("");
        }
	}

}
