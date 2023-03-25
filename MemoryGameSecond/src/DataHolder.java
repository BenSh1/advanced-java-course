
public final class DataHolder {

	private String host;
	private int port;
	private int score;
	private GameEndState state;
	private final static DataHolder INSTANCE = new DataHolder();

	private DataHolder() {
	}

	public static DataHolder getInstance() {
		return INSTANCE;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GameEndState getState() {
		return state;
	}

	public void setState(GameEndState state) {
		this.state = state;
	}

}
