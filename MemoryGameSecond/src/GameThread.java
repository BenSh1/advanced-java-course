import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.Timer;

public class GameThread extends Thread {
	private int boardSize;
	private Socket socketPlayer1;
	private Socket socketPlayer2;
	private ObjectInputStream inputPlayer1;
	private ObjectInputStream inputPlayer2;
	private ObjectOutputStream outputPlayer1;
	private ObjectOutputStream outputPlayer2;
	private boolean isFirstCard;
	private boolean isFirstPlayer;
	private Timer timer;
	private int scorePlayer1;
	private int scorePlayer2;
	private Game game;
	private Object input;
	private int[] temp;
	private boolean isError = false;

	public GameThread(Socket player1, Socket player2, int boardSize) {
		final int INTERVAL = 3000;
		this.isFirstCard = true;
		this.isFirstPlayer = true;
		this.boardSize = boardSize;
		game = new Game(this.boardSize);

		timer = new Timer(INTERVAL, new TimerListener());

		this.socketPlayer1 = player1;
		this.socketPlayer2 = player2;

		try {
			this.outputPlayer1 = new ObjectOutputStream(player1.getOutputStream());
			this.outputPlayer2 = new ObjectOutputStream(player2.getOutputStream());
			this.outputPlayer1.flush();
			this.outputPlayer2.flush();
			this.inputPlayer1 = new ObjectInputStream(player1.getInputStream());
			this.inputPlayer2 = new ObjectInputStream(player2.getInputStream());
		} catch (IOException e) {
			System.out.println("couldn't create connections");
			return;
		}

		this.scorePlayer1 = 0;
		this.scorePlayer2 = 0;

		try {
			this.outputPlayer1.writeObject(boardSize);
			this.outputPlayer1.writeObject("Wait for second player");
			this.outputPlayer2.writeObject(boardSize);
			this.outputPlayer1.writeObject(game.getBoard());
			this.outputPlayer2.writeObject(game.getBoard());
			this.outputPlayer1.writeObject("Game Started");
			this.outputPlayer2.writeObject("Game Started");
			this.outputPlayer1.writeObject("Your Turn");
			this.outputPlayer2.writeObject("Opponent's Turn");
			this.outputPlayer1.writeObject(game.getCardsStates());
			this.outputPlayer2.writeObject(game.getCardsStates());
		} catch (IOException e) {
			System.out.println("Couldn't send game to start with..");
			return;
		}
	}

	@Override
	public void run() {
		while (!game.isGameOver() && !isError) {
			if (this.isFirstPlayer) {

				try {
					this.input = inputPlayer1.readObject();
				} catch (IOException e) {
					try {
						outputPlayer2.writeObject("Error: Something went wrong with your Opponent.");
						this.isFirstPlayer = false;
						this.isError = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} catch (ClassNotFoundException e) {
					try {
						outputPlayer2.writeObject("Error: Something went wrong with your Opponent.");
						this.isFirstPlayer = false;
						this.isError = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				processTurn(input);

			} else {
				try {
					this.input = this.inputPlayer2.readObject();
				} catch (IOException e) {
					try {
						outputPlayer1.writeObject("Error: Something went wrong with your Opponent.");
						this.isFirstPlayer = true;
						this.isError = true;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					try {
						outputPlayer1.writeObject("Error: Something went wrong with your Opponent.");
						this.isFirstPlayer = true;
						this.isError = true;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				processTurn(input);
			}
		}

		try {

			if (this.scorePlayer1 == this.scorePlayer2) {
				this.outputPlayer1.writeObject("Tie!");
				this.outputPlayer2.writeObject("Tie!");
			} else if (this.scorePlayer1 > this.scorePlayer2) {
				this.outputPlayer1.writeObject("You Won!:)");
				this.outputPlayer2.writeObject("You lost!:(");
			} else {
				this.outputPlayer2.writeObject("You Won!:)");
				this.outputPlayer1.writeObject("You lost!:(");
			}

			this.outputPlayer1.writeObject("Game Over");
			this.outputPlayer2.writeObject("Game Over");
			this.outputPlayer1.writeObject(this.scorePlayer1);
			this.outputPlayer2.writeObject(this.scorePlayer2);

			this.inputPlayer1.close();
			this.inputPlayer2.close();
			this.outputPlayer1.close();
			this.outputPlayer2.close();
			this.socketPlayer1.close();
			this.socketPlayer2.close();
		} catch (IOException e) {
			System.out.println("couldn't close connections to clients");
			return;
		}

	}

	private void sendError(String error) {
		try {
			this.outputPlayer1.writeObject("Something went wrong with the game :( \n Error: " + error);
			this.outputPlayer2.writeObject("Something went wrong with the game :(  \n Error: " + error);
		} catch (IOException e) {
			System.out.println("error to write clients");
		}

	}

	private void processTurn(Object input) {
		if (input instanceof int[]) {
			if (this.isFirstCard) {
				temp = (int[]) input;
				setFlipCard(temp[0], temp[1]);
				// switch to second card
				this.isFirstCard = false;
			} else {
				setFlipCard(((int[]) input)[0], ((int[]) input)[1]);
				isFirstCard = true;

				if (game.checkTurnChoices(temp, (int[]) input)) {

					if (this.isFirstPlayer) {
						this.scorePlayer1++;
						try {
							this.outputPlayer1.writeObject("Its a match! :)");
						} catch (IOException e) {
							sendError("Couldn't send congratsulation :(");
							return;
						}
					} else {
						this.scorePlayer2++;
						try {
							this.outputPlayer2.writeObject("Its a match! :)");
						} catch (IOException e) {
							sendError("Couldn't send congratsulation :(");
							return;
						}
					}
				} else {
					timer.start();
				}

				if (this.isFirstPlayer) {
					this.isFirstPlayer = false;
					try {
						this.outputPlayer1.writeObject("Opponent's Turn");
						this.outputPlayer2.writeObject("Your Turn");
					} catch (IOException e) {
						sendError("couldn't switch turns");
						return;
					}
				} else {
					this.isFirstPlayer = true;
					try {
						this.outputPlayer2.writeObject("Opponent's Turn");
						this.outputPlayer1.writeObject("Your Turn");
					} catch (IOException e) {
						sendError("couldn't switch turns");
						return;
					}
				}
			}
		}
	}

	private void setFlipCard(int a, int b) {
		game.setFlipCard(a, b);
		boolean[][] t = new boolean[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				t[i][j] = this.game.getCardsStates()[i][j];

		try {
			this.outputPlayer1.writeObject(t);
			this.outputPlayer2.writeObject(t);

		} catch (IOException e1) {
			sendError("Partner Disconnect");
			return;
		}
	}

	public class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean[][] t = new boolean[boardSize][boardSize];
			for (int i = 0; i < boardSize; i++)
				for (int j = 0; j < boardSize; j++)
					t[i][j] = game.getCardsStates()[i][j];
			try {
				outputPlayer1.writeObject(t);
				outputPlayer2.writeObject(t);
			} catch (IOException e1) {
				sendError("Couldn't send from timer!");
				return;
			}
			timer.stop();
		}
	}
}
