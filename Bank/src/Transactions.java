
public class Transactions {
	private int bankAccount;
	private int operationNum;
	
	public Transactions(int bankAccount ,int operationNum)
	{
		this.bankAccount = bankAccount;
		this.operationNum = operationNum;
	}

	public int getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}

	public int getOperationNum() {
		return operationNum;
	}

	public void setOperationNum(int operationNum) {
		this.operationNum = operationNum;
	}
	
	
	
	
}
