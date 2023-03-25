import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ClientController {

    @FXML
    private TextField binary;

    @FXML
    private TextField number;

    @FXML
    void SendPressed(ActionEvent event) {
    	Data d = new Data(Integer.parseInt(number.getText()) , "");
    	new ClientThread( this , "127.0.0.1" , d).start();
    }

    public void setBinaryNum(String b)
    {
    	binary.setText(b);
    }
}
