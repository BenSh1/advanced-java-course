
public class HeavyThread extends Thread {

	private int num;
	private MyMonitor m;
	
	public HeavyThread(int n, MyMonitor m) {
		// TODO Auto-generated constructor stub
		this.num = n;
		this.m = m;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		m.waitForMyTurn(num);
		System.out.println("I'm doing something " + num + " ,and the name of the thread: " + currentThread().getName());
		m.imDone(num);
	}
}
