import java.util.LinkedList;

public class AnimalQueue {

	private int order = 0;	//	timestamp
	private LinkedList<Dog> dogs = new LinkedList<Dog>();
	private LinkedList<Cat> cats = new LinkedList<Cat>();

	public void enqueue(Animal animal) {
		//	for ordering cat N dog
		animal.setOrder( order );
		order++;
		
		if( animal instanceof Dog )	dogs.addLast( (Dog)animal );
		else	cats.addLast( (Cat)animal );
	}

	public Animal dequeueAny() {
		
		Animal dog = dogs.peek();
		Cat cat = cats.peek();
		
		if( dog.isOlderThan(cat) )	return dogs.pop();
		else	return cats.pop();
		
	}

	public Dog dequeueDog() {
		return dogs.pop();
	}

	public Cat dequeueCat() {
		return cats.pop();
	}

}
