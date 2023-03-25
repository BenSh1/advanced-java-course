
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ClientController {

    @FXML
    private TextArea textA;

    @FXML
    private TextField textF;
    
    public void initialize(){
    	
    }
    @FXML
    void keyPressed(KeyEvent event) {
    	//System.out.println(event.getText());
    	if(event.getCode() == KeyCode.ENTER)
    	{
    		DatagramSocket clientSocket = null;
    		try {
				clientSocket = new DatagramSocket();
				InetAddress IPAddress = InetAddress.getByName("localhost");
				sendToServer(clientSocket ,IPAddress );
				getFromServer(clientSocket);
			} catch (Exception e) {
				// TODO: handle exception
			}
    		finally {
				clientSocket.close();
			}
    	}
    }
	private void sendToServer(DatagramSocket socket, InetAddress ip) {
		// TODO Auto-generated method stub
		String sentence = textF.getText();
		textF.setText("");

		DatagramPacket packet = new DatagramPacket(sentence.getBytes(), sentence.getBytes().length , ip , 9876);
		try {
			socket.send(packet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private void getFromServer(DatagramSocket socket)
	{
		byte[] receiveData = new byte[1024];
		DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
		try {
			socket.receive(packet);
		} catch (Exception e) {
			// TODO: handle exception
		}
		byte[] data = packet.getData();
		int len = packet.getLength();
		String modifiedSentence = new String(data).substring(0,len);
		textA.setText(modifiedSentence);
	}
}
		
