
public class StackData {

	int start;	//	???
	int capacity;
	int size = 0;
	int pointer;

	public StackData(int start, int capacity) {
		this.start = start;
		this.capacity = capacity;
		this.pointer = start - 1;
	}

	public boolean isWithinStack(int idx, int totalSize) {
		if( start + capacity <= totalSize )	{	//	within case
			if( start <= idx && idx <= start+capacity )	return true;
			else	return false;
		}
		else	{	//	over case => move to first of array, because of circular array
			int shiftedIdx = totalSize + idx;
			if( start <= shiftedIdx && shiftedIdx <= start+capacity )	return true;
			else	return false;
		}
	}

}
