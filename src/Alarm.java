import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Alarm {
	private String hour;
	private String minute;
	private File sound;
	private boolean isAlarmOn;
	private MediaPlayer mediaPlayer;
	private boolean triggered = false;
	private boolean isPlaying = false;
	
	public Alarm(String hour, String minute, File sound, boolean isAlarmOn) {
		this.hour = hour;
		this.minute = minute;
		this.sound = sound;
		this.isAlarmOn = isAlarmOn;
	}
	
	public String getTime() {
		return hour + ":" + minute;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getMinute() {
		return minute;
	}
	
	public void setMinute(String minute) {
		this.minute = minute;
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
	
	public void stopAlarm() {
		mediaPlayer.stop();
	}
	
	@Override
	public String toString() {
		if (isAlarmOn) {
			return "Alarm Time: " + hour + ":" + minute + " | Current Setting: On";
		}
		return hour + ":" + minute + " Current Setting: Off";
	}
}
