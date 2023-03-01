import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class RedBallController extends Thread {

    @FXML
    private Canvas canv;
    
    private GraphicsContext gc;
    
    private boolean isAlive = true;
    
    private int x= 10 , y = 10;
    
    @FXML
    public void initialize() {
    	gc = canv.getGraphicsContext2D();
    	start();
    }

    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	super.run();
    	final int SIZE =50;
    	
    	while(isAlive)
    	{
    		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    		gc.fillOval(x, y, SIZE, SIZE);
    		try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		x++;
    		y++;
    		/*x+=10;
    		y+=10;*/
    	}
    }
    

    @FXML
    void stopPressed(ActionEvent event) {
    	isAlive = false;
    }
    
}
