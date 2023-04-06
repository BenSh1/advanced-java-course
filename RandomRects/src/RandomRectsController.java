import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class RandomRectsController {

    @FXML
    private Canvas canv1;

    @FXML
    private Canvas canv2;
    
    private LinkedList<Rectangle> rects1;
    private LinkedList<Rectangle> rects2;
    
    private GraphicsContext gc1 , gc2;
    
    final int SIZE = 10;
    
    @FXML
    public void initialize() {
    	gc1 = canv1.getGraphicsContext2D();
    	gc2 = canv2.getGraphicsContext2D();
    	
    	rects1 = new LinkedList<Rectangle>();
    	rects2 = new LinkedList<Rectangle>();
    	Random r = new Random();
    	
    	for (int i = 0 ; i < SIZE;i++)
    	{
    		int x = r.nextInt((int)canv1.getWidth()-100);
    		int y = r.nextInt((int)canv1.getHeight() - 100);
    		int wh = r.nextInt(91) + 10;//10-100 ,width height
    		rects1.add( new Rectangle(x,y,wh,wh));
    		
    	}
    	
    	drawRects();
    }

    private void drawRects() {
    	gc1.clearRect(0, 0, canv1.getWidth(), canv1.getHeight());
    	gc2.clearRect(0, 0, canv2.getWidth(), canv2.getHeight());
    	
		for (Rectangle rectangle : rects1) {
			gc1.strokeRect(rectangle.getX(), rectangle.getY(),
					rectangle.getWidth(), rectangle.getHeight());
		}
		
		for (Rectangle rectangle : rects2) {
			gc2.strokeRect(rectangle.getX(), rectangle.getY(),
					rectangle.getWidth(), rectangle.getHeight());
		}
		
	}

	@FXML
    void clicked(MouseEvent event) {
		
		Rectangle r = getSmallest();
		if(r == null)
		{
			JOptionPane.showMessageDialog(null, "no squares are left!");
		}
		else {
			if (r.contains(new Point2D(event.getX() , event.getY())))
			{
				r.setX(0);
				r.setY(0);
				rects2.add(r);
				removeSmallest();
				drawRects();
 				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Not good!");
			}
		}


			
    }
	
	private Rectangle getSmallest() {
		
		if(rects1.size() == 0 )
		{
			return null;
		}
		
		Rectangle r = rects1.get(0); 
		for (Rectangle rectangle : rects1) {
			if(rectangle.getWidth() < r.getWidth())
				r = rectangle;
		}
		return r;
		
	}
	
	private void removeSmallest() {
		rects1.remove(getSmallest());
	}

}
