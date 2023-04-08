import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final int NUM_OF_WORKERS = 10;
		final int NUM_OF_RESOURCE = 3;

		ResourcePool rPool = new ResourcePool(NUM_OF_RESOURCE);
		Worker[] worker = new Worker[NUM_OF_WORKERS];
		
		for (int i = 0; i < NUM_OF_WORKERS; i++) {
			worker[i] = new Worker(rPool);
		}
		
		Thread[] arrThread = new Thread[NUM_OF_WORKERS];
		
		for (int i = 0; i < NUM_OF_WORKERS; i++) {
			arrThread[i] = new Thread(worker[i]);
		}
		for (int i = 0; i < NUM_OF_WORKERS; i++) {
			arrThread[i].start();
		}
		
		for (int i = 0; i < NUM_OF_WORKERS; i++) {
			try {
				arrThread[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < NUM_OF_RESOURCE; i++) {
			System.out.println("The number of times that resource id "+ (i+1) + 
					" is allocate " + rPool.getUseCount(i));
			
		}
		
	}

}
