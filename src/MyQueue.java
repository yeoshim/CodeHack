
public class MyQueue {
	MyNode first, last;
	
	public void enqueue(Object item) {
		if( first == null )	{
			last = new MyNode(item);
			first = last;
		}
		else	{
			last.next = new MyNode(item);
			last = last.next;			
		}
	}
	
	class MyNode	{
		MyNode next;
		Object data;

		public MyNode(Object item) {
			this.data = item;
		}
		
	}

	public Object dequeue() {
		if( first != null )	{
			Object item = first.data;
			first = first.next; 
			return item;
		}
		
		return null;
	}

}
