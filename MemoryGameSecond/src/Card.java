import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends Button {
	private ImageView view;
	private boolean isFliped;
	private String photoPath;
	private int posX;
	private int posY;
	private final String COVER_PATH = "file:pngs/cover.png";

	public Card(String path, int posX, int posY, double width, double height) {
		this.isFliped = false;
		this.photoPath = path;
		this.posX = posX;
		this.posY = posY;
		final String STYLE = "-fx-background-color: #ffffff;";
		this.setStyle(STYLE);
		this.view = new ImageView(new Image(COVER_PATH));
		view.setFitHeight(height);
		view.setFitWidth(width);
		this.setGraphic(view);
	}

	public void flip() {
		String pathToFlip = "";
		if (!this.isFliped()) {
			pathToFlip = photoPath;
		} else {
			pathToFlip = COVER_PATH;

		}
		this.isFliped = !this.isFliped;
		view.setImage(new Image(pathToFlip));
		this.setDisable(isFliped);
	}

	public boolean isFliped() {
		return isFliped;
	}

	public void setFliped(boolean isFliped) {
		this.isFliped = isFliped;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
