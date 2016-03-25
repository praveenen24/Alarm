package Alarm;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;

public class AlarmModel extends Observable {
	private ArrayList<Alarm> alarms;
	private ArrayList<Alarm> alarmQueue;
	private boolean run = true;

	public AlarmModel() {
		alarms = new ArrayList<Alarm>();
		alarmQueue = new ArrayList<Alarm>();
	}	

	public void addAlarm(Alarm a) {
		run = false;
		alarms.add(a);
		run = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				runAlarms();
			}
		}).start();
	}

	public void runAlarms() {
		while(run) {
			for (Alarm a : alarms) {
				if (a.getIsAlarmOn()) {
					Calendar c = Calendar.getInstance();
					if (a.getHour() == c.get(Calendar.HOUR_OF_DAY) 
							&& a.getMinute() == c.get(Calendar.MINUTE) 
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
