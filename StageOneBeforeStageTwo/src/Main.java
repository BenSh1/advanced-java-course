
public class Main {
	public static void main(String[] args) {
		final int SIZE = 10;
		Manager m = new Manager(SIZE);
		
		Worker[] workers = new Worker[SIZE];
		
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new Worker(m,i);
		}
		for (int i = 0; i < workers.length; i++) {
			workers[i].start();
		}
		
		for (int i = 0; i < workers.length; i++) {
			try {
				workers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("done!");
		
	}
}
