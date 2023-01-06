import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell {
	public String value;
	public int row;
	public int col;
	public boolean wasGuessed;
	//public Image img;asd//makes a lot of problem in this program 
	//need to change that object to String c1,c2,c3,...
	
	public Cell(String value, int row , int col) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.row = row;
		this.col = col;
		this.wasGuessed = false;
		
	}
}
