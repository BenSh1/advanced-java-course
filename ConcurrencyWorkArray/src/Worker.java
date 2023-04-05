
public class Worker extends Thread{
	private Manager m;
	
	public Worker( Manager m) {
		// TODO Auto-generated constructor stub
		this.m = m;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(m.getArr().length > m.getCount())
		{
			Result r = m.allocateNum();
			System.out.println(currentThread().getName() + " is doing is work on the index " + r.getIndex()
			+ " and is doing is work on the num " + r.getNum());
			if(r != null)
			{
				if(r.getNum() % 2 == 0)
				{
					r.setNum(r.getNum() *2);
				}
				else
				{
					r.setNum(r.getNum() /2);
				}
				m.putResult(r.getNum(), r.getIndex());
			}
		
		}
		
	}
}
