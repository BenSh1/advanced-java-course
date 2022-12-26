
import java.util.ArrayList;
import java.util.Iterator;


public class GenericPriorityQueue<T> {

	//final int NUM_OF_PRIORITIES = 10;
	private int maxPriority;
	private ArrayList<ArrayList<T>> queueOfPriority;
	
	public GenericPriorityQueue(int n)
	{
		this.maxPriority = n;
		this.queueOfPriority = new ArrayList<ArrayList<T>>(n);
		
		// initializing
        for (int i = 0; i < n; i++) {
        	this.queueOfPriority.add(new ArrayList<T>());
        }
		
	}
	
	
	public int getMaxPriority() {
		return maxPriority;
	}

	public void setMaxPriority(int maxPriority) {
		this.maxPriority = maxPriority;
	}

	public ArrayList<ArrayList<T>> getQueueOfPriority() {
		return queueOfPriority;
	}

	public void setQueueOfPriority(ArrayList<ArrayList<T>> queueOfPriority) {
		this.queueOfPriority = queueOfPriority;
	}

	public void add(T item , int priorityItem)
	{
		if(priorityItem > getMaxPriority())
		{
			getQueueOfPriority().get(getMaxPriority()).add(item);
		}
		else
		{
			getQueueOfPriority().get(priorityItem ).add(item);

		}
	}
	
	public T poll()
	{
		int i =0;
		while(getMaxPriority() > i && getQueueOfPriority().get(i).size() == 0)
		{
			i++;
		}

		if( i < getMaxPriority())
		{
			T head = getQueueOfPriority().get(i).get(0);
			getQueueOfPriority().get(i).remove(0);

			return head;
		}
		else
			return null;
	}
	
	private int findItemInArray(T anotherItem)
	{
		int index = -1;
		int i = 0;
		int j = 0;
		int equalFlag = 0;
		while(i < getMaxPriority() && equalFlag == 0)
		{
			j= 0;
			while(j < getQueueOfPriority().get(i).size() && equalFlag == 0)
			{
				if(anotherItem instanceof CustomerRequest)
				{
					/*if(((CustomerRequest) anotherItem).getId() == ((CustomerRequest)this.getQueueOfPriority().get(i).get(j)).getId()  
							&& ((CustomerRequest)this.getQueueOfPriority().get(i).get(j)).getDetailsRequest().equals( ((CustomerRequest)anotherItem).getDetailsRequest()) ) */
					if(((CustomerRequest)this.getQueueOfPriority().get(i).get(j)).equal(((CustomerRequest) anotherItem)))

					{
						equalFlag = 1;
						index = i;
					}
				}
				else
				{
					if(this.getQueueOfPriority().get(i).get(j).equals(anotherItem))
					{
						equalFlag = 1;
						index = i;
					}
				}
				

				j++;
			}
			i++;
		}
		
		return index;
	}
	
	
	public Boolean contains(T anotherItem)
	{
		/*
		int i = 0;
		int j;
		int equalFlag = 0; // 0 = not find equal item | 1= find equal item

		while(i < getMaxPriority() && equalFlag == 0)
		{
			if(getQueueOfPriority().get(i).size() > 0 )
			{
				j= 0;
				while(j < getQueueOfPriority().get(i).size() && equalFlag == 0)
				{
					if(this.getQueueOfPriority().get(i).get(j).equals(anotherItem))
					{
						equalFlag = 1;
					}
					j++;
				}
			}
			i++;
		}
	
		*/
		
		int index = findItemInArray(anotherItem);
		
		if(index == -1)
		{
			return false;
		}
		else
			return true;
		

		
		/*
		if(equalFlag == 1)
		{
			return true;
		}
		else
			return false;
		*/
		
		
		

	}
	
	public Boolean remove(T anotherItem)
	{
		/*
		int i = 0;
		int j;
		int equalFlag = 0; // 0 = not find equal item | 1= find equal item
		
		while(i < this.getMaxPriority() && equalFlag == 0)
		{
			if(getQueueOfPriority().get(i).size() > 0 )
			{
				j= 0;
				while(j < getQueueOfPriority().get(i).size() && equalFlag == 0)
				{
					if(getQueueOfPriority().get(i).get(j).equals(anotherItem))
					{
						equalFlag = 1;
						getQueueOfPriority().get(i).remove(j);
					}
					j++;
				}
			}
			i++;
		}
		*/
		int i;
		int index = findItemInArray(anotherItem);
		int equalFlag = 0;
		if(index == -1)
		{
			return false;
		}
		else
		{
			i = 0;
			while(i< getQueueOfPriority().get(index).size() && equalFlag == 0)
			{
				
				if(anotherItem instanceof CustomerRequest)
				{
					/*if(((CustomerRequest) anotherItem).getId() == ((CustomerRequest)this.getQueueOfPriority().get(index).get(i)).getId()  
							&& ((CustomerRequest)this.getQueueOfPriority().get(index).get(i)).getDetailsRequest().equals( ((CustomerRequest)anotherItem).getDetailsRequest()) ) */
					if(((CustomerRequest)this.getQueueOfPriority().get(index).get(i)).equal(((CustomerRequest) anotherItem)))
					{
						equalFlag = 1;
					}
					else
						i++;
				}
				else
				{
					if(this.getQueueOfPriority().get(index).get(i).equals(anotherItem))
					{
						equalFlag = 1;
					}
					else
						i++;
				}
				
			}
			getQueueOfPriority().get(index).remove(i);
			return true;
		}
		
		/*
		if(equalFlag == 1)
		{
			return true;
		}
		else
			return false;
		*/
	}
	
	public int size()
	{
		int sum = 0;
		int i = 0;
		while(i < getMaxPriority() )
		{
			sum += getQueueOfPriority().get(i).size();
			i++;
		}
		
		return sum;
	}
	/*public Iterator iterator()
	{
		return getQueueOfPriority().iterator();
	}*/
	
	
	@SuppressWarnings("unchecked")
	public Iterator<T> iterator()
	{
		return (Iterator<T>) getQueueOfPriority().iterator();
	}
	

	@Override
	public String toString() {
		return "maxPriority=" + this.getMaxPriority() + ", queueOfPriority=" + this.getQueueOfPriority();
	}
	
	
	
}
