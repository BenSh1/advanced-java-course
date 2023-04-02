
public class Ball {
	private int x, y;
	private int dx;
	private int dy;
	public Ball(int x,int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		if(Math.random() > 0.5)
			dx = 1;
		else
			dx = -1;
		
		if(Math.random() > 0.5)
			dy = 1;
		else
			dy = -1;
	}
	
	public void move() {
		x+= dx;
		y+= dy;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	
	
}
