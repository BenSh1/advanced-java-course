
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
		
		System.out.println("Account " + this.getAccountNumber() + " is trying to transaction with the number "
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
		System.out.println("The num of account is : "+this.getAccountNumber() 
		+ ", the transaction is : " + operationNum
		+ ", and the balance is : " + this.getBalance());
		
		notifyAll();
		/*
		if(operationNum > 0)
		{
			
		}*/

		/*
		else
		{
			this.balance += operationNum;

		}*/
		
		
		
		/*
		if(operationNum >= 0)
		{
			this.balance += operationNum;
			
		}
		else
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
			
			this.balance += operationNum;
		}
		*/
		
		//System.out.println("The new balance is : " + getBalance());
		
		/*
		if(operationNum > 0)
		{
			this.balance += operationNum;
		}
		else
		{
			this.balance -= operationNum;

		}*/
	}

	public int getBalance() {
		//notifyAll();
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
