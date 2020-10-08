
/**
 * TestThread creates a timer that allows for the enemies to move across the
 * screen without needing user input.
 * https://www.tutorialspoint.com/java/java_multithreading.htm
 */
public class TestThread implements Runnable {
	private Thread t;
	private String threadName;
	private GUI gui;
	private World world;
	private Board board;
	private Hero hero;
	private int level;

	public TestThread(String name, GUI g) {
		threadName = name;
		gui = g;
		level = gui.getLevel();
		world = gui.getWorld();
		board = world.getBoard();
		hero = board.getHero();
	}

	public void run() {
		try {
			int status = 0;
			int enemy_timer = 1;
			int spawnRate = 60;

			while (status == 0) {
				Thread.sleep(17); // 17 ms - 60 fps, 40 ms - 25 fps, 10 ms, 100
									// fps
				
				if (level <= 4)
					spawnRate = 60;
				if (level >= 5 && level <= 8)
					spawnRate = 45;
				if (level >= 9)
					spawnRate = 40;
					
				if (enemy_timer % spawnRate == 0) {
					board.generateEnemy();
					enemy_timer = 1;
				}

				world.processInteractions();

			
				if (world.gameOver()) {
					world.getHero().setStart();
					gui.incrementDeath();
					gui.resetCoins();
					world.setGame();
				}

				if (gui.end() && board.getCoins().size() == 0) {
					status = 1;
				}

				gui.repaint();
				enemy_timer += 1;
			}
		} catch (InterruptedException e) {

		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}