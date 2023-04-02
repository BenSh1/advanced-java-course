
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RedBallTwoController /*extends Ball*/ implements Runnable {

	private static final double SIZE = 50;

	@FXML
    private Canvas canv;
	
	private GraphicsContext gc;
	private Ball b;
	private Thread t;
	private Thread t2;

	private boolean running;
	/*
    public RedBallTwoController(int x, int y) {
		super(x, y);

		// TODO Auto-generated constructor stub
		t = new Thread(this);
		t.start();
	}*/
    
	public void initialize() {
		gc = canv.getGraphicsContext2D();
		double w = canv.getWidth()/2 - SIZE;
		double h = canv.getHeight()/2 - SIZE;
		b = new Ball((int)w, (int)h);
		running = true;
		t = new Thread(this);
		t.start();

	}
	
    @Override
    public void run() {
    	// TODO Auto-generated method stub

		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
		gc.fillOval(b.getX(), b.getY(), SIZE, SIZE);
		gc.setFill(Color.RED);
    	while(running)
    	{
    		
    		b.move();
    		
    		
    		/* //when collide in bounds start over from center of the screen
    		if(b.getX() < 0 || b.getX() + SIZE > canv.getWidth() 
    			|| b.getY() < 0 || b.getY() + SIZE > canv.getHeight())
    		{
    			double w = canv.getWidth()/2 - SIZE;
    			double h = canv.getHeight()/2 - SIZE;
    			b.setX((int)w);
    			b.setY((int)h);
    		}*/
    		if(b.getX() < 0 || b.getX() + SIZE > canv.getWidth() 
        			|| b.getY() < 0 || b.getY() + SIZE > canv.getHeight())
        		{
    			double direct = Math.random();
    			if(direct < 0.5)
    			{
    				if(b.getDx()< 0)
    					b.setDx(1);
    				else
    					b.setDx(-1);
    			}
    			else
    			{
    				if(b.getDy()< 0)
    					b.setDy(1);
    				else
    					b.setDy(-1);
    			}
        	}

    		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    		gc.fillOval(b.getX(), b.getY(), SIZE, SIZE);
    		gc.setFill(Color.RED);
    		try {
    			Thread.sleep(10);//1000 = 1 second
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    }

    @FXML
    void stopPressed(ActionEvent event) {
    	running = false;
    }

}
