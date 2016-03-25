package Alarm;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartAlarm extends Application {
	public static void main(String[] args) throws IOException {
		Application.launch();
	}
	
	public static void playSound(File sound) {
		Media media = new Media(sound.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		AlarmModel m = new AlarmModel();
		AlarmView view = new AlarmView(m);
	}
}
