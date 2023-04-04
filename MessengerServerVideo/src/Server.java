import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.layout.VBox;

public class Server {
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	public Server(ServerSocket serverSocket) {
		// TODO Auto-generated constructor stub
		this.serverSocket = serverSocket;
		
		System.out.println("server ready");
		try {
			socket = serverSocket.accept();
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 

		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error creating server.");
			e.printStackTrace();
			closeEverthing(socket, bufferedReader, bufferedWriter);
			System.exit(1);
		}
	}
	
	public void sendMessageToClient(String messageToClient) {
		try {
			bufferedWriter.write(messageToClient);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error sending message to client");
			closeEverthing(socket, bufferedReader, bufferedWriter);
		}
		
	}
	
	public void receiveMessageFromClient(VBox vBox) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(socket.isConnected())
				{
					String messageFromClient;
					try {
						messageFromClient = bufferedReader.readLine();
						Controller.addLabel(messageFromClient, vBox);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error receiving message from the client");
						closeEverthing(socket, bufferedReader, bufferedWriter);
						break;
					}
				}
			}
		}).start();
	}
	
	
	public void closeEverthing(Socket socket, BufferedReader bufferedReader
			, BufferedWriter bufferedWriter) {
		
		try {
			if(bufferedWriter != null)
				bufferedWriter.close();
			if(bufferedReader != null)
				bufferedReader.close();
			if(socket != null)
				socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}






}
