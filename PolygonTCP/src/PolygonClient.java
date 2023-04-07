import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.scene.canvas.GraphicsContext;

public class PolygonClient extends Thread{
	
	private PolygonController cont;
	private String ip;
	private int numOfDots;

	public PolygonClient(PolygonController cont , String ip , int n) {
		this.cont = cont;
		this.ip = ip;
		this.numOfDots = n;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			handleReadAndWrite();
		}catch(Exception e) {
			System.out.println("can't read and write to server");
			e.printStackTrace();
			}
	}

	private void handleReadAndWrite() throws Exception {
		// TODO Auto-generated method stub
		Socket s = new Socket(ip,7777);
		OutputStream outputStream = s.getOutputStream();
		InputStream inputStream = s.getInputStream();
		ObjectInputStream objInputStream = new ObjectInputStream(inputStream);

		//Write the num to Server
		outputStream.write(numOfDots);

		//Get Polygon from server
		Polygon p = (Polygon)objInputStream.readObject();
		cont.addPolygon(p);
		
		objInputStream.close();
		inputStream.close();
		outputStream.close();
		s.close();
	}
}
