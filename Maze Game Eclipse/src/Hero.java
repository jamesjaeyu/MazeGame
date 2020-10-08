
/**
 * Hero is the object the user controls.
 */
public class Hero extends Shape {
	private int velX, velY;
	private int startX, startY;

	public Hero(int x1, int y1) {
		super(x1, y1);
		startX = x1;
		startY = y1;
		velX = 0;
		velY = 0;
	}

	public void update() {
		setX(getX() + velX);
		setY(getY() + velY);
	}

	public int getVelX() {
		return velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelX(int vX) {
		velX = vX;
	}

	public void setVelY(int vY) {
		velY = vY;
	}

	public void setStart() {
		setX(startX);
		setY(startY);
	}

	public void bounce() {
		if (velX < 0)
			velX = 3;
		if (velX > 0)
			velX = -3;

		update();
	}
}