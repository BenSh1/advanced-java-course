import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.scene.image.Image;

public class Board {

	private Cell[][] board;
	private int sizeOfMatrix;
	
	public Board( int n)
	{
		this.board = new Cell[n][n];
		this.sizeOfMatrix = n;
	}
	
	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	public int getSizeOfMatrix() {
		return sizeOfMatrix;
	}

	public void setSizeOfMatrix(int sizeOfMatrix) {
		this.sizeOfMatrix = sizeOfMatrix;
	}

	public void populateMatrix()
	{
		board = new Cell[sizeOfMatrix][sizeOfMatrix];
        Random rand = new Random();
        /*
		ArrayList<Image> cardList = new ArrayList<Image>();
      
        for (int i = 0; i < (sizeOfMatrix*sizeOfMatrix)/2; i++) {

        	Image img = new Image("c"+(i+1)+".png");
        	cardList.add(img);
        	System.out.println(img);

		}
		Collections.shuffle(cardList);
		*/

        ArrayList<String> cardList = new ArrayList<String>();
        
        for (int i = 0; i < (sizeOfMatrix*sizeOfMatrix)/2; i++) {
        	cardList.add("c"+(i+1)+".png");
		}

        while(!isBoardFull())
        {
        	int randomInedxOfImage = rand.nextInt(cardList.size());
        	
        	int randomRow1 = rand.nextInt(sizeOfMatrix);        	
        	int randomCol1 = rand.nextInt(sizeOfMatrix);
        	
            while(board[randomRow1][randomCol1] != null)
            {
            	randomRow1 = rand.nextInt(sizeOfMatrix);        	
            	randomCol1 = rand.nextInt(sizeOfMatrix);
            }
            
        	int randomRow2 = rand.nextInt(sizeOfMatrix);        	
        	int randomCol2 = rand.nextInt(sizeOfMatrix);
            
            while( (randomRow1 == randomRow2 && randomCol1 == randomCol2) 
            		|| (board[randomRow2][randomCol2] != null) )
            {
            	randomRow2 = rand.nextInt(sizeOfMatrix);        	
            	randomCol2 = rand.nextInt(sizeOfMatrix);


            }
            board[randomRow1][randomCol1] = new Cell(cardList.get(randomInedxOfImage) ,randomRow1, randomCol1);
            board[randomRow2][randomCol2] = new Cell(cardList.get(randomInedxOfImage) ,randomRow2, randomCol2);
            //cardList.remove(randomInedxOfImage);
            
        }
	}
	private boolean isBoardFull() {
		for (int i = 0; i < sizeOfMatrix; i++) {
			for (int j = 0; j < sizeOfMatrix; j++) {
				if(board[i][j] == null)
					return false;
			}
		}
		return true;
	}
}
