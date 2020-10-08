
/**
 * Enemies move randomly across the screen independent of the user's input.
 */
public class Enemy extends Shape {
	private int movement;
	private final int DISPLACEMENT;

	public Enemy(int x1, int y1, int move, int speed) {
		super(x1, y1);
		movement = move;
		DISPLACEMENT = speed;
	}

	public boolean update() {
		if (movement == 1) // enemy moves to the right
			setX(getX() + DISPLACEMENT);
		else if (movement == 2) // enemy moves to the left
			setX(getX() - DISPLACEMENT);
		else if (movement == 3) // enemy moves up
			setY(getY() - DISPLACEMENT);
		else if (movement == 4)
			setY(getY() + DISPLACEMENT);
		else if (movement == 0) {

		}

		if (getX() < -10 || getX() > 1920)
			return true;
		if (getY() < -10 || getY() > 1035)
			return true;

		return false;
	}
}