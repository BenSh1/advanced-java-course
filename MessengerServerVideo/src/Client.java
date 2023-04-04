import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javafx.scene.layout.VBox;

public class Client {

	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	public Client(Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			this.socket = socket;
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error creating server.");
			e.printStackTrace();
			closeEverthing(socket, bufferedReader, bufferedWriter);
			System.exit(1);

		}

	}

	private void closeEverthing(Socket socket2, BufferedReader bufferedReader2, BufferedWriter bufferedWriter2) {
		// TODO Auto-generated method stub
		
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

	public void receiveMessageFromServer(VBox vBox) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(socket.isConnected())
				{
					String messageFromServer;
					try {
						messageFromServer = bufferedReader.readLine();
						MessengerClientVideoController.addLabael(messageFromServer, vBox);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Error receiving message from the Server");
						closeEverthing(socket, bufferedReader, bufferedWriter);
						break;
					}
					
					
				}
			}
		}).start();
	}

	public void sendMessageToServer(String messageToServer) {
		// TODO Auto-generated method stub
		try {
			bufferedWriter.write(messageToServer);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error sending message to client");
			closeEverthing(socket, bufferedReader, bufferedWriter);
		}
	}

}
