import java.util.Random;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class RandomSquaresController extends Thread {

    @FXML
    private Canvas canv;
	
	private GraphicsContext gc;
	private final int N = 4;
	private final int x = 1000;
	private final int SIZE = 50;

	private int succeed;
	private int notSucceed;
	private Square s;
	private Random r;
	

	
	@FXML
	public void initialize()
	{
		gc = canv.getGraphicsContext2D();
		r = new Random();
		succeed = 0;
		notSucceed = 0;
		
		start();
	}
	


    @FXML
    void clickMouse(MouseEvent event) {
    	if(s != null && s.getX() <= event.getX() &&  event.getX() < s.getX() + s.getSize() &&
    			s.getY() <= event.getY() &&  event.getY() < s.getY() + s.getSize() )
    	{
    		synchronized(this)
    		{
    			succeed++;
    		}
    		
    		
    	}
    	else
    	{
    		synchronized(this)
    		{
    			notSucceed++;
    		}
    		
    	}
    }
    
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	super.run();
    	for (int i = 0; i < N; i++) {
    		createSquare();
    		try {
				sleep(x);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		

    		
		}
    	presentScore();
    	
    }

	private void presentScore() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Your score is " + (Math.pow(succeed, 2) - notSucceed));
	}



	private void createSquare() {
		// TODO Auto-generated method stub
		double x = r.nextDouble()*(canv.getWidth() - SIZE);
		double y = r.nextDouble()*(canv.getHeight() - SIZE);

		s = new Square(x, y, SIZE);
		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
		gc.fillRect(s.getX(), s.getY(), s.getSize(), s.getSize());
	}
    
    
}




