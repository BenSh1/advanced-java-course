
public class MainData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data d1 = new Data(0, 0);
		
		UpdateOperator updateOp = new UpdateOperator(d1);
		DifferenceOperator differenceOp = new DifferenceOperator(d1);
		
		Thread t1 = new Thread(updateOp);
		Thread t2 = new Thread(differenceOp);
		
		t1.start();
		t2.start();

		System.out.println("DONE");
		//-------------------------------------------------------
		//for section C
		final int NUMBER_OF_UPDATE_OPERATOR = 5;
		final int NUMBER_OF_DIFFERENCE_OPERATOR = 5;
		
		Thread t[] = new Thread[NUMBER_OF_UPDATE_OPERATOR + NUMBER_OF_DIFFERENCE_OPERATOR ];

		for (int i = 0; i < NUMBER_OF_UPDATE_OPERATOR; i++) {
			t[i] = new Thread(updateOp);
			
		}
		
		for (int i = NUMBER_OF_UPDATE_OPERATOR; i < NUMBER_OF_DIFFERENCE_OPERATOR + NUMBER_OF_UPDATE_OPERATOR; i++) {
			t[i] = new Thread(differenceOp);
			
		}
		


	}

}
