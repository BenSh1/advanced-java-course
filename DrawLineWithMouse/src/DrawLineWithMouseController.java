import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class DrawLineWithMouseController {

    @FXML
    private Canvas canv;
    
    private GraphicsContext gc;
    
    private double lastX  = -1 , lastY  = -1;
    
    @FXML
    public void initialize() {
    	gc = canv.getGraphicsContext2D();
    }


    @FXML
    void clearPressed(ActionEvent event) {

    	gc.clearRect(0, 0, canv.getWidth(),canv.getHeight());
    }

    @FXML
    void pressed(MouseEvent event) {

    	if(lastX != -1 &&  lastY != -1)
    	{
    		gc.strokeLine(lastX , lastY , event.getX() , event.getY());
    	}
    	
    	lastX = event.getX();
    	lastY = event.getY();
    }

}
