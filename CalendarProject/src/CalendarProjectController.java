
import javax.swing.JOptionPane;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.Calendar;

public class CalendarProjectController {
	
	final int DAYS_IN_WEEK = 7;
	final int MAXIMUM_DAYS_IN_MONTH = 31;
	final int BUTTON_WIDTH_SIZE = 80;
	final int NUM_OF_MONTHS = 12;
    final int SIZE = 7;
    final int DEFAULT_YEAR_VALUE = 2022;
    
    @FXML
    private Label isSave;

    @FXML
    private GridPane grid;
	
    @FXML
    private HBox hbox;
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private VBox pane;

    @FXML
    private Label yearTitle;
    
    @FXML
    private Label monthTitle;
    
    @FXML
    private ComboBox<String> yearC;
    
    @FXML
    private Label pickDay;
    
    private Button[] btnsDays;

    private Button[] btnsMonths;
    
    private ManageDate manageDate;
    
    @FXML
    public void initialize() {
        manageDate = new ManageDate();
        
    	btnsDays = new Button[SIZE*SIZE];
    	double w = grid.getPrefWidth()/SIZE;
    	double h = grid.getPrefHeight()/SIZE; 
    	    	 
    	for(int i =0;i<37;i++)
    	{
    		btnsDays[i] = new Button((i+1) + "");
    		btnsDays[i].setPrefSize(w,h);
    		grid.add(btnsDays[i] ,i % SIZE , i / SIZE);
    		
    		btnsDays[i].setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					handleButton(event);

				}
			});
			
    	}
    	
    	btnsMonths = new Button[12];
    	
    	// add buttons Month
        for (int i = 0; i < 12; i++)
        {
        	btnsMonths[i] = new Button(manageDate.getMonths()[i]);
        	pane.getChildren().add(btnsMonths[i]);
        	btnsMonths[i].setPrefWidth(BUTTON_WIDTH_SIZE);
        	btnsMonths[i].setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					handleButtonMonths(event);
				}
			});
        }
    	
        
    	initCombobox();
    	yearTitle.setText("2022");//default
    	monthTitle.setText("January");//default
    	pickDay.setText(manageDate.getDayC() + "/" + 1 + "/" + DEFAULT_YEAR_VALUE);
    	manageDate.setCalendar(DEFAULT_YEAR_VALUE, 0, 1);

    	handleMonthVisibility();//default
    }
    

    private void handleMonthVisibility()
    {    
    	for (int i = 0; i < MAXIMUM_DAYS_IN_MONTH; i++) {
			btnsDays[i].setVisible(true);
		}
    	
    	for (int i = 36 ; i >= MAXIMUM_DAYS_IN_MONTH; i--) {
			btnsDays[i].setVisible(false);
		}
    	
    	for(int i = 30; i < 30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK); i++)
    	{
			btnsDays[i].setVisible(true);
    	}

    	int daysInMonth = manageDate.getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);

    	if(daysInMonth == 31)
    	{
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 1].setVisible(true);
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 2].setVisible(true);
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 3].setVisible(true);
    	}
    	else if(daysInMonth == 30)
    	{
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 1].setVisible(false);
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 2].setVisible(true);
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 3].setVisible(true);
    	}
    	else if(daysInMonth == 29)
    	{
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 1].setVisible(false);
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 2].setVisible(false);
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 3].setVisible(true);
    	}
    	else //daysInMonth == 28
    	{
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 1].setVisible(false);
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 2].setVisible(false);
    		btnsDays[30 + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 3].setVisible(false);
    	}

    	int i = 0;
        while(manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) != i+1)
    	{
    		btnsDays[i].setVisible(false);
    		i++;
    	}
    	
    	int counter = 0;
    	for (int j = manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 1; j < MAXIMUM_DAYS_IN_MONTH + manageDate.getCalendar().get(Calendar.DAY_OF_WEEK) - 1; j++) {
    		counter++;
    		btnsDays[j].setText(counter + "");
		}
    	
    }
    
    private void handleButtonMonths(ActionEvent event) {
    	Button b = (Button)event.getSource();
    	monthTitle.setText(b.getText());

    	manageDate.setCalendar(Integer.parseInt(yearTitle.getText()), extractMonthNumber(),1);//newClass
    	
    	yearTitle.setText(yearC.getValue());
    	handleMonthVisibility();
    	pickDay.setText("--/--/----");
    	restartElementsText();

    }
    private void restartElementsText()
    {
    	textArea.setText("");
    	isSave.setText("");
    }
    private void handleButton(ActionEvent event) {
    	Button b = (Button)event.getSource();
    	manageDate.setDayC(Integer.parseInt(b.getText()));

    	yearTitle.setText(yearC.getValue());
    	
    	manageDate.setCalendar(Integer.parseInt(yearTitle.getText()), extractMonthNumber(),manageDate.getDayC());//newClass
    	
    	
    	b.setStyle("-fx-base: lightgreen");
    	PauseTransition pause = new PauseTransition(
    	    Duration.seconds(1)
    	);
    	pause.setOnFinished(event1 -> 
    	{
    	    b.setStyle(null);
    	}
    	);
    	pause.play();    

    	pickDay.setText(manageDate.getDayC() + "/" + (extractMonthNumber()+1) + "/" + yearC.getValue());
    	restartElementsText();
    }

    
    private int extractMonthNumber()
    {
    	int currentMonthNum = 0;
    	
    	while(currentMonthNum < NUM_OF_MONTHS && !(manageDate.getMonths()[currentMonthNum].equals(monthTitle.getText())))
    	{
    		currentMonthNum++; 
    	}
    	
    	return currentMonthNum;
    }
    
    @FXML
    void addPressed(ActionEvent event) {
    	if(checkingPickingDay())
    	{
        	Date d = new Date(manageDate.getCalendar().get(Calendar.YEAR),manageDate.getCalendar().get(Calendar.MONTH),manageDate.getCalendar().get(Calendar.DAY_OF_MONTH));

        	manageDate.setCalendar(Integer.parseInt(yearTitle.getText()), extractMonthNumber(),manageDate.getDayC());//newClass

        	manageDate.getHash().put(d, textArea.getText());
        	isSave.setText("Saved!");
    	}
    }
    

    @FXML
    void showPressed(ActionEvent event) {
    	if(checkingPickingDay())
    	{        	
        	Date d = new Date(manageDate.getCalendar().get(Calendar.YEAR),manageDate.getCalendar().get(Calendar.MONTH),manageDate.getCalendar().get(Calendar.DAY_OF_MONTH));
        	textArea.setText(manageDate.getHash().get(d));	
    	}

    }
    
    private Boolean checkingPickingDay()
    {
    	if(pickDay.getText().equals("--/--/----"))
    	{
    		JOptionPane.showMessageDialog(null, "You must choose the day","Error",JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    	return true;
    }
    @FXML
    void submitPressed(ActionEvent event) {
    	yearTitle.setText(yearC.getValue());
    	
    	manageDate.setCalendar(Integer.parseInt(yearTitle.getText()), extractMonthNumber(),manageDate.getDayC() );//newClass

    	handleMonthVisibility();
    	pickDay.setText("--/--/----");
    	restartElementsText();
    }

	private void initCombobox()
	{
		yearC.setPrefWidth(80);
    	final int START_YEAR = 1970, END_YEAR = 2050 ;
    	for(int i = START_YEAR; i <= END_YEAR;i++)
    	{
    		yearC.getItems().add(i+ "");
    	}
    	yearC.setValue(DEFAULT_YEAR_VALUE + "");
	}
    
    
}
