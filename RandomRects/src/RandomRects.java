
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RandomRects extends Application{ 

	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("RandomRects.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("RandomRects"); 
		stage.setScene(scene); 
		stage.show(); 
	} 

	public static void main(String[] args) { 
		launch(args); 
		System.out.println();
	} 
}