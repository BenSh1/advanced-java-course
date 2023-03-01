
import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MagicBoxController extends Thread {

    @FXML
    private ImageView img;
    
    private Boolean isVis = true;
    private Boolean isAlive = true;

    @FXML
    public void initialize() {
    	//File file = new File("img.jpg");
    	//Image image = new Image(file.toURI().toString());
    	//img.setImage(image);
    
    	start();
    }
    
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	super.run();
    	
    	while(isAlive)
    	{

    		try {
    			sleep(700);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		isVis = !isVis;
    		img.setVisible(isVis);

    	}
    }

    @FXML
    void stopPressed(ActionEvent event) {
    	isAlive = false;
    	
    }


}
