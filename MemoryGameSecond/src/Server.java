import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Server {

	public static void main(String[] args) {
		final int PORT = 7777;
		ServerSocket serverSocket = null;
		Socket socketPlayer1 = null;
		Socket socketPlayer2 = null;
		boolean isListening = true;
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("can't create server socket :(");
			System.exit(1);
		}

		boolean isValidSize = false;
		int size = 1;
		while (!isValidSize) {
			try {
				size = Integer.parseInt(
						JOptionPane.showInputDialog("Please enter board size \n" + "It has to be or 4 or 6."));
			} catch (NumberFormatException e) {
				System.out.println("Input is not a number!");
			}
			if (size % 2 != 0)
				System.out.println("size has to be even!");
			else if (size > 6)
				System.out.println("size has to be 6 or less!");
			else if (size < 4)
				System.out.println("size has to be 4 or more!");
			else
				isValidSize = true;
		}
		System.out.println("Waiting for players...");
		while (isListening) {
			try {
				socketPlayer1 = serverSocket.accept();
				System.out.println("Player 1 is connected :)");
				socketPlayer2 = serverSocket.accept();
				System.out.println("Player 2 is connected :)");
				(new GameThread(socketPlayer1, socketPlayer2, size)).start();

			} catch (IOException e) {
				System.out.println("Can't accept sockets :(");
				System.exit(1);
			}
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Can't close server socket :(");
			System.exit(1);
		}
	}
}
