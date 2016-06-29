import java.util.ArrayList;
import java.util.Stack;

public class SetOfStacks {
	private static final int STACK_SIZE = 3;
	
	private ArrayList<Stack<Integer>> stackList = new ArrayList<Stack<Integer>>();
//	private Stack<Integer> curStack;

	public void push(int item) throws Exception {
		Stack<Integer> curStack = getLastStack();
		if( curStack != null && !IsFull(curStack) )	{
			curStack.push( item );
		}
		else	{
			curStack = new Stack<Integer>();
			curStack.push(item);
			stackList.add( curStack );
		}
	}

	private boolean IsFull(Stack<Integer> curStack) {
		return curStack != null && curStack.size() == STACK_SIZE;
	}

	private Stack<Integer> getLastStack()	{
		if( stackList.size() == 0 )	return null;
		return stackList.get( stackList.size()-1 );
	}

	public boolean isEmpty() {
		Stack<Integer> curStack = getLastStack();
		return curStack == null || curStack.isEmpty();
	}

	public int pop() throws Exception {
		Stack<Integer> curStack = getLastStack();
		if( curStack == null )	throw new Exception("Access Empty Stack!!!");
		int item = curStack.pop();
		if( curStack.size() == 0 )	{
			stackList.remove( stackList.size()-1 );
		}
		
		return item;
	}
	
	

}
