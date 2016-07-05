import java.util.ArrayList;

public class SetOfStacks {
	static final int STACK_SIZE = 3;
	
	private ArrayList<Stack4SOS> stackList = new ArrayList<Stack4SOS>();

	public void push(int item) throws Exception {
		Stack4SOS curStack = getLastStack();
		if( curStack != null && !curStack.isFull() )	{
			curStack.push( item );
		}
		else	{
			curStack = new Stack4SOS( STACK_SIZE );
			curStack.push(item);
			stackList.add( curStack );
		}
	}

	public int pop() throws Exception {
		Stack4SOS curStack = getLastStack();
		if( curStack == null )	throw new Exception("Access Empty Stack!!!");

		int item = curStack.pop();
		if( curStack.size() == 0 )	{
			stackList.remove( stackList.size()-1 );
		}
		
		return item;
	}
	
	private Stack4SOS getLastStack()	{
		if( stackList.size() == 0 )	return null;
		return stackList.get( stackList.size()-1 );
	}

	public boolean isEmpty() {
		Stack4SOS curStack = getLastStack();
		return curStack == null || curStack.isEmpty();
	}


	public int popAt(int idx) throws Exception {
		if( stackList.size() <= idx-1 )	throw new Exception( "Current Stacks size is: " + stackList.size() );
		return leftShift( idx-1, true );
	}

	private int leftShift(int idx, boolean removeTop) {
//		System.out.println( "stacks size: " + stackList.size() + " get idx: " + idx );
		Stack4SOS stack = stackList.get( idx );
		int removedItem = 0;
		
		if( removeTop )	removedItem = stack.pop();
		else	removedItem = stack.removeBottom();
		
		//	arrange stacks
		if( stack.isEmpty() )	{
			stackList.remove( idx );
		}
		else if( stackList.size() > idx+1 )	{
			int v = leftShift( idx+1, false );
			stack.push( v );
		}
		
		return removedItem;
	}

}
