
public class LLNode {

	private int value = -1;
	private LLNode next = null;

	public LLNode(int value) {
		this.value = value;
	}


	public LLNode() {
		// TODO Auto-generated constructor stub
	}


	public void appendToTail(int value) {
		LLNode end = new LLNode(value);
		this.next = end;
	}

	public LLNode get(int idx) {
		LLNode node = this;
		int cnt = 0;
		while( this.next != null && cnt < idx )	{
			node = node.next;
			cnt++;
		}

		if( cnt < idx )	return new LLNode(-1);	//	null
		
		return node;
	}

	public int value() {
		return this.value;
	}

	public LLNode delNode(LLNode head, int value) {
		LLNode node = head;
		
		if( node.value == value )	{
			//	node가 1개인 경우
			//	head가 변경
			return node.next;
		}
		
		while( node.next != null )	{
			if( node.next.value == value )	{
				node.next = node.next.next;
				//	head의 변경은 없음, 중간노드가 삭제된 것이므로
				return head;
			}
			node = node.next;
		}
		
		return head;
	}

}
