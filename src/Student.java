
public class Student {

	private int id;

	public Student(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getId() == ((Student)obj).getId();
	}
	
	public int getId() {
		return id;
	}

//	public void setId(int id) {
//		this.id = id;
//	}

}
