import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;

public class AlarmModel extends Observable {
	private ArrayList<Alarm> alarms;
	private boolean run = true;

	public AlarmModel() {
		alarms = new ArrayList<Alarm>();
	}	

	public void addAlarm(Alarm a) {
		alarms.add(a);
	}

	public void run() {
		while(run) {
			for (Alarm a : alarms) {
				if (a.getIsAlarmOn()) {
					Calendar c = GregorianCalendar.getInstance();
					System.out.println(c.get(Calendar.HOUR));
					if (a.getHour() == c.get(Calendar.HOUR) 
							&& a.getHour() == c.get(Calendar.MINUTE) 
							&& !a.getTriggered()) {
						update(a);
						return;
					}
				}
			}
		}
	}

	public Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}
	
	public void update(Alarm a) {
		setChanged();
		notifyObservers(a);
	}
}
