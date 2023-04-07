import java.awt.Point;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;

public class ServerThread extends Thread{
	private Socket s = null;

	public ServerThread(Socket s) {
		// TODO Auto-generated constructor stub
		this.s = s;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			handleReadAndWrite();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	private void handleReadAndWrite() throws Exception{
		// TODO Auto-generated method stub
		OutputStream outputStream = s.getOutputStream();
		ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
		InputStream inputStream = s.getInputStream();
		//ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
		
		//Get num from Client
		int num = inputStream.read();
		
		//build the polygon and send to Client
		objOutputStream.writeObject(buildPolygon(num));
		
		inputStream.close();
		objOutputStream.close();
		outputStream.close();
		s.close();
		
	}

	private Polygon buildPolygon(int num) {
		// TODO Auto-generated method stub
		Random r = new Random();
		Point arr[] = new Point[num];
		for (int i = 0; i < num; i++) {
			arr[i] = new Point(r.nextInt(400),r.nextInt(400));
		}
		return new Polygon(arr);
	}
}
