import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MathDemoController {

    @FXML
    private TextField leftNum;

    @FXML
    private ComboBox<String> operation;

    @FXML
    private TextField result;

    @FXML
    private TextField rightNum;

	final int NUMBER_OF_OPERATORS = 3;
    
    public void initialize()
    {
		operation.getItems().add("+");
		operation.getItems().add("-");
		operation.getItems().add("*");
    	operation.setValue("+");
    	
    }
    

    @FXML
    void pressedEqual(ActionEvent event) {
    	Double x = 0.0;
    	Double y = 0.0;
    	Double outcome = 0.0;
    	if(leftNum.getText().equals("") || rightNum.getText().equals(""))
    	{
    		JOptionPane.showMessageDialog(null,"You can't left text file without number!","Error", JOptionPane.ERROR_MESSAGE);
    	}
    	else
    	{ 
    		try {
    			x = Double.parseDouble(leftNum.getText());
    			y = Double.parseDouble(rightNum.getText());
    			
       			if(operation.getValue().equals("+"))
       			{
       				outcome = x + y;
       			} 
       			else if(operation.getValue().equals("-"))
       			{
       				outcome = x - y;
       			}
       			else
       			{
       				outcome = x * y;
       			}
    			result.setText(outcome + "");

    		} catch (Exception e) {
				// TODO: handle exception
        		JOptionPane.showMessageDialog(null,"You must choose only numbers!","Error", JOptionPane.ERROR_MESSAGE);
    			rightNum.setText("");
    			leftNum.setText("");
    			result.setText("");
    		}
    		
    	}
    }
}
