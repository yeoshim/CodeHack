
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

}
