import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ManageLibraryController {

    @FXML
    private AnchorPane pane;
	
    @FXML
    private TextArea additionalInfo;

    @FXML
    private ListView<String> bookList;

    @FXML
    private TextArea nameAuthor;

    @FXML
    private TextArea nameBook;
    
    private HashMap<String, Details> hash;
    
    @FXML
    public void initialize() {
    	hash = new HashMap<String, Details>();

    	loadFromFile();
    	
    	
    	bookList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends String> arg0 ,String arg1 ,String arg2) {
    			String currentBook = bookList.getSelectionModel().getSelectedItem();
    			System.out.println(currentBook);
    			
    			Details d = ((Details)hash.get(currentBook));
    			nameBook.setText(d.getNameOfBook());
    			nameAuthor.setText(d.getNameOfAuthor());
    			additionalInfo.setText(d.getAdditionalInfo());

    		}
		});
    	
    	
    }


    @FXML
    void addPressed(ActionEvent event) {
    	bookList.getItems().add(nameBook.getText());
    	

    	Details d = new Details(nameBook.getText(), nameAuthor.getText(), additionalInfo.getText());
    	hash.put(nameBook.getText(), d);
    	
    	addClosingEvent();
    }
    

    @FXML
    void clearPressed(ActionEvent event) {
    	additionalInfo.setText("");
    	nameBook.setText("");
    	nameAuthor.setText("");

    }
    
    public void loadFromFile() {
    	File file = getFile();

		FileInputStream fi;
		try {
			fi = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fi);
			hash = (HashMap<String, Details>)ois.readObject();
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
    private void addClosingEvent()
    {
    	Stage stage = (Stage)((Node)pane).getScene().getWindow();
    	stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,
    			event1->
    	{
    		saveToFile();
    	});
    }
    
    public void saveToFile() {
		File file = getFile();

		FileOutputStream fo;
		try {
			fo = new FileOutputStream(file);
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
    
    private File getFile()
    {
    	FileChooser file = new FileChooser();
    	file.setTitle("select a file");
    	file.setInitialDirectory(new File("."));
    	return file.showOpenDialog(null);
    }
}
