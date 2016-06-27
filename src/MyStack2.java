
public class MyStack2<T> {

	private int[] stackPtr = {0, 0, 0};
	private int stackSize = 100;
	@SuppressWarnings("unchecked")
	private T[] buffer = (T[]) new Object[stackSize*3];

	public void push(int stackNum, T item) throws Exception {
		if( stackPtr[stackNum] >= stackSize  )	{
			throw new Exception( "Out of space" );
		}
		
		int idx = stackNum * stackSize + stackPtr[stackNum]+1;
		stackPtr[stackNum]++;
		buffer [idx] = item;
	}

	@SuppressWarnings("unchecked")
	public T pop(int stackNum) throws Exception {
		if( stackPtr[stackNum] == 0 )	{
			throw new Exception( "Trying to pop an empty stack." );
		}
		
		int idx = stackNum * stackSize + stackPtr[stackNum];
		stackPtr[stackNum]--;
		T item = buffer[idx];
		buffer[idx] = (T) new Integer(0);	//	for test
		
		return item;
	}
}
