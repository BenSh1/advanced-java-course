import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;

public class PolygonController {

    @FXML
    private Canvas canv;

    @FXML
    private TextField numOfDotsInPolygon;
    
    private GraphicsContext gc;
    
    public void initialize() {
    	gc = canv.getGraphicsContext2D();
    }
    
    @FXML
    void createPolygon(ActionEvent event) {
    	int n = Integer.parseInt(numOfDotsInPolygon.getText());
    	//System.out.println(n);
    	new PolygonClient(this , "127.0.0.1" , n).start();
    }

    public void addPolygon(Polygon p) {
    	gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    	p.draw(gc);
    }
}
