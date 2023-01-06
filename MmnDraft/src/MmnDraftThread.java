import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MmnDraftThread extends Thread {
	private MmnDraftController cont;
	private String ip;
	private Game game;
	
	
	
	public MmnDraftThread(MmnDraftController cont, String ip, Game game) {
		super();
		this.cont = cont;
		this.ip = ip;
		this.game = game;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			handleReadAndWrite();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleReadAndWrite() throws Exception {
		// TODO Auto-generated method stub
		Socket s = new Socket(ip , 7777);
		
		OutputStream outputStream = s.getOutputStream();
		ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
		
		InputStream inputStream = s.getInputStream();
		ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
		
		//write the data to server
		objOutputStream.writeObject(game);
		
		Game g;
		g = (Game)objInputStream.readObject();
		cont.setCardsBlank(game.getWasGuessed());
		
		
		//close all connections
		objInputStream.close();
		inputStream.close();
		objOutputStream.close();
		outputStream.close();
		s.close();

		
		
	}
}
