
import java.util.ArrayList;
import java.awt.*; // access to Container
import java.awt.event.*; // access to WindowAdapter, WindowEvent
import java.awt.image.BufferedImage; //access to BufferedImage for double buffering
import javax.swing.*; // access to JFrame and Jcomponents
import javax.swing.Timer;

import java.util.*;

/**
 * The GUI paints the Hero, Enemies, Coins, PlayingAreas, and EndArea. It
 * creates a KeyListener which passes on the input to the World class, where it
 * will be processed.
 */

public class GUI extends JFrame {
	private JFrame frame;
	private Image realHero, realCoin, realEnemy, realMap, realBlackBar;

	private int deathCount;
	private World world;
	private Board board;
	private ArrayList<PlayingArea> area;
	private Hero hero;
	private ArrayList<Enemy> enemies;
	private ArrayList<Coin> coins;
	private int lev;
	private Sound music; // !< background music player

	public GUI(int level, int deaths) {
		setFocusable(true);
		frame = new JFrame("Maze Game");
		requestFocus();
		setResizable(false);
		deathCount = deaths;
		lev = level;
		world = new World(level);
		board = world.getBoard();
		hero = board.getHero();
		enemies = board.getEnemies();
		coins = board.getCoins();

		addKeyListener(new KeyHandler());

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ClassLoader loader = this.getClass().getClassLoader();
		ImageIcon hero = new ImageIcon(loader.getResource("FinalPlayer.png"));
		ImageIcon enemy = new ImageIcon(loader.getResource("FinalEnemy.png"));
		ImageIcon coin = new ImageIcon(loader.getResource("FinalCoin.png"));
		ImageIcon map = new ImageIcon(loader.getResource("newMap1.png"));
		ImageIcon blackBar = new ImageIcon(loader.getResource("blackbar.png"));

		if (level == 1 || level == 5 || level == 9)
			map = new ImageIcon(loader.getResource("newMap1.png"));
		if (level == 2 || level == 6 || level == 10)
			map = new ImageIcon(loader.getResource("newMap2.png"));
		if (level == 3 || level == 7 || level == 11)
			map = new ImageIcon(loader.getResource("newMap3.png"));
		if (level == 4 || level == 8 || level == 12)
			map = new ImageIcon(loader.getResource("newMap4.png"));

		realMap = map.getImage();

		realHero = hero.getImage();
		realEnemy = enemy.getImage();
		realCoin = coin.getImage();
		realBlackBar = blackBar.getImage();

		setSize(1920, 1035);
		setVisible(true);

		// Music
		music = new Sound("The Impossible Game-Level 3-Envy-Heaven.wav");
		music.playBackgroundMusic();
	}

	public int getLevel() {
		return lev;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public World getWorld() {
		return world;
	}

	public boolean end() {
		if (world.inEnd()) {
			music.stopBackgroundMusic();
		}
		return world.inEnd();
	}

	public void resetCoins() {
		board.clearEnemies();
		board.resetCoins();
		coins = board.getCoins();
		world.updateCoins(coins);
	}

	public int getDeaths() {
		return deathCount;
	}

	public void paint(Graphics g) {
		Image offImage = createImage(1920, 1035);
		// Creates an off-screen drawable image to be used for
		// double buffering; XSIZE, YSIZE are each of type �int�;
		// represents size of JFrame or JPanel, etc
		Graphics buffer = offImage.getGraphics();
		// Creates a graphics context for drawing to an
		// off-screen image
		paintOffScreen(buffer); // your own method
		g.drawImage(offImage, 0, 0, null);
		// draws the image with upper left corner at 0,0
	}

	public void paintOffScreen(Graphics g) {
		// double buffering
		BufferedImage bufferedImage = new BufferedImage(1920, 1035, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = bufferedImage.createGraphics();
		Font stringFont = new Font("Showcard Gothic", Font.PLAIN, 18);
		// draw white background
		g2d.setColor(Color.WHITE);
		g2d.drawRect(0, 0, 1920, 1035);
		g2d.fillRect(0, 0, 1920, 1035);

		g2d.drawImage(realBlackBar, 0, 20, this);
		g2d.drawImage(realBlackBar, 960, 20, this);

		// draw map
		g2d.drawImage(realMap, 5, 200, this);

		// draw coins
		for (Coin c : coins) {
			g2d.drawImage(realCoin, c.getX(), c.getY(), this);
		}
		// draw enemies and remove if not on screen
		boolean remove = false;
		for (int i = 0; i < enemies.size(); i++) {
			remove = enemies.get(i).update();
			if (remove) {
				board.removeEnemy(i);
			} else {
				g2d.drawImage(realEnemy, enemies.get(i).getX(), enemies.get(i).getY(), this);
			}
		}

		// update hero's location
		board.update();
		// draw hero
		g2d.drawImage(realHero, hero.getX(), hero.getY(), this);

		// draw black bar on top of screen and display informatio
		// g2d.drawImage(realBlackBar, 500, 500, this);
		g2d.setFont(stringFont);
		g2d.setColor(Color.WHITE);
		// display if the hero is in the end area
		if (world.getEnd()) {
			g2d.drawString("In the End Area", 1300, 50);
		} else {
			g2d.drawString("Not in the End Area", 1300, 50);
		}

		// display the amount of coins left to collect
		g2d.drawString("Number of coins left: " + coins.size(), 1550, 50);
		g2d.drawString("Level " + lev, 1100, 50);

		// display death count
		String d = "Deaths: " + deathCount;
		g2d.drawString(d, 1800, 50);

		// double buffering
		Graphics2D g2dComponent = (Graphics2D) g;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);
	}

	public void incrementDeath() {
		deathCount++;
	}

	private class KeyHandler implements KeyListener {

		/** Stores currently pressed keys */
		HashSet<Integer> pressedKeys = new HashSet<Integer>();

		public KeyHandler() {

			// Check every 100ms if there's keys pressed
			// (This is the Swing Timer they talk about)
			new Timer(1, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
				}
			}).start();
		}

		public void keyPressed(KeyEvent event) {
			if (event.getKeyChar() == 'a')
				world.setLeft(true);

			if (event.getKeyChar() == 'd')
				world.setRight(true);

			if (event.getKeyChar() == 'w')
				world.setUp(true);

			if (event.getKeyChar() == 's')
				world.setDown(true);
		}

		public void keyReleased(KeyEvent event) {
			if (event.getKeyChar() == 'a')
				world.setLeft(false);

			if (event.getKeyChar() == 'd')
				world.setRight(false);

			if (event.getKeyChar() == 'w')
				world.setUp(false);

			if (event.getKeyChar() == 's')
				world.setDown(false);
		}

		public void keyTyped(KeyEvent event) {

		}
	}

}