
public class MyStack {

	private MyNode top;

	public void push(Object item) {
		MyNode node = new MyNode( item );
		node.next = top;
		top = node;
	}

	public Object pop() {
		if( top.data != null )	{
			Object item = top.data;
			top = top.next;
			return item;
		}
		return null;
	}
	
	private class MyNode	{
		public MyNode(Object item) {
			this.data = item;
		}
		private MyNode next;
		private Object data;
	}

}
