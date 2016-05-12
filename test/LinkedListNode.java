
public class LinkedListNode {

	char data;
	LinkedListNode next;

	public LinkedListNode(char c) {
		this.data = c;
		this.next = null;
	}

	public LinkedListNode() {
		this.next = null;
	}

	public void add(char c) {
		LinkedListNode newNode = new LinkedListNode( c );
		LinkedListNode cur = this;
		
		while( cur.next != null )	{
			cur = cur.next;
		}
		
		cur.next = newNode;
	}
}
