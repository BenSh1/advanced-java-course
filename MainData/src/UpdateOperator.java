import java.util.Random;

public class UpdateOperator implements Runnable {
	Data d;
	
	public UpdateOperator(Data other)
	{
		this.d = other;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		final int NUMBER_OF_COUPLES = 10;
		Random rand = new Random();
		
		for (int i = 0; i < NUMBER_OF_COUPLES; i++) {
			int num1 = rand.nextInt(101);
			int num2 = rand.nextInt(101);

			d.update(num1, num2);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println("create DATA " + "( " + d.getX() + " , " + d.getY() + " )");

		}
		 
	}
	
}
