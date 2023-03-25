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
			while(true)
			{
				s = sc.accept();
				new ServerThread(s).start();
			}
			
		} catch (IOException e) {

		}
		finally {
			try {
				sc.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		new Server();
	}
	
	
}
