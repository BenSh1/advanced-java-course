import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class PracticeLexicalOrderController {

    @FXML
    private GridPane grid;

    @FXML
    private Pane pane;
    private Label[] labels;
    private final int SIZE = 10;
    
    private int heightForNextWord;  
    
    public void initialize() {

    	initializeWords();
		
    	/*
    	l1.setOnMousePressed(new EventHandler<MouseEvent>() {
    		
    		public void handle(MouseEvent event) {
    			handleLabels(event);
    			
    		}

    	});*/
    	/*Button b = new Button("asdas");
    	b.setOnMouseClicked(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent event) {
    			
    		}
    	});*/
    }
    
    public void initializeWords() {
    	labels = new Label[SIZE];
    	heightForNextWord = 0;
    	for (int i = 0; i < SIZE; i++) {
    		labels[i] = new Label("Word-"+(i+1));
    		labels[i].setLayoutX(100);
    		labels[i].setLayoutY(10 + i*30);	
        	pane.getChildren().add(labels[i]);
        	labels[i].setOnMousePressed(new EventHandler<MouseEvent>() {
        		
        		public void handle(MouseEvent event) {
        			handleLabels(event);
        			
        		}

        	});
    	}
    }

    @FXML
    void checkPressed(ActionEvent event) {
    	
    }

    @FXML
    void startPressed(ActionEvent event) {
    	initializeWords();
    }

	private void handleLabels(MouseEvent event) {
		// TODO Auto-generated method stub
		heightForNextWord += 30;
		Label l = (Label)event.getSource();
		double y = l.getLayoutY();
		double x = l.getLayoutX();

		System.out.println(l.getText());
		l.setLayoutX(pane.getPrefWidth() - x);
		l.setLayoutY(heightForNextWord);
	}

}
