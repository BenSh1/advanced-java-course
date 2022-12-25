
public class BankTeller extends Thread{

	private BankTransactionData myTransactionData;
	private BankAccount bankAccounts;
	
	public BankTeller(BankAccount otherBankAccounts ,BankTransactionData otherTransactionData)
	{
		this.myTransactionData = otherTransactionData;
		this.bankAccounts = otherBankAccounts;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(this.myTransactionData.getTransactions().size() != 0)
		{
			
			Transactions t = this.myTransactionData.retrieveTransaction();
			this.bankAccounts.transaction(t.getOperationNum());
			
			/*System.out.println("The num of account is : "+this.bankAccounts.getAccountNumber() 
			+ ", the transaction is : " + t.getOperationNum()
			+ ", and the balance is : " + this.bankAccounts.getBalance());*/
			
			try {
				sleep((int)Math.random()*100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

		
		
	}
	
	
	
}
