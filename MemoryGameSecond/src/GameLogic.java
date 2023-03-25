import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameLogic {
	private boolean myTurn;
	private boolean isGameOver;
	private String messages;
	private Card[][] cards;
	private double cardWidth;
	private double cardHeight;
	private int boardSize;
	private Communication comm;
	private int score;
	private GameEndState gameEndState;
	private String errorMsg;

	public GameLogic(double boardWidth, double boardHeight) throws ConnectException, UnknownHostException, IOException {
		this.initialize(boardWidth, boardHeight);
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public Card getCard(int i, int j) {
		return this.cards[i][j];
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GameEndState getGameEndState() {
		return gameEndState;
	}

	public void setGameEndState(GameEndState gameEndState) {
		this.gameEndState = gameEndState;
	}

	public void loop() {
		Object input = this.comm.readObject();
		this.parseInput(input);
	}

	public void disconnect() {
		System.out.println("Send Disconnect");
		this.comm.disconnect();
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private void initialize(double boardWidth, double boardHeight)
			throws ConnectException, UnknownHostException, IOException {
		DataHolder dh = DataHolder.getInstance();
		String serverName = dh.getHost();
		int port = dh.getPort();

		this.comm = new Communication(serverName, port);
		boardSize = (int) this.comm.readObject();
		this.cardWidth = boardWidth / boardSize;
		this.cardHeight = boardHeight / boardSize;
		this.cards = new Card[boardSize][boardSize];
		this.isGameOver = false;
		this.messages = "";

		// wait for board
		Object input = this.comm.readObject();
		while (!(input instanceof String[][])) {
			input = this.comm.readObject();
		}
		String[][] board = (String[][]) input;
		for (int i = 0; i < board[0].length; i++) {
			for (int j = 0; j < board.length; j++) {
				cards[i][j] = new Card(board[i][j], i, j, cardWidth, cardHeight);
				cards[i][j].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						flipCard((Card) event.getSource());
					}

				});
			}
		}
	}

	private void flipCard(Card card) {
		if (myTurn) {
			int[] cardPos = new int[2];
			cardPos[0] = card.getPosX();
			cardPos[1] = card.getPosY();
			card.flip();
			this.comm.writeObject(cardPos);
		}
	}

	private void parseInput(Object input) {
		if (input instanceof String) {
			String msg = null;
			msg = (String) input;
			if (msg.contains("Error")) {
				this.setGameOver(true);
				this.setGameEndState(GameEndState.END_WITH_ERROR);
				this.setErrorMsg(msg);
				return;
			}
			switch (msg) {
			case "Game Over":
				this.setScore((int) this.comm.readObject());
				this.setGameOver(true);
				break;
			case "Wait for second player":
				this.setMessages(messages + msg + '\n');
				break;
			case "Game Started":
				this.setMessages(messages + msg + '\n');
				break;
			case "Your Turn":
				this.setMyTurn(true);
				this.setMessages(messages + msg + '\n');
				break;
			case "Opponent's Turn":
				this.setMyTurn(false);
				this.setMessages(messages + msg + '\n');
				break;
			case "Tie!":
				this.setGameEndState(GameEndState.TIE);
				break;
			case "You Won!:)":
				this.setGameEndState(GameEndState.WIN);
				break;
			case "You lost!:(":
				this.setGameEndState(GameEndState.LOSE);
				break;
			default:
				this.setMessages(messages + msg + '\n');
			}
		} else if (input instanceof boolean[][]) {
			boolean[][] states = (boolean[][]) input;
			for (int i = 0; i < states[0].length; i++) {
				for (int j = 0; j < states.length; j++) {
					if (cards[i][j].isFliped() != states[i][j]) {
						cards[i][j].flip();
					}
				}
			}
		}
	}
}
