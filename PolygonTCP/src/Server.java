import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
	
	public Server() {
		ServerSocket sc = null;
		Socket s = null;
		boolean listening = true;
		
		try {
			sc = new ServerSocket(7777);
			//handleReadAndWrite(sc);
		}catch (IOException e) {
			System.out.println("can't create server socket :(");
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("server ready!");

		while(listening)
		{
			try {
				s =  sc.accept();
				new ServerThread(s).start();
			} 
			catch (IOException e) {
				System.out.println("Can't accept socket :(");
				e.printStackTrace();
				System.exit(1);
			}
		}

		try {
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	private void handleReadAndWrite(ServerSocket sc) throws Exception{
		// TODO Auto-generated method stub
		Socket s = sc.accept();
		OutputStream outStream = s.getOutputStream();
		ObjectOutputStream objOutputStream = new ObjectOutputStream(outStream);
		InputStream inputStream = s.getInputStream();
		
		int num = inputStream.read();
		
		objOutputStream.writeObject(buildPolygon(num));
		
		
		inputStream.close();
		objOutputStream.close();
		outStream.close();
		s.close();
	}*/

	private Object buildPolygon(int num) {
		// TODO Auto-generated method stub
		Random r = new Random();
		Point arr[] = new Point[num];
		for (int i = 0; i < num; i++) {
			arr[i] = new Point(r.nextInt(400),r.nextInt(400));
		}
		return new Polygon(arr);
	}

	public static void main(String[] args) {
		new Server();
	}

}
