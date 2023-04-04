
public class Worker extends Thread {

	private Manager m;
	private int id;
	public Worker(Manager m , int id) {
		// TODO Auto-generated constructor stub
		this.m = m;
		this.id = id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		/*System.out.println(currentThread().getName());
		System.out.println(currentThread().getId());*/

		m.stage1(id);
		m.stage2(id);
	}
}
