import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class BoardController implements Runnable {

	@FXML
	private GridPane board;

	@FXML
	private TextArea messages;

	@FXML
	private AnchorPane pane;

	private GameLogic logic;

	private void initializeBoard(int size) {
		for (int i = 0; i < size - 1; i++) {
			RowConstraints row = new RowConstraints();
			row.setFillHeight(true);
			this.board.getRowConstraints().add(row);

		}

		for (int i = 0; i < size - 1; i++) {
			ColumnConstraints col = new ColumnConstraints();
			col.setFillWidth(true);
			this.board.getColumnConstraints().add(col);
		}

		for (int i = 0; i < this.board.getColumnConstraints().size(); i++) {
			this.board.getColumnConstraints().get(i).setPrefWidth(board.getPrefWidth() / size);
			this.board.getColumnConstraints().get(i).setFillWidth(true);
		}

		for (int i = 0; i < this.board.getRowConstraints().size(); i++) {
			this.board.getRowConstraints().get(i).setPrefHeight(board.getPrefHeight() / size);
			this.board.getRowConstraints().get(i).setFillHeight(true);
		}
	}

	private void showEndGameView() {
		int score = this.logic.getScore();
		DataHolder dh = DataHolder.getInstance();
		dh.setScore(score);
		GameEndState endView = this.logic.getGameEndState();
		dh.setState(endView);
		if (endView == GameEndState.END_WITH_ERROR) {
			showErrorAlert(this.logic.getErrorMsg());
		} else {
			if (dh.getState() != null) {
				Stage stage = (Stage) this.messages.getScene().getWindow();
				stage.close();
				try {

					Parent root = (Parent) FXMLLoader.load(getClass().getClassLoader().getResource("EndGame.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void showErrorAlert(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Connection Failed!");
		alert.setContentText(msg);
		alert.showAndWait();
		if (logic != null) {
			logic.setErrorMsg("");
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Stage stage = (Stage) pane.getScene().getWindow();
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
		});

	}

	public void initialize() {
		try {
			this.logic = new GameLogic(board.getPrefWidth(), board.getPrefHeight());
		} catch (IOException e) {
			this.showErrorAlert("Failed to connect the server.");
		}

		if (this.logic != null) {
			int size = this.logic.getBoardSize();
			initializeBoard(size);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					this.board.add(this.logic.getCard(i, j), i, j);
				}
			}
			new Thread(null, this).start();
		}

	}

	@Override
	public void run() {
		while (!this.logic.isGameOver()) {
			this.logic.loop();
			this.messages.setText(this.logic.getMessages());
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				showEndGameView();
			}
		});

	}
}
