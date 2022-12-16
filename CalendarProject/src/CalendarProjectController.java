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

    
    //private Button[] btns;
    private Button[] btnsDays;
    final int SIZE = 5;

    final int COLUMNS = 7;
    final int ROWS = 6;

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
    	calendar = Calendar.getInstance();
    	
    	btnsDays = new Button[ROWS*ROWS];
    	double w = grid.getPrefWidth()/ROWS;
    	double h = grid.getPrefHeight()/ROWS; 
    	 
    	for(int i =0;i<31;i++)
    	{
    		btnsDays[i] = new Button((i+1) + "");
    		btnsDays[i].setPrefSize(w,h);
    		grid.add(btnsDays[i] ,i % ROWS , i / ROWS);
    		
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
        	btnsMonths[i].setPrefWidth(80);
        	btnsMonths[i].setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					handleButtonMonths(event);
				}
			});
        }
    	
    	initCombobox();
    	loadFromFile();
    	//default
    	yearTitle.setText("2022");
    	monthTitle.setText("JANUARY");
    }
    
    
    private void handleButtonMonths(ActionEvent event) {
    	Button b = (Button)event.getSource();
    	monthTitle.setText(b.getText());
    	if(b.getText().equals("January") || b.getText().equals("March")|| b.getText().equals("May")
    			|| b.getText().equals("July")|| b.getText().equals("August")|| b.getText().equals("October")
    			|| b.getText().equals("December"))
    	{
    		btnsDays[30].setVisible(true);
    		btnsDays[29].setVisible(true);
    		
    	}
    	else if(b.getText().equals("April") || b.getText().equals("March")|| b.getText().equals("June")
    			|| b.getText().equals("September")|| b.getText().equals("November"))
    	{
    		btnsDays[30].setVisible(false);

    	}
    	else if(b.getText().equals("February"))
    	{
    		btnsDays[30].setVisible(false);
    		btnsDays[29].setVisible(false);
    	}


    	
    }
    
    private void handleButton(ActionEvent event) {
    	Button b = (Button)event.getSource();
    	dayC = Integer.parseInt(b.getText());
    	
    	b.setStyle("-fx-base: lightgreen");
    	PauseTransition pause = new PauseTransition(
    	    Duration.seconds(2)
    	);
    	pause.setOnFinished(event1 -> 
    	{
    	    b.setStyle(null);
    	}
    	);
    	pause.play();    
    	
    	pickDay.setText(dayC + "");
    	
    	int currentMonthNum = 0;
    	while(currentMonthNum < 12 && !(months[currentMonthNum].equals(monthTitle.getText())))
    	{
    		currentMonthNum++;
    	}
    	Date d = new Date(Integer.parseInt(yearTitle.getText()) , currentMonthNum,dayC);

    	JOptionPane.showMessageDialog(null, hash.get(d));
    }
    

    @FXML
    void addPressed(ActionEvent event) {
    	if(pickDay.getText().equals(""))
    	{
    		JOptionPane.showMessageDialog(null, "You must choose the day","Error",JOptionPane.ERROR_MESSAGE);
    	}
    	
    	
    	int currentMonthNum = 0;
    	while(currentMonthNum < 12 && !(months[currentMonthNum].equals(monthTitle.getText())))
    	{
    		currentMonthNum++;
    	}
    	/*Calendar c = Calendar.getInstance();
    	c.set(Integer.parseInt(yearTitle.getText()) , currentMonthNum,dayC);*/
    	calendar.set(Integer.parseInt(yearTitle.getText()) , currentMonthNum,dayC);
    	Date d = new Date(Integer.parseInt(yearTitle.getText()) , currentMonthNum,dayC);
    	hash.put(d, textArea.getText());

    	//hash.put(Calendar, textArea.getText());
    	textArea.setText("");
    	addClosingEvent();
    	System.out.println("add");


    }


    @FXML
    void showPressed(ActionEvent event) {
    	//Date d = new Date(, ROWS, COLUMNS)
    	//Calendar c = new Calendar(yearTitle.getText()  ,monthTitle.getText() ,10 );
    	if(pickDay.getText().equals(""))
    	{
    		JOptionPane.showMessageDialog(null, "You must choose the day","Error",JOptionPane.ERROR_MESSAGE);
    	}
    	
    	int currentMonthNum = 0;
    	while(currentMonthNum < 12 && !(months[currentMonthNum].equals(monthTitle.getText())))
    	{
    		currentMonthNum++;
    	}
    	
    	calendar.set(Integer.parseInt(yearTitle.getText()) , currentMonthNum,dayC);
    	/*Calendar c = Calendar.getInstance();
    	c.set(Integer.parseInt(yearTitle.getText()) , currentMonthNum,dayC);*/
    	Date d = new Date(Integer.parseInt(yearTitle.getText()) , currentMonthNum,dayC);

    	
    	//textArea.setText(hash.get(Date));
    	textArea.setText(hash.get(d));	
    	System.out.println("show");
    }
    
    private void addClosingEvent()
    {
    	Stage stage = (Stage)((Node)hbox).getScene().getWindow();
    	stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,
    			event1->
    	{
    		saveToFile();
    	});
    }
    
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
    
    private File getFile()
    {
    	FileChooser fc = new FileChooser();
    	fc.setTitle("Choose File: ");
    	fc.setInitialDirectory(new File("."));
    	return fc.showOpenDialog(null);
    }
    
    @FXML
    void submitPressed(ActionEvent event) {
    	yearTitle.setText(yearC.getValue());
    }

	private void initCombobox()
	{
    	final int START_YEAR = 2020, END_YEAR = 2022 ;
    	for(int i = START_YEAR; i <= END_YEAR;i++)
    	{
    		yearC.getItems().add(i+ "");
    	}
    	yearC.setValue("2022");
	}
    
    
}
