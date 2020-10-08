
import java.io.*;
import javax.sound.sampled.*;

public class Sound {
	private Clip bMusic;
	private File musicFile;
	private AudioInputStream stream;

	public Sound(String filePath) {
		musicFile = new File(filePath);
		try {
			stream = AudioSystem.getAudioInputStream(musicFile);
			bMusic = AudioSystem.getClip();
			bMusic.open(stream);
		} catch (UnsupportedAudioFileException exception) {
			System.out.println("ERROR : UNUSABLE AUDIO FILE");
		} catch (IOException exception) {
			System.out.println("ERROR : FAILED INPUT OR OUTPUT");
		} catch (LineUnavailableException exception) {
			System.out.println("ERROR : LINE NONEXISTENT");
		}
	}

	public void playBackgroundMusic() {
		bMusic.start();
	}

	public void stopBackgroundMusic() {
		if (bMusic.isRunning()) {
			bMusic.stop();
			bMusic.setFramePosition(0);
		}
	}
}