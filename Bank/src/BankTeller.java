
public class BankTeller extends Thread{

	private BankTransactionData myTransactionData;
	private BankAccount[] bankAccounts;

	public BankTeller(BankAccount[] otherBankAccounts ,BankTransactionData otherTransactionData)
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
			if(t != null)
			{
				this.bankAccounts[t.getBankAccount()].transaction(t.getOperationNum());
			}
			
			try {
				sleep((int)Math.random()*100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}

		
		
	}
	
	
	
}
