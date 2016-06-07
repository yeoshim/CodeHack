package com.codehack.lecture.basic;

import java.util.Scanner;
import java.util.Stack;

public class FixParen {
    private static Scanner sc;
    
    static class Pair	{
		private int idx;
		private char ch;

		public Pair(int idx, char ch) {
			this.idx = idx;
			this.ch = ch;
		}
    }
    
	public static void main(String[] args) {
        sc = new Scanner(System.in);
        int cases = sc.nextInt();
        
        while(cases-- > 0) {
        	char[] input = sc.next().toCharArray();
        	char[] priority = sc.next().toCharArray();
        	
        	fixParen( input, priority );
        	if( cases != 0 )	System.out.println("");
        }
	}

	private static void fixParen(char[] input, char[] priority) {
		Stack<Pair> openStack = new Stack<Pair>();
		
		String openStr = "{(<[";
		
		//	check open/close	{(<[ / ]>)}
		for (int i = 0; i < input.length; i++) {
			if( openStr.contains(input[i]+"") )	openStack.push(new Pair(i, input[i]));
			else	{
				Pair open = openStack.pop();
				char[] fix = checkPri( open.ch, input[i], priority );
				input[open.idx] = fix[0];
				input[i] = fix[1];
			}
		}
		
		for (Character ch : input) {
			System.out.print(ch);
		}
		
	}

	private static char[] checkPri(char open, char ch, char[] priority) {
		char[] ret = new char[2];
		char close = ' ';
		switch (ch) {
		case '}':
			close = '{';
			break;
		case ']':
			close = '[';
			break;
		case '>':
			close = '<';
			break;
		case ')':
			close = '(';
			break;
		}

		int openIdx = 0;
		int closeIdx = 0;
		for (int i = 0; i < priority.length; i++) {
			if( priority[i] == open)	{
				openIdx = i;
				break;
			}
		}
		
		for (int i = 0; i < priority.length; i++) {
			if( priority[i] == close)	{
				closeIdx = i;
				break;
			}
		}

		if( openIdx < closeIdx )	{
			ret[0] = priority[openIdx];
		}
		else	ret[0] = priority[closeIdx];

		switch (ret[0]) {
		case '{':
			ret[1] = '}';
			break;
		case '[':
			ret[1] = ']';
			break;
		case '<':
			ret[1] = '>';
			break;
		case '(':
			ret[1] = ')';
			break;
		}

		return ret;
	}
}
