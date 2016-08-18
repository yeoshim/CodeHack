
public abstract class Animal {

	String name;
	private int order;

	public Animal( String name ) {
		this.name = name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isOlderThan(Animal animal) {
		return this.order < animal.order;
	}

}