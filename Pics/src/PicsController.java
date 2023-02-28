
import java.io.FileInputStream;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PicsController {

    @FXML
    private ImageView img;

    @FXML
    private Label nameOfImage;
    
    private String[] nameOfImages = {"Apple" , "Orange" ,"Watermelon"};
    private int indexOfImage = 0;
    
    //MyImage myImages = new MyImage();
	@FXML
	public void initialize() {
		
		FileInputStream input;
		try {
			input = new FileInputStream(nameOfImages[indexOfImage].concat(".jpg") );
			Image myImg = new Image(input);
			img.setImage(myImg);
	    	nameOfImage.setText(nameOfImages[indexOfImage]);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/*//you could use that code instead of the other code upper
    	nameOfImage.setText(nameOfImages[indexOfImage]);
		Image myImg = new Image(nameOfImages[indexOfImage] + ".jpg");
		img.setImage(myImg);*/
	}


    @FXML
    void goNext(ActionEvent event) {
    	indexOfImage++;

    	if (indexOfImage < nameOfImages.length)
    	{
        	nameOfImage.setText(nameOfImages[indexOfImage]);
    		Image myImg = new Image(nameOfImages[indexOfImage]+ ".jpg");
    		img.setImage(myImg);
    	}
    	else
    	{
    		indexOfImage = 0;
        	nameOfImage.setText(nameOfImages[indexOfImage]);
    		Image myImg = new Image(nameOfImages[indexOfImage]+ ".jpg");
    		img.setImage(myImg);
    	}

    }

    @FXML
    void jumpTo(ActionEvent event) {
    	String desiredImage = JOptionPane.showInputDialog("Enter the index of Image that you want");
    	int desiredIndex = -1;
    	boolean notGoodInput = false;
    	/*|| desiredImageIndex > nameOfImages.length*/
    	try {
    		desiredIndex = Integer.parseInt(desiredImage);
    		if(desiredIndex < 0  || desiredIndex >= nameOfImages.length)
    		{
    			throw new Exception();
    		}
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		JOptionPane.showMessageDialog(null,"Bad Input","Error",JOptionPane.ERROR_MESSAGE);
    		notGoodInput = true;
		}
    	
    	if(!notGoodInput)
    	{
    		indexOfImage = desiredIndex;
        	nameOfImage.setText(nameOfImages[indexOfImage]);
    		Image myImg = new Image(nameOfImages[indexOfImage]+ ".jpg");
    		img.setImage(myImg);
    	}
    	
    }

}
