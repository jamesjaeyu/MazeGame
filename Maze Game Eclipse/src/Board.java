
import java.util.ArrayList;

/**
 * The Board consists of multiple PlayingArea objects and contains the Hero
 * andArrayLists of Enemies and Coins. It has multiple methods to return data
 * back to World and GUI so they can process it and paint the updating Board.
 */
public class Board {
	private Hero hero;
	private ArrayList<Enemy> enemies;
	private ArrayList<Coin> coins;
	private ArrayList<Coin> saveCoins;
	private ArrayList<PlayingArea> area;
	private StartArea start;
	private EndArea end;
	private int levelNum;

	public Board(int level) {
		levelNum = level;
		area = new ArrayList<PlayingArea>();
		enemies = new ArrayList<Enemy>();
		coins = new ArrayList<Coin>();
		saveCoins = new ArrayList<Coin>();

		if (level == 1 || level == 5 || level == 9) {
			hero = new Hero(100, 220);
			start = new StartArea(30, 215, 240, 280);
			area.add(start);
			area.add(new PlayingArea(30, 215, 300, 280));
			area.add(new PlayingArea(265, 215, 1290, 775));
			area.add(new PlayingArea(1400, 630, 300, 360));
			coins.add(new Coin(400, 300));
			coins.add(new Coin(1300, 300));
			coins.add(new Coin(850, 600));
			coins.add(new Coin(400, 850));
			coins.add(new Coin(1300, 850));
			saveCoins.add(new Coin(400, 300));
			saveCoins.add(new Coin(1300, 300));
			saveCoins.add(new Coin(850, 600));
			saveCoins.add(new Coin(400, 850));
			saveCoins.add(new Coin(1300, 850));

			end = new EndArea(1550, 630, 250, 360);
			area.add(end);
		}

		if (level == 2 || level == 6 || level == 10) {
			hero = new Hero(100, 555);
			start = new StartArea(80, 380, 240, 410);
			area.add(start);
			area.add(new PlayingArea(80, 380, 500, 410));
			area.add(new PlayingArea(320, 240, 1340, 720));
			coins.add(new Coin(430, 300));
			coins.add(new Coin(950, 300));
			coins.add(new Coin(1530, 300));
			coins.add(new Coin(430, 850));
			coins.add(new Coin(950, 850));
			coins.add(new Coin(1530, 850));
			saveCoins.add(new Coin(430, 300));
			saveCoins.add(new Coin(950, 300));
			saveCoins.add(new Coin(1530, 300));
			saveCoins.add(new Coin(430, 850));
			saveCoins.add(new Coin(950, 850));
			saveCoins.add(new Coin(1530, 850));

			end = new EndArea(80, 380, 240, 410);
			area.add(end);
		}

		if (level == 3 || level == 7 || level == 11) {
			hero = new Hero(50, 350);
			start = new StartArea(15, 235, 160, 250);
			area.add(start);
			area.add(new PlayingArea(15, 235, 160, 250));
			area.add(new PlayingArea(130, 255, 1660, 215));
			area.add(new PlayingArea(1590, 255, 200, 665));
			area.add(new PlayingArea(100, 710, 1690, 210));
			coins.add(new Coin(850, 340));
			coins.add(new Coin(1670, 560));
			coins.add(new Coin(850, 800));
			saveCoins.add(new Coin(850, 340));
			saveCoins.add(new Coin(1670, 560));
			saveCoins.add(new Coin(850, 800));

			end = new EndArea(13, 690, 170, 250);
			area.add(end);
		}

		if (level == 4 || level == 8 || level == 12) {
			hero = new Hero(100, 550);
			start = new StartArea(75, 430, 230, 340);
			area.add(start);
			area.add(new PlayingArea(75, 430, 660, 340));
			area.add(new PlayingArea(513, 220, 225, 760));
			area.add(new PlayingArea(513, 220, 1210, 210));
			area.add(new PlayingArea(513, 770, 1210, 210));
			area.add(new PlayingArea(1510, 300, 215, 570));
			area.add(new PlayingArea(1510, 300, 215, 450));
			coins.add(new Coin(800, 300));
			coins.add(new Coin(1200, 300));
			coins.add(new Coin(1600, 300));
			coins.add(new Coin(800, 860));
			coins.add(new Coin(1200, 860));
			coins.add(new Coin(1600, 860));
			saveCoins.add(new Coin(800, 300));
			saveCoins.add(new Coin(1200, 300));
			saveCoins.add(new Coin(1600, 300));
			saveCoins.add(new Coin(800, 860));
			saveCoins.add(new Coin(1200, 860));
			saveCoins.add(new Coin(1600, 860));

			end = new EndArea(1510, 430, 215, 340);
			area.add(end);
		}
	}

	public void resetCoins() {
		coins = new ArrayList<Coin>();
		for (Coin c : saveCoins) {
			coins.add(c);
		}
	}

	public void clearEnemies() {
		for (int i = enemies.size() - 1; i > -1; i--) {
			enemies.remove(i);
		}
	}

	public ArrayList<PlayingArea> getPlayingAreas() {
		return area;
	}

	public EndArea getEnd() {
		return end;
	}

	public StartArea getStart() {
		return start;
	}

	public ArrayList<Coin> getCoins() {
		return coins;
	}

	public Hero getHero() {
		return hero;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	public void removeEnemy(int index) {
		enemies.remove(index);
	}

	public void generateEnemy() {
		int speed;
		if (levelNum >= 1 && levelNum <= 4) {
			speed = 7;
		} else if (levelNum >= 5 && levelNum <= 8) {
			speed = 10;
		} else {
			speed = 15;
		}

		int movement = (int) (Math.random() * 4) + 1;
		int xPos = 0;
		int yPos = 0;
		if (movement == 1) {
			xPos = -5;
			yPos = (int) (Math.random() * 500) + 100;
		} else if (movement == 2) {
			xPos = 1800;
			yPos = (int) (Math.random() * 500) + 100;
		} else if (movement == 3) {
			yPos = 1000;
			xPos = (int) (Math.random() * 1600) + 50;
		} else if (movement == 4) {
			yPos = 10;
			xPos = (int) (Math.random() * 1600) + 50;
		}
		enemies.add(new Enemy(xPos, yPos, movement, speed));
	}

	public boolean isInStart(Shape s) {
		if (start.isValid(s))
			return true;
		return false;
	}

	public boolean isInEnd(Shape s) {
		if (end.isValid(s))
			if (coins.size() == 0)
				return true;
		return false;
	}

	public void update() {
		Hero tempHero = new Hero(hero.getX() + hero.getVelX(), hero.getY() + hero.getVelY());
		if (isValid(tempHero)) {
			hero.update();
		}
	}

	public boolean isValid(Shape s) {
		for (PlayingArea a : area) {
			if (a.isValid(s))
				return true;
		}
		return false;
	}
}