
/**
 * PlayingArea is the area in which the Hero can move around, collect coins, and
 * dodge enemies. The Hero must move in a PlayingArea.
 */
public class PlayingArea {
	private int xPos;
	private int yPos;
	private int height;
	private int width;

	public PlayingArea(int x, int y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isValid(Shape s) {
		if (s.getX() >= xPos && s.getX() <= xPos + width - 50) {
			if (s.getY() >= yPos && s.getY() <= yPos + height - 50)
				return true;
		}
		return false;
	}
}