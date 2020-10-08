
/**
 * This class contains the main method for the game.
 */
public class StartGame {
	
	public StartGame() {

	}

	public void run() {

		int deaths = 0;
		GUI g = new GUI(1, deaths);

		TestThread t = new TestThread("test", g);
		t.start();

		int status = 0;
		while (status == 0) {
			stop(); 
			if (g.end()) {
				status = 1;
				deaths = g.getDeaths();
			}
		}
		g.dispose();

		for (int i = 2; i < 13; i++) {
			g = new GUI(i, deaths);
			t = new TestThread("test", g);
			t.start();

			status = 0;
			while (status == 0) {
				stop(); 
				if (g.end()) {
					status = 1;
					deaths = g.getDeaths();
				}
			}
			g.dispose();
		}

	}

	public static void stop() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {

		}
	}
}