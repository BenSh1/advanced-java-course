import java.io.Serializable;

public class Data implements Serializable{
	private int num;
	private String binary;
	public Data(int num, String binary) {
		super();
		this.num = num;
		this.binary = binary;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBinaryNum() {
		return binary;
	}
	public void setBinaryNum(String binary) {
		this.binary = binary;
	}
	
	
	
}
