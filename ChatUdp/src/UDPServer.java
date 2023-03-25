import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	public UDPServer() {
		// TODO Auto-generated constructor stub
		while(true)
		{
			try {
				sendAndRecieve();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void sendAndRecieve() {
		// TODO Auto-generated method stub
		DatagramSocket serverSocket = null;
		try {
			serverSocket = new DatagramSocket(9876);
			byte[] receive = new byte[1024];
			DatagramPacket packet = new DatagramPacket(receive , receive.length);
			serverSocket.receive(packet);
			String msg = new String(packet.getData()).substring(0,packet.getLength());
			msg = msg.toUpperCase();
			//send to client
			int p = packet.getPort();
			InetAddress add = packet.getAddress();
			packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length,add, p);
			serverSocket.send(packet);
		
		
		} catch (IOException e) {
			// TODO: handle exception

		}
		finally {
			serverSocket.close();
		}
	}
	
	public static void main(String[] args) {
		new UDPServer();
	}
}
