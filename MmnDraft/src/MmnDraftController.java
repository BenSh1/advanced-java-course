import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MmnDraftController {
    @FXML
    private GridPane grid;
    
    
    @FXML
    private VBox vbox;
    
    Board board;
    
    private ImageView[] imageCards;
    
    private Game game;

    private Cell firstCard = null;
    private Cell secondCard = null;

    
    @FXML
    public void initialize() {
    	String n = JOptionPane.showInputDialog("Please enter the size of the matrix of cards");
    	final int SIZE = Integer.parseInt(n);
    	board = new Board(SIZE);
    	
    	board.populateMatrix();


     	game = new Game(null ,null,board , -1, -1, -1 , -1 , false);
     	
    	imageCards = new ImageView[SIZE*SIZE];
    	double w = grid.getPrefWidth()/SIZE;
    	double h = grid.getPrefHeight()/SIZE; 

    	
    	int i = 0;
    	for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
	    		imageCards[i] = new ImageView("blank.jpg");
	    		imageCards[i].setFitWidth(w);
	    		imageCards[i].setFitHeight(h);

	        	imageCards[i].setUserData(row+","+col);
	        	/*
	            VBox vbox = new VBox();
	        	vbox.getChildren().add(imageCards[i]);
	        	vbox.setPrefSize(w, h);
	        	//imageCards[i].setUserData(row+","+col);
	    		grid.add(vbox,i%SIZE,i/SIZE);
	    		*/
	        	imageCards[i].setOnMouseClicked(event -> handleCards(event));

	    		grid.add(imageCards[i],i%SIZE,i/SIZE);
	    		
	    		/*
	    		imageCards[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	    		     @Override
	    		     public void handle(MouseEvent event) {
	    		         handleCards(event );
	    		         System.out.println("Tile pressed ");
	    		         event.consume();
	    		     }
	    		});
	    		*/
	    		
	    		i++;
			}
		}
    	new MmnDraftThread( this , "127.0.0.1" , game).start();

    }
    private void handleCards(MouseEvent event ) {

    	Node soucrceComponent = (Node) event.getSource();
    	String rowAndColum = (String)soucrceComponent.getUserData();
    	System.out.println(rowAndColum);
    	
    	//int rowSelected = Integer.parseInt(rowAndColum.split(",")[0]);
    	game.setCurrentRow(Integer.parseInt(rowAndColum.split(",")[0]));
    	
    	//int colSelected = Integer.parseInt(rowAndColum.split(",")[1]);
    	game.setCurrentCol(Integer.parseInt(rowAndColum.split(",")[1]));


    	//String nameOfImg = board.getBoard()[rowSelected][colSelected].getValue();
    	String nameOfImg = board.getBoard()[game.getCurrentRow()][game.getCurrentCol()].getValue();
    	
    	System.out.println(nameOfImg);
    	Image selectedImage = new Image(nameOfImg);
    	((ImageView)soucrceComponent).setImage(selectedImage);
    	
    	//checkingPair(rowSelected ,colSelected );
    }

    private void checkingPair(int rowSelected , int colSelected)
    {

    	if(game.getFirstCard() == null )
    		game.setFirstCard(board.getBoard()[rowSelected][colSelected]);
    	
    	else if(game.getSecondCard() == null )
    		game.setSecondCard(board.getBoard()[rowSelected][colSelected]);
    	
    	else
    	{
    		if(game.getFirstCard().getValue().equals(game.getSecondCard().getValue()))
    		{
    			board.getBoard()[game.getFirstCard().getRow()][game.getFirstCard().getCol()].setWasGuessed(true);
    			board.getBoard()[game.getSecondCard().getRow()][game.getSecondCard().getCol()].setWasGuessed(true);
    		}
    		else
    		{
    			int indexOfFirstCard = (game.getFirstCard().getRow() * board.getSizeOfMatrix()) + game.getFirstCard().getCol();
    			((ImageView)grid.getChildren().get(indexOfFirstCard)).setImage(new Image("blank.jpg"));
    			
    			int indexOfSecondCard = (game.getSecondCard().getRow() * board.getSizeOfMatrix()) + game.getSecondCard().getCol();
    			((ImageView)grid.getChildren().get(indexOfSecondCard)).setImage(new Image("blank.jpg"));
    			
    		}
    		game.setFirstCard(board.getBoard()[rowSelected][colSelected]);
    		game.setSecondCard(null);
    	}
    	
    }
    
    public void setCardsBlank(Boolean isEqual)
    {
    	if(isEqual)
    	{
    		((ImageView)grid.getChildren().get(game.getIndexOfFirstCard())).setImage(new Image("blank.jpg"));
    		
    		((ImageView)grid.getChildren().get(game.getIndexOfSecondCard())).setImage(new Image("blank.jpg"));
    	}

    }
    
    
}
