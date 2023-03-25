import java.util.Random;

public class Game {
	private int boardSize;
	private String[][] board;
	private boolean[][] cardsState;

	public Game(int size) {
		boardSize = size;
		board = new String[boardSize][boardSize];
		cardsState = new boolean[boardSize][boardSize];
		this.createBoard(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cardsState[i][j] = false;
			}
		}
	}

	private void createBoard(int size) {
		int cardIndex = 1;

		// initialize board.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = "file:pngs/" + cardIndex + ".png";
				cardIndex++;
				if (cardIndex > (Math.pow(size, 2) / 2)) {
					cardIndex = 1;
				}
			}
		}

		// shuffle the board
		Random random = new Random();
		for (int i = size - 1; i > 0; i--) {
			for (int j = size - 1; j > 0; j--) {
				int m = random.nextInt(i + 1);
				int n = random.nextInt(j + 1);

				String temp = board[i][j];
				board[i][j] = board[m][n];
				board[m][n] = temp;
			}
		}
	}

	public void setFlipCard(int i, int j) {
		cardsState[i][j] = true;
	}

	public boolean checkTurnChoices(int[] a, int[] b) {
		if (board[a[0]][a[1]].equals(board[b[0]][b[1]])) {
			return true;
		} else {
			cardsState[a[0]][a[1]] = false;
			cardsState[b[0]][b[1]] = false;
			return false;
		}
	}

	public boolean[][] getCardsStates() {
		return cardsState;
	}

	public String[][] getBoard() {
		return board;
	}

	public boolean isGameOver() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (!cardsState[i][j])
					return false;
			}
		}
		return true;
	}
}
