import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartAlarm extends Application {
	public static void main(String[] args) throws IOException {
		Application.launch();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		AlarmModel m = new AlarmModel();
		AlarmView view = new AlarmView(m);
	}
}
