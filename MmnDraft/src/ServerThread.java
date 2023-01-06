import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ServerThread extends Thread {
	private Socket s = null;
	
	public ServerThread(Socket socket) {
		super();
		this.s = socket;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			handleReadAndWrite();
		}catch(Exception e) {}
	
	}
	
	private void handleReadAndWrite() throws Exception
	{
		OutputStream outputStream = s.getOutputStream();
		ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
		InputStream inputStream = s.getInputStream();
		ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
		
		// get Data from client
		Game game;
		game = (Game)objInputStream.readObject();
		
		// process the pair of cards and send Data to client
    	if(game.getFirstCard() == null )
    	{
    		game.setFirstCard(game.getBoardGame().getBoard()[game.getCurrentRow()][game.getCurrentCol()]);
			game.setWasGuessed(false);
    	}


    	else if(game.getSecondCard() == null )
    	{
    		game.setSecondCard(game.getBoardGame().getBoard()[game.getCurrentRow()][game.getCurrentCol()]);
			game.setWasGuessed(false);
    	}
    	else
    	{
    		if(game.getFirstCard().getValue().equals(game.getSecondCard().getValue()))
    		{
    			game.getBoardGame().getBoard()[game.getFirstCard().getRow()][game.getFirstCard().getCol()].setWasGuessed(true);
    			game.getBoardGame().getBoard()[game.getSecondCard().getRow()][game.getSecondCard().getCol()].setWasGuessed(true);
    			game.setWasGuessed(true);
    		}
    		else
    		{
    			game.setIndexOfFirstCard((game.getFirstCard().getRow() * game.getBoardGame().getSizeOfMatrix()) + game.getFirstCard().getCol());
    			game.setIndexOfSecondCard((game.getSecondCard().getRow() * game.getBoardGame().getSizeOfMatrix()) + game.getSecondCard().getCol());
    			game.setWasGuessed(false);

    		}
    		game.setFirstCard(game.getBoardGame().getBoard()[game.getCurrentRow()][game.getCurrentCol()]);
    		game.setSecondCard(null);
    	}

		objOutputStream.writeObject(game);
		
		//close all connections
		objInputStream.close();
		inputStream.close();
		objOutputStream.close();
		outputStream.close();
		s.close();

		
	}
}
