import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class SquaresGameController {

    @FXML
    private Canvas canv;
    
    private GraphicsContext gc;
    private final int SIZE =30;

    private final int NUM_OF_SQUARES =10;
    //private Square[] squares;
    
    private LinkedList<Rectangle> squares;
    private double startTime;
    private double endTime;


    public void initialize()
    {
    	gc = canv.getGraphicsContext2D();
    	//squares = new Square[NUM_OF_SQUARES];
    	squares = new LinkedList<Rectangle>();
    	Random r = new Random();
    	
    	for (int i = 0; i < NUM_OF_SQUARES; i++) {
    		double x = r.nextDouble()*(canv.getHeight() -11);
    		double y = r.nextDouble()*(canv.getHeight() -20);
    		int wh = 30;//width height

    		squares.add(new Rectangle(x, x, wh, wh));
			
		}
    }
    

    @FXML
    void pressedSquare(MouseEvent event) {

    	if(squares.size() == 0)
    	{
    		endTime = System.currentTimeMillis();
    		JOptionPane.showMessageDialog(null,"Your time is: " + (endTime-startTime ) );
    	}
    	else
    	{
    		int i = 0;
    		for(Rectangle sqaur : squares)
    		{
    			if(event.getX() > sqaur.getX() && event.getX() < (sqaur.getX()+SIZE) 
    					&& event.getY() > sqaur.getY() && event.getY() < (sqaur.getY()+SIZE))
    			{
    				gc.clearRect(sqaur.getX()-1, sqaur.getY()-1,SIZE+1, SIZE+1);
    				squares.remove(i);
    				break;
    			}
    			i++;
    		}

        	if(squares.size() == 0)
        	{
        		endTime = System.currentTimeMillis();
        		JOptionPane.showMessageDialog(null,"Your time is: " + (endTime- startTime) );
        	}
    	}
    	


    	
    }

    @FXML
    void pressedGo(ActionEvent event) {
    	double startTime = System.currentTimeMillis();
    	
		for (Rectangle square : squares) {
			gc.strokeRect(square.getX() , square.getY() ,SIZE,SIZE );
		}
		
    }

}
