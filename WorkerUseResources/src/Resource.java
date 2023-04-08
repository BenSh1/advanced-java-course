
public class Resource {
	private int id;
	public Resource(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	public void use()
	{
		System.out.println("The program doing the resource of " + id);
	}
	public int getId() {
		return id;
	}
}
