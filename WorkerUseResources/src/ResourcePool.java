import java.util.ArrayList;
import java.util.Iterator;

public class ResourcePool {
	private final int NUM_OF_WORKER = 10;
	private ArrayList<Resource> list;
	private int[] numOfAllocate;
	private int max;
	private int nextTurn = 0;

	public ResourcePool(int n) {
		// TODO Auto-generated constructor stub
		list = new ArrayList<Resource>();
		for (int i = 0; i < n; i++) {
			Resource r = new Resource((i+1));
			list.add(r);
		}
		numOfAllocate = new int[n];
		for (int i = 0; i < n; i++) {
			numOfAllocate[i] = 0;
		}
		max = NUM_OF_WORKER;
	}
	
	public synchronized Resource getResource() {
		while(list.size() == 0)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Resource r = list.get(0);
		numOfAllocate[(r.getId()-1)]++;
		list.remove(0);
		return r;
	}
	
	public synchronized void returnResource(Resource r)
	{
		list.add(r);
		notifyAll();
	}
	
	public int getUseCount(int id)
	{
		return numOfAllocate[id];
	}

	
}
