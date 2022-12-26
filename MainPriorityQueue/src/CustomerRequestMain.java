
public class CustomerRequestMain {
	public static void main(String[] args)
	{
		CustomerRequest ben = new CustomerRequest("Ben" , 3150 , "I want a phone call.");
		CustomerRequest may = new CustomerRequest("May" , 3156 , "When are you going to add [feature]?");
		CustomerRequest gil = new CustomerRequest("gil" , 3158 , "I want a refund!");
		CustomerRequest matan = new CustomerRequest("matan" , 3159 , "Tweet me back.");
		CustomerRequest johny = new CustomerRequest("johny" , 3158 , "When do you close?");
		CustomerRequest dummy = new CustomerRequest("dummy" , 3150 , "I want a phone call, now!");
		CustomerRequest dummy2 = new CustomerRequest("dummy2" , 3150 , "Check");

		GenericPriorityQueue<CustomerRequest> CustomerpriorQueue = new GenericPriorityQueue<CustomerRequest>(5);
		System.out.println("-----------------add-------------------");
		
		CustomerpriorQueue.add(ben, 2);
		CustomerpriorQueue.add(may, 2);
		CustomerpriorQueue.add(gil, 4);
		CustomerpriorQueue.add(matan, 1);
		CustomerpriorQueue.add(johny, 1);

		//CustomerpriorQueue.add(dummy, 5);

		System.out.println(CustomerpriorQueue.toString());
		
		System.out.println("-----------------poll-------------------");
		CustomerRequest headCustomer = CustomerpriorQueue.poll();
		System.out.println("head : " + headCustomer.toString());
		System.out.println(CustomerpriorQueue.toString());
		
		System.out.println("-----------------contains-------------------");
		System.out.println(CustomerpriorQueue.contains(ben));
		System.out.println(CustomerpriorQueue.contains(johny));
		System.out.println(CustomerpriorQueue.contains(dummy));
		System.out.println(CustomerpriorQueue.contains(may));
		System.out.println(CustomerpriorQueue.contains(dummy2));

		System.out.println("-----------------remove-------------------");
		System.out.println(CustomerpriorQueue.remove(gil));
		System.out.println(CustomerpriorQueue.remove(dummy));

		System.out.println(CustomerpriorQueue.toString());
		
		


	}

}
