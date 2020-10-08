
/**
 * Shape is the superclass for Hero, Enemy, and Coin.
 */
public class Shape {
	private int x, y;

	public Shape() {
		x = 0;
		y = 0;
	}

	public Shape(int x1, int y2) {
		x = x1;
		y = y2;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x2) {
		x = x2;
	}

	public void setY(int y2) {
		y = y2;
	}
}