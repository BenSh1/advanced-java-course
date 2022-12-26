

//for section A
/*
public class Data{
	private int x = 0;
	private int y = 0;
	public Data (int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getDiff(){
		System.out.println("The difference is: " + (Math.abs(x-y)));
		return (Math.abs(x-y));
		
	}
	public void update(int dx, int dy){
		x = x + dx;
		y = y + dy;
		System.out.println("Update DATA " + "( " + getX() + " , " + getY() + " )");

	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
*/


//--------------------------------------------------------------

//for section B
/*
public class Data{
	private int x = 0;
	private int y = 0;
	private int counterDiff = 0;
	private int counterUpdate = 0;

	public Data (int x, int y){
		this.x = x;
		this.y = y;
	}
	public synchronized int getDiff(){
		
		while(counterUpdate <= counterDiff )
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The difference is: " + (Math.abs(x-y)));
		counterDiff++;
		return (Math.abs(x-y));
	}
	public synchronized void update(int dx, int dy){
		x = x + dx;
		y = y + dy;
		System.out.println("Update DATA " + "( " + getX() + " , " + getY() + " )");
		counterUpdate++;
		notifyAll();
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
*/

//--------------------------------------------------------------

//for section C
public class Data{
	private int x = 0;
	private int y = 0;
	public Data (int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getDiff(){
		System.out.println("The difference is: " + (Math.abs(x-y)));
		return (Math.abs(x-y));
		
	}
	public synchronized void update(int dx, int dy){
		x = x + dx;
		y = y + dy;
		System.out.println("Update DATA " + "( " + getX() + " , " + getY() + " )");

	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}

