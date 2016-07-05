
public class Stack4SOS {

	private Node4SOS top;
	private Node4SOS bottom;
	
	private int size;
	private int capacity;

	public Stack4SOS(int capacity) {
		this.capacity = capacity;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int pop() {
		Node4SOS t = top;
		top = top.below;
		size--;
		
		return t.value;
	}

	private class Node4SOS	{
		public int value;
		public Node4SOS below;
		public Node4SOS above;

		public Node4SOS(int item) {
			this.value = item;
		}
	}

	public boolean push(int item) {
		if( size >= capacity )	return false;
		
		size++;
		Node4SOS n = new Node4SOS( item );
		if( size == 1 )	bottom = n;
		
		//	connecting above(n) and below(cur_top) for insert n to top of stack(top=n)
		join( n, top );
		top = n;
		
		return true;
	}

	private void join(Node4SOS above, Node4SOS below) {
		if( below != null )	below.above = above;
		if( above != null )	above.below = below;
	}

	public int removeBottom() {
		Node4SOS b = bottom;
		bottom = bottom.above;
		if( bottom != null )	bottom.below = null;
		size--;
		
		return b.value;
	}

	public boolean isFull() {
		return capacity == size;
	}
}
