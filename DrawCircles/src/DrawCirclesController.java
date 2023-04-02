import java.util.Random;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class DrawCirclesController {

    @FXML
    private Canvas canv;
    private final int SIZE = 10;
    private GraphicsContext gc;
    private int i;
    private Random r;
    
    public void initialize() {
    	gc = canv.getGraphicsContext2D();
    	i = 0;
    	r = new Random();
    }
    
    @FXML
    void createPressed(ActionEvent event) {
    	i++;
    	drawCircles(i);
    }

    @FXML
    void deletePressed(ActionEvent event) {
    	if(i == 0)
    	{
    		JOptionPane.showMessageDialog(null, "Can't delete cause there aren't any circles yet!");
    	}
    	else {
        	i--;
        	drawCircles(i);
    	}

    }
    private void drawCircles(int i) {
    	gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    	for (int j = 0; j < i; j++) {
    		double x = r.nextDouble()*canv.getWidth() - SIZE;
    		double y = r.nextDouble()*canv.getHeight() - SIZE;
			gc.strokeOval(x, y, SIZE, SIZE);
			gc.fillOval(x, y, SIZE, SIZE);

		}
    }

}
