import java.util.ArrayList;
import java.util.Random;

public class Bank {

	public static void main(String[] args) {
		final int MAX_MONEY_PER_OPERATION = 1000;
		final int NUMBER_OF_ACCOUNTS = 5;
		final int NUMBER_OF_TRANSACTIONS = 10;
		final int NUM_OF_TELLERS = 10;

		BankAccount accounts[] = new BankAccount[NUMBER_OF_ACCOUNTS];
		
		for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
			accounts[i] = new BankAccount(i, 0);
		}

		Random rand = new Random();


		ArrayList<Transactions>trans = new ArrayList<Transactions>();
		
		for (int i = 0; i < NUMBER_OF_TRANSACTIONS; i++) {
			int accountNum = rand.nextInt(5);
			int signRandom = rand.nextInt(2);// 0 = negative | 1 = positive
			
			if (signRandom == 1)
				signRandom = 1;
			else
				signRandom = -1;

			int numberForTrans = signRandom * rand.nextInt(MAX_MONEY_PER_OPERATION + 1);
						
			trans.add(new Transactions(accountNum, numberForTrans));
		}
		
		System.out.println("Transactions (number of account , transaction amount) :");
		for (int i = 0; i < NUMBER_OF_TRANSACTIONS; i++) {
			System.out.print( "("+ trans.get(i).getBankAccount() +" , " + trans.get(i).getOperationNum() + ")");
		}
		System.out.println("\n---------------------------start------------------------------");
		
		BankTransactionData bankTransactionData = new BankTransactionData(trans);
		
		BankTeller tellers[] = new BankTeller[NUM_OF_TELLERS];

		for (int i = 0; i < NUM_OF_TELLERS; i++) {
			tellers[i] = new BankTeller(accounts, bankTransactionData);
		}
		
		for (int i = 0; i < NUM_OF_TELLERS; i++) {
			tellers[i].start();
		}
		
        System.out.println("DONE!");

	}

}
