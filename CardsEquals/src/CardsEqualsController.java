
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CardsEqualsController {

    @FXML
    private GridPane grid;
    
    private Button[][] btns;
    
    private int score;
    
    private int valOfLastCard;
    private final int SIZE = 10;
    private Random rand;
    
    public void initialize() {
    	
    	btns = new Button[SIZE][SIZE];
    	valOfLastCard = -1;
    	score = 0;
    	double w = grid.getPrefWidth()/SIZE;
    	double h = grid.getPrefHeight()/SIZE;
    	rand = new Random();
    	
    	for (int i = 0; i < SIZE; i++) {
    		for (int j = 0; j < SIZE; j++) {
				btns[i][j] = new Button();
				btns[i][j].setPrefSize(w, h);
				btns[i][j].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						hadnleButton(event);
					}
				});
				grid.add(btns[i][j],j,i);
			}
		}

    	
	}
    
	private void hadnleButton(ActionEvent event) {
		// TODO Auto-generated method stub
		Button b = (Button)event.getSource();
		int randomNum = 1 + rand.nextInt(3);
		if(valOfLastCard == -1)
		{
			valOfLastCard = randomNum;
		}
		else
		{
			if(randomNum == valOfLastCard)
			{
				JOptionPane.showMessageDialog(null, "Great you got it!\n " + "Your score is : " + score );
				String message = JOptionPane.showInputDialog("Would you like to start a new game ?(yes or no)");
				while(!message.equals("yes") && !message.equals("no") && !message.equals("Yes") && !message.equals("No"))
				{
					JOptionPane.showMessageDialog(null, "Bad Input - try again" , "Error" , JOptionPane.ERROR_MESSAGE);
					message = JOptionPane.showInputDialog("Would you like to start a new game ?(yes or no)");
				}
				if(message.equals("yes") || message.equals("Yes"))
				{
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.close();
					try {
						Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("CardsEquals.fxml")); 
						Scene scene = new Scene(root); 
						stage.setTitle("CardsEquals"); 
						stage.setScene(scene); 
						stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
							@Override
							public void handle(WindowEvent event) {
								System.exit(0);
							}
						});
						stage.show();
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				else
				{
					System.exit(0);
				}
				
			}
			else
			{
				score++;
			}
			valOfLastCard = -1;

		}
		
		
		b.setText(randomNum + "");
		b.setDisable(true);

	}

}
