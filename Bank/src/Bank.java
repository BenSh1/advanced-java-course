import java.util.ArrayList;
import java.util.Random;

public class Bank {

	public static void main(String[] args) {
		/*
		BankAccount a1 = new BankAccount(0, 0);
		BankAccount a2 = new BankAccount(1, 0);
		BankAccount a3 = new BankAccount(2, 0);
		BankAccount a4 = new BankAccount(3, 0);
		BankAccount a5 = new BankAccount(4, 0);*/
		
		final int NUMBER_OF_ACCOUNTS = 5;
		BankAccount accounts[] = new BankAccount[NUMBER_OF_ACCOUNTS];
		
		for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
			accounts[i] = new BankAccount(i, 0);
		}
		
		//------------------------
		/*
		Transactions b1 = new Transactions(0,200);
		Transactions b2 = new Transactions(0,200);
		Transactions b3 = new Transactions(0,100);
		Transactions b4 = new Transactions(0,50);
		Transactions b5 = new Transactions(0,500);
		Transactions b6 = new Transactions(0,70);
		Transactions b7 = new Transactions(0,800);

		ArrayList<Transactions> b = new ArrayList<Transactions>();
		b.add(b1);
		b.add(b2);
		b.add(b3);
		b.add(b4);
		b.add(b5);
		b.add(b6);
		b.add(b7);
		*/
		
		final int NUMBER_OF_TRANSACTIONS = 10;
		Random rand = new Random();

		//Transactions trans[] = new Transactions[NUMBER_OF_TRANSACTIONS];
		ArrayList<Transactions>trans = new ArrayList<Transactions>();
		
		for (int i = 0; i < NUMBER_OF_TRANSACTIONS; i++) {
			int accountNum = rand.nextInt(5);
			int signRandom = rand.nextInt(2);
			
			if (signRandom == 1)
				signRandom = 1;
			else
				signRandom = -1;

			int numberForTrans = signRandom * rand.nextInt(1001);
						
			trans.add(new Transactions(accountNum, numberForTrans));
		}
		
		
		//------------------------
		
		for (int i = 0; i < NUMBER_OF_TRANSACTIONS; i++) {
			System.out.println(trans.get(i).getOperationNum());
		}
		
		Transactions b1 = new Transactions(0,1000);
		Transactions b2 = new Transactions(1,1000);
		trans.add(b1);
		trans.add(b2);

		BankTransactionData c = new BankTransactionData(trans);
		
		
		//------------------------
		/*
		BankTeller d1 = new BankTeller(accounts[0] , c);
		BankTeller d2 = new BankTeller(accounts[1]  , c);
		BankTeller d3 = new BankTeller(accounts[2]  , c);*/
		
		final int NUM_OF_TELLERS = 10;
		BankTeller tellers[] = new BankTeller[NUM_OF_TELLERS];
		
		for (int i = 0; i < tellers.length - 5; i++) {
			int accountNum = rand.nextInt(5);
			tellers[i] = new BankTeller(accounts[accountNum], c);
		}
		
		//for assuring that there will be at least one Thread for every account (0-4)
		int j = 0;
		for (int i = 5; i < tellers.length; i++) {
			tellers[i] = new BankTeller(accounts[j], c);
			j++;
		}
		
		for (int i = 0; i < NUM_OF_TELLERS; i++) {
			tellers[i].start();
		}
		

/*
        try {
    		d1.join();
    		d2.join();
        } catch(InterruptedException e){}
*/
        System.out.println("All threads are done");

	}

}
