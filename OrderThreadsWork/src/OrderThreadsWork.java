import java.util.Iterator;

public class OrderThreadsWork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeavyThread[] heavyThreads = new HeavyThread[10];
		MyMonitor m = new MyMonitor();
		for (int i = 0; i < heavyThreads.length; i++) {
			heavyThreads[i] = new HeavyThread(i , m);
		}
		
		for (int i = 0; i < heavyThreads.length; i++) {
			heavyThreads[i].start();
		}
		
		for (int i = 0; i < heavyThreads.length; i++) {
			try {
				heavyThreads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("I'm Done");
	}

}
