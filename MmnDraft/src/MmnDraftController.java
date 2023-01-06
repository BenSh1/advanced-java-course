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

    private Cell firstCard = null;
    private Cell secondCard = null;

    
    @FXML
    public void initialize() {
    	String n = JOptionPane.showInputDialog("Please enter the size of the matrix of cards");
    	final int SIZE = Integer.parseInt(n);
    	board = new Board(SIZE);
    	board.populateMatrix();


     	
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
	    		grid.add(vbox,i%SIZE,i/SIZE);*/
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
	    		});*/
	    		
	    		i++;
	    		
	    		
			}
		}

    }
    private void handleCards(MouseEvent event ) {

    	Node soucrceComponent = (Node) event.getSource();
    	String rowAndColum = (String)soucrceComponent.getUserData();
    	System.out.println(rowAndColum);
    	int rowSelected = Integer.parseInt(rowAndColum.split(",")[0]);
    	int colSelected = Integer.parseInt(rowAndColum.split(",")[1]);

    	String nameOfImg = board.getBoard()[rowSelected][colSelected].value;
    	System.out.println(nameOfImg);
    	Image selectedImage = new Image(nameOfImg);
    	((ImageView)soucrceComponent).setImage(selectedImage);
    	
    	checkingPair(rowSelected ,colSelected );
    }
    
    private void checkingPair(int rowSelected , int colSelected)
    {
    	if(firstCard == null )
    		firstCard = board.getBoard()[rowSelected][colSelected];
    	else if(secondCard == null )
    		secondCard = board.getBoard()[rowSelected][colSelected];
    	else
    	{
    		if(firstCard.value.equals(secondCard.value) )
    		{
    			board.getBoard()[firstCard.row][firstCard.col].wasGuessed = true;
    			board.getBoard()[secondCard.row][secondCard.col].wasGuessed = true;
    		}
    		else
    		{
    			int indexOfFirstCard = (firstCard.row * board.getSizeOfMatrix()) + firstCard.col;
    			((ImageView)grid.getChildren().get(indexOfFirstCard)).setImage(new Image("blank.jpg"));
    			
    			int indexOfSecondCard = (secondCard.row * board.getSizeOfMatrix()) + secondCard.col;
    			((ImageView)grid.getChildren().get(indexOfSecondCard)).setImage(new Image("blank.jpg"));
    			
    		}
    		firstCard = board.getBoard()[rowSelected][colSelected];
    		secondCard = null;
    	}
    	
    }
    
}
