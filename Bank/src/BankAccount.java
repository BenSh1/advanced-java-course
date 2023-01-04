
public class BankAccount {
	private int accountNumber;
	private int balance;

	public BankAccount(int accountNumber , int balance)
	{
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public synchronized void transaction(int operationNum)
	{
		
		System.out.println("Account " + this.getAccountNumber() + " is wanting to transaction with the number "
		+operationNum);
		if(operationNum < 0)
		{
			int oppositeNumber = (-1)*operationNum;
			while(this.balance < oppositeNumber )
			{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		this.balance += operationNum;
		System.out.println("finished transaction of Account "+this.getAccountNumber() + ": The transaction is " + operationNum
		+ "\n \t\t\t           The balance now is " + this.getBalance());
		
		notifyAll();

	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	

}
