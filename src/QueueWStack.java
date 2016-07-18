import java.util.Stack;

public class QueueWStack<T> {

	private Stack<T> stackNewest;
	private Stack<T> stackOldest;

	public QueueWStack()	{
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();
	}
	
	public void add(T item) {
		stackNewest.push( item );
	}

	public T peek() {
		shiftStacks();
		return stackOldest.peek();
	}

	private void shiftStacks() {
		if( stackOldest.isEmpty() )	{
			while( !stackNewest.isEmpty() )	{
				stackOldest.push( stackNewest.pop() );
			}
		}
	}

	public T pop() {
		shiftStacks();
		return stackOldest.pop();
	}

}
