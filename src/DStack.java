
public class DStack<T> {
	private static final int DEFAULT_SIZE = 4;
	private static final int NUM_STACK = 3;
	private static final int TOTAL_SIZE = DEFAULT_SIZE * NUM_STACK;
	private StackData[] stacks = {new StackData(0, DEFAULT_SIZE), 
		new StackData(DEFAULT_SIZE, DEFAULT_SIZE), 
		new StackData(DEFAULT_SIZE*2, DEFAULT_SIZE)
	};
	
	@SuppressWarnings("unchecked")
	private T[] buffer = (T[]) new Object[TOTAL_SIZE];

	public void push(int stackNum, T item) throws Exception {
		StackData stack = stacks[stackNum];
		
		if( stack.size >= stack.capacity )	{
			if( numberOfElements() >= TOTAL_SIZE )	throw new Exception( "Out of space." );
			else	expand( stackNum );
		}
		
		stack.size++;
		stack.pointer = nextElement( stack.pointer );
		buffer[stack.pointer] = item;
	}

	private int numberOfElements() {
		int sum = 0;
		for( StackData stack: stacks )	{
			sum += stack.size;
		}
		return sum;
	}

	//	use next stack capacity, so need to move next stack (right 1)
	private void expand(int stackNum) {
		shift( (stackNum+1) % NUM_STACK );	//	% for cycle
		stacks[stackNum].capacity++;
	}

	private void shift(int stackNum) {
		StackData stack = stacks[stackNum];
		//	why need???
//		if( stack.size >= stack.capacity )	{
//			int nextStack = (stackNum+1) % NUM_STACK;
//			shift( nextStack );	//	extend capacity (???)
//			stack.capacity++;
//		}
		
		//	move reverse (???)
		for( int i = (stack.start + stack.capacity - 1) % NUM_STACK;
				stack.isWithinStack(i, TOTAL_SIZE); i = previousElement(i) )	{
			buffer[i] = buffer[previousElement(i)];
		}
		
		buffer[stack.start] = (T) new Integer(0);	//	for test
		stack.start = nextElement( stack.start );
		stack.pointer = nextElement( stack.pointer );
		stack.capacity--;	//	???
	}

	//	for circular array
	private int nextElement(int idx) {
		if( idx+1 == TOTAL_SIZE )	return 0;
		else	return idx+1;
	}
	
	private int previousElement(int idx) {
//		if( idx == 0 )	return TOTAL_SIZE-1;
		if( idx-1 < 0 )	return TOTAL_SIZE-1;
		else	return idx-1;
	}

	public T pop(int stackNum) throws Exception {
		StackData stack = stacks[stackNum];
		if( stack.size == 0 )	{
			throw new Exception( "Trying to pop an empty stack" );
		}
		
		T item = buffer[stack.pointer];
		buffer[stack.pointer] = (T) new Integer(0);
		stack.pointer = previousElement( stack.pointer );
		stack.size--;
		
		return item;
	}

	public T[] getBuf() {
		return buffer;
	}


}
