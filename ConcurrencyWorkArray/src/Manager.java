
public class Manager {
	private int[] arr;
	private int count;
	private int numOfItems;
	public Manager(int[] arr) {
		// TODO Auto-generated constructor stub
		this.arr = arr;
		numOfItems = arr.length;
	}
	public synchronized Result allocateNum() {
		if(count == arr.length)
			return null;
		int i = count;
		int num = arr[count];
		count++;
		Result r = new Result(num, i);
		return r;
	}
	
	public synchronized void putResult(int num , int index) {
		arr[index] = num;
		numOfItems--;
		notifyAll();
	}
	
	public synchronized int[] getResults() {
		while(numOfItems > 0)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arr;
	}
	public int[] getArr() {
		return arr;
	}
	public void setArr(int[] arr) {
		this.arr = arr;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNumOfItems() {
		return numOfItems;
	}
	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}
	
	
}
