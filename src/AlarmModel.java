import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

public class AlarmModel extends Observable {
	private ArrayList<Alarm> alarms;

	public AlarmModel() {
		alarms = new ArrayList<Alarm>();
	}	

	public void addAlarm(Alarm a) {
		alarms.add(a);
	}

	public void run() {
		while(true) {
			for (Alarm a : alarms) {
				if (a.getIsAlarmOn()) {
					if (a.getTime().equals(getCurrentTime()) && !a.getTriggered()) {
//						System.out.println("Reached");
//						a.playAlarm();
						update(a);
						return;
					}
				}
			}
		}
	}

	public String getCurrentTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm");
		String currentTime = s.format(c.getTime());
		return currentTime;
	}
	
	public void update(Alarm a) {
		setChanged();
		notifyObservers(a);
	}
}
