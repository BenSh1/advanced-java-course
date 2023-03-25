import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndGameController {

	@FXML
	private Label msg;

	@FXML
	private Label score;

	@FXML
	void startNewGamePressed(ActionEvent event) {
		final int DEFAULT_PORT = 7777;
		final String DEFAULT_HOST = "localhost";
		DataHolder dh = DataHolder.getInstance();
		dh.setHost(DEFAULT_HOST);
		dh.setPort(DEFAULT_PORT);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("StartMenu.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setMsg(GameEndState state) {
		switch (state) {
		case WIN:
			this.msg.setText("Congratulations! You Win! 😃");
			break;
		case LOSE:
			this.msg.setText("Ohh, You Lost! ☹");
			break;
		case TIE:
			this.msg.setText("It's a Tie! 🙃");
			break;
		default:
			this.msg.setText("");
		}
	}

	public void initialize() {
		DataHolder dh = DataHolder.getInstance();
		int score = dh.getScore();
		GameEndState state = dh.getState();
		setMsg(state);
		this.score.setText(this.score.getText() + score);
	}

}
