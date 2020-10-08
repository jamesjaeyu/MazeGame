
import java.util.ArrayList;

/**
 * The World processes the user input and adjusts the Hero's location based on
 * it. It also compares the Hero's location to the Coins and Enemies in the
 * Board. If the Hero overlaps with a Coin, the Coin is 'collected' and the Hero
 * moves on. If the Hero overlaps with an Enemy, the Hero 'dies' and the level
 * resets.
 */
public class World {
	private Board board;
	private Hero hero;
	private ArrayList<Enemy> enemies;
	private ArrayList<Coin> coins;
	private boolean gameOver, end, isLeftPressed, isRightPressed, isUpPressed, isDownPressed;
	private final int MOVE = 10;

	public World(int level) {
		board = new Board(level);
		hero = board.getHero();
		enemies = board.getEnemies();
		coins = board.getCoins();
		gameOver = false;
		end = false;
	}

	public Board getBoard() {
		return board;
	}

	public void setLeft(boolean l) {
		isLeftPressed = l;
		changeSpeed();
	}

	public void setRight(boolean r) {
		isRightPressed = r;
		changeSpeed();
	}

	public void setUp(boolean u) {
		isUpPressed = u;
		changeSpeed();
	}

	public void setDown(boolean d) {
		isDownPressed = d;
		changeSpeed();
	}

	public void updateCoins(ArrayList<Coin> next) {
		coins = next;
	}

	private void changeSpeed() {
		hero.setVelX(0);
		hero.setVelY(0);

		if (isLeftPressed)
			hero.setVelX(-MOVE);

		if (isRightPressed)
			hero.setVelX(MOVE);

		if (isUpPressed)
			hero.setVelY(-MOVE);

		if (isDownPressed)
			hero.setVelY(MOVE);
	}

	public boolean inStartArea() {
		if (board.isInStart(hero))
			return true;
		return false;

	}

	public void compareLocationEnemy() {
		int heroX = hero.getX() + 25;
		int heroY = hero.getY() + 25;

		for (Enemy e : enemies) {
			if (Math.abs(e.getX() + 50 - heroX) < 75)
				if (Math.abs(e.getY() + 50 - heroY) < 75)
					gameOver = true;
		}

	}

	public void compareLocationCoin() {
		int heroX = hero.getX() + 25;
		int heroY = hero.getY() + 25;

		for (int i = coins.size() - 1; i >= 0; i--) {
			if (Math.abs(coins.get(i).getX() + 17 - heroX) < 35)
				if (Math.abs(coins.get(i).getY() + 17 - heroY) < 35)
					coins.remove(i);
		}
	}

	public void compareLocationBorder() {
		if (isLeftPressed)
			hero.setVelX(-MOVE);

		if (isRightPressed)
			hero.setVelX(MOVE);

		if (isUpPressed)
			hero.setVelY(-MOVE);

		if (isDownPressed)
			hero.setVelY(MOVE);
	}

	public boolean inEnd() {
		if (board.isInEnd(hero))
			return true;
		else
			return false;
	}

	public void processInteractions() {
		compareLocationBorder();
		compareLocationEnemy();
		if (inStartArea())
			gameOver = false;
		compareLocationCoin();
		if (inEnd() && coins.size() == 0)
			end = true;
	}

	public Hero getHero() {
		return hero;
	}

	public boolean gameOver() {
		return gameOver;
	}

	public void setGame() {
		gameOver = false;
	}

	public boolean getEnd() {
		return end;
	}
}