import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public Server()
	{
		ServerSocket sc = null;
		Socket s = null;
		try {
			sc = new ServerSocket(7777);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true)
		{
			try {
				s =sc.accept();
				new ServerThread(s).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					sc.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//e.printStackTrace();
			}
		}


		
	}
	
	public static void main(String[] args) 
	{
		new Server();
	}
	
}
