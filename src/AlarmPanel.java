import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AlarmPanel extends JPanel {
	private AlarmModel model;
	private JList<Alarm> alarmList;
	private DefaultListModel<Alarm> alarmListModel = new DefaultListModel<Alarm>();
	private JLabel timeLabel;
	private Timer t;

	private TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
			timeLabel.setText(s.format(Calendar.getInstance().getTime()));
		}
	};
	
	
	public AlarmPanel(AlarmModel model) {
		super(new GridLayout(0,1));
		this.model = model;
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		timeLabel = new JLabel(s.format(Calendar.getInstance().getTime()));
		timeLabel.setFont(new Font(timeLabel.getFont().getName(), Font.BOLD, 90));
		add(timeLabel);
		add(new JScrollPane(alarmList));
		t = new Timer();
		t.scheduleAtFixedRate(task, 0, 1000);
		model.runAlarms();
	}
}
