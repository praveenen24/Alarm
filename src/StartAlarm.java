import java.io.IOException;

import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartAlarm extends Application {
	private MediaPlayer mediaPlayer;
	public static void main(String[] args) throws IOException {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AlarmModel m = new AlarmModel();
		AlarmView view = new AlarmView(m);
//		c.showOpenDialog(null);
//		File sound = c.getSelectedFile();
//		Media media = new Media(sound.toURI().toString());
//		mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.play();
	}
}
