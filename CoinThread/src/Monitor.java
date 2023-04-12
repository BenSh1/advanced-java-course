
public class Monitor {

	private int print = 0;
	private int max;
	private int count = 0;
	private int numOfHead = 0;;
	private int numOfTail = 0;
	
	public Monitor(int numberOfThread) {
		// TODO Auto-generated constructor stub
		max = numberOfThread;
	}
	
	public synchronized void waitForMyTurn(int id) {
		while(id != print)
		{
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public synchronized void goNext() {
		print++;
		count++;
		notifyAll();
	}
	public void waitForAll() {
		while(max != count)
		{
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public synchronized void sumHeadsAndTails(int numOfHead , int numOfTail) {
		this.numOfHead += numOfHead;
		this.numOfTail += numOfTail;
	}

	public int getNumOfHead() {
		return numOfHead;
	}

	public int getNumOfTail() {
		return numOfTail;
	}

	
}
