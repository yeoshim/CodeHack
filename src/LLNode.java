
public class LLNode<T> {

	private T value;
//	private LLNode<T> next = null;
	public LLNode<T> next = null;

	public LLNode(T value) {
		this.value = value;
	}

	public LLNode() {}


	public void appendToTail(T value) {
		LLNode<T> end = new LLNode<T>(value);
		this.next = end;
	}

	public LLNode<T> get(int idx) {
		LLNode<T> node = this;
		int cnt = 0;
		while( this.next != null && cnt < idx )	{
			node = node.next;
			cnt++;
		}

//		if( cnt < idx )	return new LLNode(-1);	//	null
		if( cnt < idx )	return new LLNode<T>();	//	null
		
		return node;
	}

	public T value() {
		return this.value;
	}

	public LLNode<T> delNode(LLNode<T> head, T value) {
		LLNode<T> node = head;
		
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
