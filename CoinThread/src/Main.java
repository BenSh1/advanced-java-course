
public class Main {

	public static void main(String[] args) {
		final int NUM_OF_WORKERS = 10;

		Monitor m = new Monitor(NUM_OF_WORKERS);
		CoinThread[] worker = new CoinThread[NUM_OF_WORKERS];
		
		for (int i = 0; i < NUM_OF_WORKERS; i++) {
			worker[i] = new CoinThread(i , m);
		}

		for (int i = 0; i < NUM_OF_WORKERS; i++) {
			worker[i].start();
		}
		/*
		for (int i = 0; i < NUM_OF_WORKERS; i++) {
			try {
				worker[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		m.waitForAll();
		System.out.println("heads= " + m.getNumOfHead() + " tails= " + m.getNumOfTail());


		
		System.out.println("MAIN DONE");
		
	}
}
