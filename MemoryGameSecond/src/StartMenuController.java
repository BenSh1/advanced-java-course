import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartMenuController {

	@FXML
	private TextField computerName;

	@FXML
	private TextField port;

	private DataHolder dh;

	void computerNameChanged() {
		this.dh.setHost(computerName.getText());
	}

	@FXML
	void startGamePressed(ActionEvent event) {
		if (!validateDetails()) {
			return;
		}
		this.dh.setHost(computerName.getText());
		this.dh.setPort(Integer.parseInt(port.getText()));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("Board.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
				}

			});

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean validateDetails() {
		if (!computerName.getText().equals("localhost") && !computerName.getText().equals("LOCALHOST")) {
			final String NUMBERS_RANGE = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\\\d|25[0-5])";
			final String REGEX = NUMBERS_RANGE + "\\." + NUMBERS_RANGE + "\\." + NUMBERS_RANGE + "\\." + NUMBERS_RANGE;
			Pattern p = Pattern.compile(REGEX);
			Matcher m = p.matcher(computerName.getText());
			System.out.println(computerName.getText());
			System.out.println(dh.getHost());
			if (computerName.getText() == null || !m.matches()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid IP");
				alert.setContentText("IP address is not valid!");
				alert.showAndWait();
				return false;
			}
		}

		try {
			int port = Integer.parseInt(this.port.getText());
			if (port <= 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Number Format Error");
				alert.setContentText("Port should be a positive number!");
				alert.showAndWait();
				return false;
			}
			this.dh.setPort(port);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Number Format Error");
			alert.setContentText("Port is not a valid number!");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	public void initialize() {
		this.dh = DataHolder.getInstance();
		this.dh.setHost(computerName.getText());
		this.computerName.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				computerNameChanged();
			}
		});
	}

}
