import java.util.Random;

public class CustomerRequestMain {
	public static void main(String[] args)
	{

		final int NUMBER_OF_REQUEST = 5;
		Random rand = new Random();

		String[] names = {"Ben" , "Chen","Matan" , "May" , "Johny"};
		String[] detailsOfRequest = {"I want a phone call.", "When are you going to add [feature]?" 
								, "I want a refund!","When do you close?","When do you open?"};

		CustomerRequest requests[] = new CustomerRequest[NUMBER_OF_REQUEST];
		
		for (int i = 0; i < NUMBER_OF_REQUEST; i++) {
			//int randomLastDigitId = rand.nextInt(10);
			requests[i] = new CustomerRequest(names[i], 800800800 + i/*randomLastDigitId*/, detailsOfRequest[i]);
		}
		
		int maxOfPriority = rand.nextInt(10) + 1;
		GenericPriorityQueue<CustomerRequest> CustomerpriorQueue = new GenericPriorityQueue<CustomerRequest>(maxOfPriority);
		
		System.out.println("-----------------add-------------------");
		int levelOfPriority;
		for (int i = 0; i < requests.length ; i++) {
			levelOfPriority = rand.nextInt(maxOfPriority);
			CustomerpriorQueue.add(requests[i] ,levelOfPriority );
		}
		
		System.out.println(CustomerpriorQueue.toString());
		
		System.out.println("-----------------poll-------------------");
		CustomerRequest headCustomer = CustomerpriorQueue.poll();
		System.out.println("The Customer head : \""+ headCustomer.toString()+"\" is deleted");

		System.out.println(CustomerpriorQueue.toString());
		
		System.out.println("-----------------contains-------------------");
		CustomerRequest Kayle = new CustomerRequest("Kayle" , 123456789 , "Checking if it's working");
		CustomerRequest Lara = new CustomerRequest("Lara" , 800800801 , "When are you going to add [feature]?");

		System.out.println("Is the customer \"Kayle\" in the Queue? " + CustomerpriorQueue.contains(Kayle));
		System.out.println("Is the customer \""+ requests[0].getName() +"\" in the Queue? " + CustomerpriorQueue.contains(requests[0]));
		System.out.println("Is the customer \"Lara\" in the Queue? " + CustomerpriorQueue.contains(Lara));

		System.out.println("-----------------remove-------------------");
		System.out.println("Do the customer \"Kayle\" is removed? " +CustomerpriorQueue.remove(Kayle));
		System.out.println("Do the customer \""+ requests[0].getName() +"\" is removed? " + CustomerpriorQueue.remove(requests[0]));
		System.out.println("Do the customer \"Lara\" is removed? " +CustomerpriorQueue.remove(Lara));

		System.out.println(CustomerpriorQueue.toString());
		
		


	}

}
