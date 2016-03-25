package Alarm;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Alarm {
	private int hour;
	private int minute;
	private File sound;
	private boolean isAlarmOn;
	private MediaPlayer mediaPlayer;
	private boolean triggered = false;
	
	public Alarm(int hour, int minute, File sound, boolean isAlarmOn) {
		this.hour = hour;
		this.minute = minute;
		this.sound = sound;
		this.isAlarmOn = isAlarmOn;
	}
	
	public void setTime(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	public int getHour() {
		return hour;
	}
	
	public String hourToString() {
		if (hour == 0) return "0" + hour;
		else return "" + hour;
	}
	
	public String minuteToString() {
		if (minute < 10) return "0" + minute;
		else return "" + minute;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setIsAlarmOn(boolean isAlarmOn) {
		this.isAlarmOn = isAlarmOn;
	}
	
	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}
	
	public boolean getIsAlarmOn() {
		return isAlarmOn;
	}
	
	public File getSound() {
		return sound;
	}
	
	public boolean getTriggered() {
		return triggered;
	}
	
	public void playAlarm() {
		triggered = true;
		Media media = new Media(sound.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	public void snooze() {
		minute += 10;
		if (minute >= 60) {
			minute -= 60;
			hour += 1;
			if (hour == 24) hour = 0;
		}
	}
	
	public void stopAlarm() {
		mediaPlayer.stop();
	}
	
	@Override
	public String toString() {
		if (isAlarmOn) {
			return "Alarm Time: " + hourToString() + ":" + minuteToString() + " | Current Setting: On";
		}
		return "Alarm Time: " + hourToString() + ":" + minuteToString()  + " Current Setting: Off";
	}
}
