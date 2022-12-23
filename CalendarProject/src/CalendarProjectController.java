import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

public class CalendarProjectController {
	
	final int DAYS_IN_WEEK = 7;
	final int MAXIMUM_DAYS_IN_MONTH = 31;
	final int MONTHS = 12;
	final int BUTTON_WIDTH_SIZE = 80;

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
    
   /* @FXML
    private ToggleGroup options;*/

    private Button[] btnsDays;
    final int SIZE = 5;

    final int COLUMNS = 7;

    private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };


    private Button[] btnsMonths;
    
    private int dayC;
    
    private HashMap<Date, String> hash;
    //private HashMap<Calendar, String> hash;
    private Calendar calendar;

    @FXML
    public void initialize() {
    	hash =  new HashMap<Date,String>();
    	//hash =  new HashMap<Calendar,String>();
    	calendar = Calendar.getInstance();
    	
    	btnsDays = new Button[COLUMNS*COLUMNS];
    	double w = grid.getPrefWidth()/COLUMNS;
    	double h = grid.getPrefHeight()/COLUMNS; 
    	    	 
    	for(int i =0;i<37;i++)
    	{
    		btnsDays[i] = new Button((i+1) + "");
    		btnsDays[i].setPrefSize(w,h);
    		grid.add(btnsDays[i] ,i % COLUMNS , i / COLUMNS);
    		
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
        	btnsMonths[i] = new Button(months[i]);
        	//pane.getChildren().add(new Button(months[i]));
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
    	
    	//loadFromFile();


    	yearTitle.setText("2022");//default
    	monthTitle.setText("January");//default
    	calendar.set(Integer.parseInt(yearTitle.getText()), extractMonthNumber(),1);//default
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

    	for(int i = 30; i < 30 + calendar.get(Calendar.DAY_OF_WEEK); i++)
    	{
			btnsDays[i].setVisible(true);
    	}

    	int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    	
    	if(daysInMonth == 31)
    	{
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 1].setVisible(true);
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 2].setVisible(true);
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 3].setVisible(true);
    	}
    	else if(daysInMonth == 30)
    	{
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 1].setVisible(false);
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 2].setVisible(true);
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 3].setVisible(true);
    	}
    	else if(daysInMonth == 29)
    	{
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 1].setVisible(false);
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 2].setVisible(false);
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 3].setVisible(true);
    	}
    	else //daysInMonth == 28
    	{
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 1].setVisible(false);
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 2].setVisible(false);
    		btnsDays[30 + calendar.get(Calendar.DAY_OF_WEEK) - 3].setVisible(false);
    	}

    	int i = 0;
    	while(calendar.get(Calendar.DAY_OF_WEEK) != i+1)
    	{
    		btnsDays[i].setVisible(false);
    		i++;
    	}
    	
    	int counter = 0;
    	for (int j = calendar.get(Calendar.DAY_OF_WEEK) - 1; j < MAXIMUM_DAYS_IN_MONTH + calendar.get(Calendar.DAY_OF_WEEK) - 1; j++) {
    		counter++;
    		btnsDays[j].setText(counter + "");
		}
    	
    }
    
    private void handleButtonMonths(ActionEvent event) {
    	Button b = (Button)event.getSource();
    	monthTitle.setText(b.getText());
    
    	calendar.set(Integer.parseInt(yearTitle.getText()), extractMonthNumber(),1);
    	yearTitle.setText(yearC.getValue());
    	handleMonthVisibility();
    	
    }
    
    private void handleButton(ActionEvent event) {
    	Button b = (Button)event.getSource();
    	dayC = Integer.parseInt(b.getText());
    	
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
    	
    	pickDay.setText(dayC + "");
    }
    
    private int extractMonthNumber()
    {
    	int currentMonthNum = 0;
    	while(currentMonthNum < MONTHS && !(months[currentMonthNum].equals(monthTitle.getText())))
    	{
    		currentMonthNum++; 
    	}
    	
    	return currentMonthNum;
    }
    
    @FXML
    void addPressed(ActionEvent event) {
    	if(checkingPickingDay())
    	{
        	Date d = new Date(Integer.parseInt(yearTitle.getText()) , extractMonthNumber(),dayC);
        	hash.put(d, textArea.getText());
        	textArea.setText("");
    	}

    	
    	//addClosingEvent();
    }


    @FXML
    void showPressed(ActionEvent event) {
    	if(checkingPickingDay())
    	{
        	Date d = new Date(Integer.parseInt(yearTitle.getText()) , extractMonthNumber(),dayC);
        	textArea.setText(hash.get(d));	
    	}

    }
    
    private Boolean checkingPickingDay()
    {
    	if(pickDay.getText().equals(""))
    	{
    		JOptionPane.showMessageDialog(null, "You must choose the day","Error",JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    	return true;
    }
    
    /*
    private void addClosingEvent()
    {
    	Stage stage = (Stage)((Node)hbox).getScene().getWindow();
    	stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,
    			event1->
    	{
    		saveToFile();
    	});
    }
    */
    /*
    public void loadFromFile()
    {
    	File f = getFile();
    	
    	if ( f != null)
    	{
    		try {
    			FileInputStream fi = new FileInputStream(f); 	
    			ObjectInputStream ois = new ObjectInputStream(fi);
    			hash = (HashMap<Date, String>)ois.readObject();
    			
    			ois.close();
    			fi.close();
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
    */
    /*
    public void saveToFile()
    {
    	File f = getFile();
    	if ( f != null)
    	{
    		try {
    			FileOutputStream fo = new FileOutputStream(f);
    			ObjectOutputStream out = new ObjectOutputStream(fo);
    			out.writeObject(hash);
    			out.close();
    			fo.close();
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    */
    /*
    private File getFile()
    {
    	FileChooser fc = new FileChooser();
    	fc.setTitle("Choose File: ");
    	fc.setInitialDirectory(new File("."));
    	return fc.showOpenDialog(null);
    }
    */
    @FXML
    void submitPressed(ActionEvent event) {
    	yearTitle.setText(yearC.getValue());
    	calendar.set(Integer.parseInt(yearTitle.getText()), extractMonthNumber(),1);
    	handleMonthVisibility();
    	
    }

	private void initCombobox()
	{
		yearC.setPrefWidth(80);
    	final int START_YEAR = 1970, END_YEAR = 2050 ;
    	for(int i = START_YEAR; i <= END_YEAR;i++)
    	{
    		yearC.getItems().add(i+ "");
    	}
    	yearC.setValue("2022");
	}
    
    
}
