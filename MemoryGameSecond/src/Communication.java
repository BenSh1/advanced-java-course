import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.application.Platform;

public class Communication {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public Communication(String serverName, int port) throws IOException, UnknownHostException, ConnectException {
		this.socket = new Socket(serverName, port);
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.in = new ObjectInputStream(socket.getInputStream());
	}

	public Object readObject() {
		Object input = null;
		try {
			input = in.readObject();
		} catch (IOException e) {
			System.out.println("Could not get input..");
			Platform.exit();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not get input..");
			Platform.exit();
			System.exit(1);
		} catch (NullPointerException ex) {
			System.out.println("Could not get input..");
			Platform.exit();
			System.exit(1);
		}
		return input;
	}

	public void writeObject(Object obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e1) {
			System.out.println("Could not send turn info..");
			Platform.exit();
			System.exit(1);
		}
	}

	public void disconnect() {
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			Platform.exit();
			System.exit(1);
		}
	}
}
