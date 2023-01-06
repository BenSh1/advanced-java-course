import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell implements Serializable{
	private String value;
	private int row;
	private int col;
	private boolean wasGuessed;
	//public Image img;asd//makes a lot of problem in this program 
	//need to change that object to String c1,c2,c3,...
	
	public Cell(String value, int row , int col) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.row = row;
		this.col = col;
		this.wasGuessed = false;
		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isWasGuessed() {
		return wasGuessed;
	}

	public void setWasGuessed(boolean wasGuessed) {
		this.wasGuessed = wasGuessed;
	}
	
	
}
