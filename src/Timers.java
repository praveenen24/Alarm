import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Timers extends JPanel {
	private int hours;
	private int minutes;
	private int secondsLeft;
	private MediaPlayer player;
	private Timer t;
	private JLabel timeLabel;
	private File sound;
	
	private ActionListener chooseFileListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int choice = chooser.showOpenDialog(null);
			if (choice == JFileChooser.APPROVE_OPTION) {
				sound = chooser.getSelectedFile();
			}
		}
	};

	public Timers(int hours, int minutes, int secondsLeft) {
		super(new GridLayout(0,1));
		this.hours = hours;
		this.minutes = minutes;
		if (secondsLeft == 0) this.secondsLeft = 60;
		else this.secondsLeft = secondsLeft;
		timeLabel = new JLabel(hours + ":" + minutes + ":" + secondsLeft);
		t = new Timer();
		add(timeLabel);
		setPanel();
	}

	public void setPanel() {
		JButton startButton = new JButton("Start");
		JButton setButton = new JButton("Set");
		JButton pauseButton = new JButton("Pause");
		JButton soundButton = new JButton("Select Sound");
		Box hBox = Box.createHorizontalBox();
		String[] hour = new String[25];
		for (int i = 0; i < 25; i++) {
			hour[i] = String.valueOf(i);
		}
		String[] min = new String[60];
		for (int i = 0; i < 60; i++) {
			min[i] = String.valueOf(i);
		}
		JComboBox<String> hourField = new JComboBox<String>(hour);
		JComboBox<String> minuteBox = new JComboBox<String>(min);
		JComboBox<String> secondsBox = new JComboBox<String>(min);
		hBox.add(hourField);
		hBox.add(new JLabel("Hours"));
		hBox.add(minuteBox);
		hBox.add(new JLabel("Minutes"));
		hBox.add(secondsBox);
		hBox.add(new JLabel("Seconds"));
		Box hBox2 = Box.createHorizontalBox();
		setButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hours = Integer.parseInt((String) hourField.getSelectedItem());
				minutes = Integer.parseInt((String) minuteBox.getSelectedItem());
				secondsLeft = Integer.parseInt((String) secondsBox.getSelectedItem());
				System.out.println(hours + ":" + minutes + ":" + secondsLeft);
				timeLabel.setText(hours + " hours : " + minutes + " minutes : " + secondsLeft + " seconds");
				pause();
			}
		});
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		soundButton.addActionListener(chooseFileListener);
		hBox2.add(startButton);
		hBox2.add(pauseButton);
		hBox2.add(setButton);
		hBox2.add(soundButton);
		add(hBox);
		add(hBox2);
	}

	public void start() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				secondsLeft--;
				if (secondsLeft <= 0 && minutes <= 0 && hours <= 0) {
					if (sound == null) {
						timeLabel.setText("FINISHED!");
					} else {
						timeLabel.setText(hours + " hours : " + minutes + " minutes : " + secondsLeft + " seconds");
						playSound();
					}
					pause();
					return;
				}
				if (secondsLeft <= 0 && (minutes != 0 || hours != 0)) {
					secondsLeft = 60;
					minutes--;
					if (minutes <= 0) {
						minutes = 59;
						hours--;
					}
				}
				timeLabel.setText(hours + " hours : " + minutes + " minutes : " + secondsLeft + " seconds");
			}
		};
		t = new Timer();
		t.schedule(task, 0, 1000);
	}

	private void playSound() {
		Media media = new Media(sound.toURI().toString());
		player = new MediaPlayer(media);
		player.stop();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				player.play();
			}
		});
	}
	
	public void pause() {
		t.cancel();
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return secondsLeft;
	}	
}
