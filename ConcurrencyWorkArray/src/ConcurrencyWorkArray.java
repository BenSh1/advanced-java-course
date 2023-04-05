import java.util.Random;

public class ConcurrencyWorkArray {

	public static void main(String[] args) {
		Random r = new Random();
		
		int[] arr = new int[100];
		for(int i = 0 ;i < arr.length; i++)
		{
			arr[i] = r.nextInt(1000) + 1;
		}
		for(int i =0 ;i < arr.length; i++)
		{
			System.out.print(arr[i] + " , ");
		}
		System.out.println();

		Manager m = new Manager(arr);
		Worker[] worker = new Worker[10];
		
		for(int i =0 ;i < worker.length; i++)
		{
			worker[i] = new Worker(m);
		}
		
		for(int i =0 ;i < worker.length; i++)
		{
			worker[i].start();
		}
		
		for(int i =0 ;i < worker.length; i++)
		{
			try {
				worker[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int[] newArr = m.getResults();
		for(int i =0 ;i < newArr.length; i++)
		{
			System.out.print(newArr[i] + " , ");
		}
		
		System.out.println();
		System.out.println("DONE!");
	}
	

	
}
