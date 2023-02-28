import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SoldierController {

    @FXML
    private GridPane grid;

    private Button[][] btns;
    private final int N = 10;
    private int counter;
    private int currentX , currentY;

    //@FXML
    public void initialize() {
    	counter = 1;
    	btns = new Button[N][N];	
    	double w = grid.getPrefWidth()/N;
    	double h = grid.getPrefHeight()/N;

    	for (int i = 0; i < btns.length; i++) {
			for (int j = 0; j < btns.length; j++) {
				btns[i][j] = new Button();
				btns[i][j].setPrefSize(w, h);
				
				
		   		btns[i][j].setOnAction(ActionEvent -> {
	    			handleButton2();

	    		});
				btns[i][j].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event)
					{
						hadnleButton(event);
					}

				}  );

				grid.add(btns[i][j],j,i);
			}
		}
    	
    	Random rand = new Random();
    	currentX = rand.nextInt(N);
    	currentY = rand.nextInt(N);
    	btns[currentX][currentY].setText("*");

    }


	private void handleButton2() {
		// TODO Auto-generated method stub
		System.out.println("zxczxc");
	}


	private void hadnleButton(ActionEvent event) {
		// TODO Auto-generated method stub
		/*
		Button b = (Button)event.getSource();
		b.setText(counter++ + "");
		System.out.println(b.getText());*/


    	for (int i = 0; i < btns.length; i++) {
			for (int j = 0; j < btns.length; j++) {
				
				if(event.getSource() == btns[i][j])
				{
					if(btns[i][j].getText().equals("") && Math.abs(i-currentX) + Math.abs(j-currentY) <= 1  )
					{
						btns[currentX][currentY].setText(counter++ + "");
						btns[i][j].setText("*");
						currentX = i;
						currentY = j;

					}
				}
			}
    	}

	}
    
}
