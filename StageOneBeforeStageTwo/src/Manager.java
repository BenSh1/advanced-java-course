
public class Manager {
	private int numOfThreads;
	private int countStageOne;
	private int countStageTwo;

	public Manager(int n) {
		// TODO Auto-generated constructor stub
		numOfThreads = n;
		countStageOne = 0;
		countStageTwo = 0;
	}
	
	public synchronized void stage1(int id) {
		System.out.println("Thread " + id + " is Working in stage1");
		countStageOne++;
		while(countStageOne != numOfThreads)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
	}
	
	
	public synchronized void stage2(int id) {

		while(countStageTwo != id)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		notifyAll();
		countStageTwo++;
		System.out.println("Thread " + id + " is Working in stage2");

	}

}
