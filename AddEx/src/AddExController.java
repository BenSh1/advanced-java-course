import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class AddExController {

    @FXML
    private GridPane grid;
    
    private Button[] btns;
    final int SIZE = 5;
    
    private Button x , y, z;
    private int count = 0;
    
    @FXML
    public void initialize() {
    	btns = new Button[SIZE*SIZE];
    	double w = grid.getPrefWidth()/SIZE;
    	double h = grid.getPrefHeight()/SIZE;

    	
    	for(int i =0;i<SIZE*SIZE;i++)
    	{
    		btns[i] = new Button((i+1) + "");
    		btns[i].setPrefSize(w,h);
    		grid.add(btns[i] ,i % SIZE , i / SIZE);

    		
    		btns[i].setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					handleButton(event);
					
				}
			});
    		/*
    		btns[i].setOnMouseClicked(mouseEvent ->{
				handleButton3();
    		});
    		
    		btns[i].setOnAction(mainEvent ->{
				handleButton4();
    		});
    		
    		btns[i].setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    // TODO Auto-generated method stub
					handleButton2(event);
                }
            });*/
    	}
    	
    	/*
    	int k = 1;
    	for (int i = 0; i < SIZE; i++) {
    		for (int j = 0; j < SIZE; j++) {
    			btns[i] = new Button((k) + "");
        		btns[i].setPrefSize(w,h);
        		grid.add(btns[i] ,j , i);
        		k++;
        		
        		btns[i].setOnAction(new EventHandler<ActionEvent>() {
    				
    				@Override
    				public void handle(ActionEvent event) {
    					handleButton(event);
    					
    				}
    			});
			}
			
		}*/
    	
    	
    }
    private void handleButton4() {
		// TODO Auto-generated method stub
    	System.out.println("ben");
    	btns[0].setDisable(true);
		
	}
	private void handleButton3() {
		// TODO Auto-generated method stub
		System.out.println("Zxczxc");
	}




	@FXML
    void finishPressed(ActionEvent event) {
		JOptionPane.showMessageDialog(null, count);
    }
    private void handleButton2(MouseEvent event) {
    	//Button b = (Button)event.getSource();
    	//System.out.println(b.getText());
    	
    	if(x==null)
    	{
    		x = (Button)event.getSource();
    	}
    	else if(y==null)
    	{
    		y = (Button)event.getSource();
    	}
    	else
    	{
    		z = (Button)event.getSource();
    		
    		int xValue = Integer.parseInt(x.getText());
    		int yValue = Integer.parseInt(y.getText());
    		int zValue = Integer.parseInt(z.getText());
    		
    		if(xValue + yValue == zValue)
    		{
    			x.setText("");
    			x.setDisable(true);
    			//x.setVisible(false);
    			y.setText("");
    			y.setDisable(true);
    			z.setText("");
    			z.setDisable(true);
    			
    			count++;
    		}
    		else 
    		{
    			JOptionPane.showMessageDialog(null, "Not good");
    		}
    		
    		x = null;
    		y = null;
    		z = null;
    		
    	}
    }
    private void handleButton(ActionEvent event) {
    	//Button b = (Button)event.getSource();
    	//System.out.println(b.getText());
    	
    	if(x==null)
    	{
    		x = (Button)event.getSource();
    	}
    	else if(y==null)
    	{
    		y = (Button)event.getSource();
    	}
    	else
    	{
    		z = (Button)event.getSource();
    		
    		int xValue = Integer.parseInt(x.getText());
    		int yValue = Integer.parseInt(y.getText());
    		int zValue = Integer.parseInt(z.getText());
    		
    		if(xValue + yValue == zValue)
    		{
    			x.setText("");
    			x.setDisable(true);
    			//x.setVisible(false);
    			y.setText("");
    			y.setDisable(true);
    			z.setText("");
    			z.setDisable(true);
    			
    			count++;
    		}
    		else 
    		{
    			JOptionPane.showMessageDialog(null, "Not good");
    		}
    		
    		x = null;
    		y = null;
    		z = null;
    		
    	}
    }
}
