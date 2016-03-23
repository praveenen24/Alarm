import java.io.IOException;

public class StartAlarm {
	public static void main(String[] args) throws IOException {
		AlarmModel m = new AlarmModel();
		AlarmView view = new AlarmView(m);
	}
}
