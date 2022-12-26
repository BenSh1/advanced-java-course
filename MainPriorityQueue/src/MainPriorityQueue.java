import java.util.Iterator;
import java.util.Random;

public class MainPriorityQueue {
	public static void main(String[] args)
	{
		Random rand = new Random();
		String head;
		String arr[] = {"Hey" , "Priority" , "Queue" , "Array" , "Java" , "more than one word" ,
				"Polymorphism" ,"Inheritance" , "Advanced-Java" , "List" , "Eclipse" };
		
		int maxOfPriority = rand.nextInt(10) + 1;
		GenericPriorityQueue<String> priorQueue = new GenericPriorityQueue<String>(maxOfPriority);
		
		System.out.println("-----------------add-------------------");
		int levelOfPriority;
		for (int i = 0; i < arr.length ; i++) {
			levelOfPriority = rand.nextInt(maxOfPriority);
			priorQueue.add(arr[i] ,levelOfPriority );
		}

		System.out.println(priorQueue.toString());
		
		System.out.println("-----------------poll-------------------");
		head = priorQueue.poll();
		System.out.println("The String head : \""+ head +"\" is deleted");
		System.out.println(priorQueue.toString());
		
		System.out.println("-----------------contains-------------------");

		System.out.println("Is the string \"Eclipse\" in the Queue? " + priorQueue.contains("Eclipse"));
		System.out.println("Is the string \"Hello\" in the Queue? " + priorQueue.contains("Hello"));
		System.out.println("Is the string \"C#\" in the Queue? " + priorQueue.contains("C#"));
		System.out.println("Is the string \"Inheritance\" in the Queue? " +priorQueue.contains("Inheritance"));

		System.out.println("-----------------remove-------------------");
		System.out.println("Do the string \"Eclipse\" is removed? " + priorQueue.remove("Eclipse"));
		System.out.println("Do the string \"Ben\" is removed? " + priorQueue.remove("Ben"));
		System.out.println("Do the string \"Eclipse\" is removed? " + priorQueue.remove("Eclipse"));

		System.out.println(priorQueue.toString());
		
		System.out.println("-----------------size-------------------");
		System.out.println("The size of priorityQueue is: "+priorQueue.size());
		
		System.out.println("-----------------iterator-------------------");
		Iterator<String> outIterator = priorQueue.iterator();
		Iterator<String> insideIterator;
		int i= 0;
		System.out.println("Iterator: ");
		while(outIterator.hasNext())
		{
			insideIterator = priorQueue.getQueueOfPriority().get(i).iterator();
			while(insideIterator.hasNext())
			{
				System.out.println("\t "+insideIterator.next());
			}
			i++;
			outIterator.next();
		}
	}
}
