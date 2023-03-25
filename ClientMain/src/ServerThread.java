import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket s = null;

	public ServerThread(Socket socket) {
		super();
		this.s = socket;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			handleReadAndWrite();
		}
		catch(Exception e) {}
	}
	
	public void handleReadAndWrite() throws Exception {
		// TODO Auto-generated method stub
		OutputStream outputStream = s.getOutputStream();
		ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
		InputStream inputStream = s.getInputStream();
		ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
		
		// get Data from client
		Data n;
		n = (Data)objInputStream.readObject();
		
		// process the number and send Data to client
		n.setBinaryNum(Integer.toBinaryString(n.getNum()));
		objOutputStream.writeObject(n);
		
		//close all connections
		objInputStream.close();
		inputStream.close();
		objOutputStream.close();
		outputStream.close();
		s.close();

		
		
	}
}
