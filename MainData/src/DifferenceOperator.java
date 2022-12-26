
public class DifferenceOperator implements Runnable {
	Data d;
	
	public DifferenceOperator(Data other)
	{
		this.d = other;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		final int NUMBER_OF_COUPLES = 10;
		for (int i = 0; i < NUMBER_OF_COUPLES; i++) {
			//System.out.println("The difference is: " + d.getDiff());
			d.getDiff();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
