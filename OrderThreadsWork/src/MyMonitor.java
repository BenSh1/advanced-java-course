
public class MyMonitor {
	private int nextTurn = 2;
	
	public synchronized void waitForMyTurn(int threadNumber) {
		while(threadNumber >= nextTurn)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public synchronized void imDone(int n) {
		nextTurn++;
		notifyAll();
	}
	
	
}
