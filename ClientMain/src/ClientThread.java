import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread extends Thread {
	private ClientController cont;
	private String ip;
	private Data num;
	public ClientThread(ClientController cont, String ip, Data num) {
		super();
		this.cont = cont;
		this.ip = ip;
		this.num = num;
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
		objOutputStream.writeObject(num);

		Data n;
		n = (Data)objInputStream.readObject();
		cont.setBinaryNum(n.getBinaryNum());

		
		//close all connections
		objInputStream.close();
		inputStream.close();
		objOutputStream.close();
		outputStream.close();
		s.close();

		
		
	}
}
