
public class CoinThread extends Thread{

	private int id;
	private Monitor m;
	public CoinThread(int id , Monitor m) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.m =m;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		int head = 0;
		int tail = 0;
		for (int i = 0; i < 100; i++) {
			if(Math.random() < 0.5)
				head++;
			else
				tail++;
		}
		m.waitForMyTurn(id);
		System.out.println("theard " + id + " heads= " + head + " tails= " + tail);
		m.goNext();
		m.sumHeadsAndTails(head, tail);
	}
	
	
	
}
