import java.util.Stack;

public class Tower {

	private int index;
	private Stack<Integer> disks;

	public Tower(int i) {
		this.index = i;
		disks = new Stack<Integer>();
	}

	public void add(int disk) {
		if( !disks.isEmpty() && disks.peek() <= disk )	{
			System.out.println( "Error placing disk " + disk );
		}
		else	disks.push( disk );
	}

	public void moveDisks(int n, Tower dest, Tower buf) {
		if( n > 0 )	{
			moveDisks( n-1, buf, dest );	//	move disks(except last disk) to buf
			moveTopTo( dest );	//	last disk to dest
			buf.moveDisks( n-1, dest, this );	//	move buffed disks to dest
		}
	}

	private void moveTopTo(Tower t) {
		int top = disks.pop();
		t.add( top );
//		System.out.println( "Move disk " + top + " from " + index + " to " + t.index );
	}

	public void print() {
		System.out.println( "Tower#" + index );
		for (int disk : disks) {
			System.out.print( disk );
		}
		System.out.println( "" );		
	}

}
