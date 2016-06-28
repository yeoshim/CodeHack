import java.util.Stack;

public class StackWmin {

	private class NodeWmin	{
		private int item;
		private NodeWmin next;
		private int min;

		public NodeWmin(int item, int newMin) {
			this.item = item;
			this.next = null;
			this.min = newMin;
		}
	}

	private NodeWmin top;

	public void push(int item) {
		int newMin = Math.min( item, min() );
		NodeWmin node = new NodeWmin( item, newMin );
		node.next = top;
		top = node;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public int pop() throws Exception {
		if( isEmpty() )	throw new Exception( "Null stack." );
		
		int item = top.item;
		top = top.next;
		return item;
	}

	public int min() {
		if( isEmpty() )	return  99999999;	//	for test...
		return top.min;
	}

	///////////////////////////////
	//	for sol2
	private class Node	{
		private int item;
		private Node next;

		public Node(int item) {
			this.item = item;
			this.next = null;
		}
	}
	
	private Stack<Integer> minStack = new Stack<Integer>();
	private Node top2;
	
	public void push2(int item) {
		if( item <= min2() )	{
			minStack.add( item );
		}
		
		Node node = new Node( item );
		node.next = top2;
		top2 = node;
	}
	
	public int pop2() throws Exception {
		if( isEmpty2() )	throw new Exception( "Null stack." );
		
		int item = top2.item;
		top2 = top2.next;
		int value = item;
		
		if( value == min2() )	minStack.pop();
		
		return value;
	}

	private boolean isEmpty2() {
		return top2 == null;
	}

	public int min2() {
		if( minStack.isEmpty() )	return 999999;
		else	return minStack.peek();
	}

}
