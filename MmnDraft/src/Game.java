import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Game implements Serializable {
    private Cell firstCard = null;
    private Cell secondCard = null;
    private Board boardGame;
    private int currentRow;
    private int currentCol;
    private int indexOfFirstCard;
    private int indexOfSecondCard;
    private Boolean wasGuessed;
    

    
	public Game(Cell firstCard, Cell secondCard , Board boardGame ,int currentRow 
			,int currentCol,int indexOfFirstCard , int indexOfSecondCard , Boolean wasGuessed) {
		super();
		this.firstCard = firstCard;
		this.secondCard = secondCard;
		this.boardGame = boardGame;
		this.currentRow = currentRow;
		this.currentCol = currentCol;
		this.indexOfFirstCard = indexOfFirstCard;
		this.indexOfSecondCard = indexOfSecondCard;
		this.wasGuessed = wasGuessed;
	}
	
	
	public Cell getFirstCard() {
		return firstCard;
	}
	public void setFirstCard(Cell firstCard) {
		this.firstCard = firstCard;
	}
	public Cell getSecondCard() {
		return secondCard;
	}
	public void setSecondCard(Cell secondCard) {
		this.secondCard = secondCard;
	}


	public Board getBoardGame() {
		return boardGame;
	}


	public void setBoardGame(Board boardGame) {
		this.boardGame = boardGame;
	}
    
	
	
	
	public int getCurrentRow() {
		return currentRow;
	}


	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}


	public int getCurrentCol() {
		return currentCol;
	}


	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}


	public int getIndexOfFirstCard() {
		return indexOfFirstCard;
	}


	public void setIndexOfFirstCard(int indexOfFirstCard) {
		this.indexOfFirstCard = indexOfFirstCard;
	}


	public int getIndexOfSecondCard() {
		return indexOfSecondCard;
	}


	public void setIndexOfSecondCard(int indexOfSecondCard) {
		this.indexOfSecondCard = indexOfSecondCard;
	}


	public Boolean getWasGuessed() {
		return wasGuessed;
	}


	public void setWasGuessed(Boolean wasGuessed) {
		this.wasGuessed = wasGuessed;
	}

	
	
	
/*
	public Boolean checkIfPairOfCardEquals(int rowSelected , int colSelected)
	{
		return checkingPair(rowSelected , colSelected);
	}
	
    private Boolean checkingPair(int rowSelected , int colSelected)
    {

    	if(getFirstCard() == null )
    	{
    		setFirstCard(getBoardGame().getBoard()[rowSelected][colSelected]);
    		return false;
    	}
    		
    	
    	else if(getSecondCard() == null )
    	{
    		setSecondCard(getBoardGame().getBoard()[rowSelected][colSelected]);
    		return false;
    	}
    	
    	else
    	{
    		if(getFirstCard().getValue().equals(getSecondCard().getValue()))
    		{
    			getBoardGame().getBoard()[getFirstCard().getRow()][getFirstCard().getCol()].setWasGuessed(true);
    			getBoardGame().getBoard()[getSecondCard().getRow()][getSecondCard().getCol()].setWasGuessed(true);
        		return true;
    		}    	

    		setFirstCard(getBoardGame().getBoard()[rowSelected][colSelected]);
    		setSecondCard(null);
    	}
		return false;

    	
    }
*/

}
