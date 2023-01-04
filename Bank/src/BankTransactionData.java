import java.util.ArrayList;

public class BankTransactionData {
	private ArrayList<Transactions> transactions;
	
	public BankTransactionData(ArrayList<Transactions> transactions)
	{
		this.transactions = transactions;
	}
	
	public synchronized Transactions retrieveTransaction()
	{
		Transactions temp;
		
		//if(transactions.get(0) != null)
		if(transactions.size() != 0)
		{
			temp = transactions.get(0);
			transactions.remove(0);
			return temp;
		}
		else
			return null;
		
	}

	public ArrayList<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transactions> transactions) {
		this.transactions = transactions;
	}
	
	

}
