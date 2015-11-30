import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class AlarmView extends JFrame implements Observer {
	private AlarmModel model;
	private JList<Alarm> alarmList;
	private DefaultListModel<Alarm> alarmListModel = new DefaultListModel<Alarm>();

	public AlarmView(AlarmModel m) {
		super("Alarm Clock");
		model = m;
		model.addObserver(this);
		alarmList = new JList<Alarm>(alarmListModel);
		setJMenuBar(getBar());
		add(mainPanel());
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		model.run();
	}

	public JPanel mainPanel() {
		JPanel panel = new JPanel(new GridLayout(0,1));
		panel.add(new JScrollPane(alarmList));
		return panel;
	}

	public JMenuBar getBar() {
		JMenuBar bar = new JMenuBar();

		JMenu alarmMenu = new JMenu("Alarm");
		JMenuItem addAlarm = new JMenuItem("Add");
		addAlarm.addActionListener(addAlarmListener);
		alarmMenu.add(addAlarm);
		bar.add(alarmMenu);
		return bar;
	}

	private ActionListener addAlarmListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JPanel panel = new JPanel(new GridLayout(0,1));
			String[] amPm = {"AM", "PM"};
			String[] hours = new String[12];
			for (int i = 0; i < 12; i++) {
				hours[i] = String.valueOf(i+1);
			}
			String[] minutes = new String[60];
			for (int i = 0; i < 60; i++) {
				if (i < 10) {
					minutes[i] = "0" + String.valueOf(i); 
				} else {
					minutes[i] = String.valueOf(i);
				}
			}
			JComboBox<String> hourBox = new JComboBox<String>(hours);
			JComboBox<String> minuteBox = new JComboBox<String>(minutes);
			JComboBox<String> amPmBox = new JComboBox<String>(amPm);
			panel.add(new JLabel("Time"));
			Box timeBox = Box.createHorizontalBox();
			timeBox.add(hourBox);
			timeBox.add(new JLabel(" : "));
			timeBox.add(minuteBox);
			timeBox.add(amPmBox);
			panel.add(timeBox);
			panel.add(new JLabel("Initial Setting"));
			String[] options = {"On", "Off"};
			JComboBox<String> optionBox = new JComboBox<String>(options);
			panel.add(optionBox);
			int result = JOptionPane.showConfirmDialog(getContentPane(), panel, "ALARM", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == 0) {
				JFileChooser chooser = new JFileChooser();
				int choice = chooser.showOpenDialog(getContentPane());
				if (choice == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					Alarm a;
					int h = Integer.parseInt((String) hourBox.getSelectedItem());
					if (((String)amPmBox.getSelectedItem()).equals("PM")) {
						h = h + 12;
					}
					if (optionBox.getSelectedItem().equals("On")) {						
						a = new Alarm(String.valueOf(h), (String) minuteBox.getSelectedItem(),
								f, true);
					} else {
						a = new Alarm(String.valueOf(h), (String) minuteBox.getSelectedItem(),
								f, false);
					}
					model.addAlarm(a);
					alarmListModel.addElement(a);
				}
			}
		}
	};
	
	public int snoozeOrStop() {
		Object[] choices = {"Stop", "Snooze"};
		Object defaultChoice = choices[0];
		int result = JOptionPane.showOptionDialog(this,
		             "Select one of the values",
		             "Title message",
		             JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE,
		             null,
		             choices,
		             defaultChoice);
		System.out.println(result);
		return result;
	}

	@Override
	public void update(Observable o, Object arg) {
		Alarm a = (Alarm) arg;
		a.playAlarm();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				int result = snoozeOrStop();
				if (result == 0) {
					int minute = Integer.parseInt(a.getMinute());
					minute+=1;
					a.setMinute(String.valueOf(minute));
					a.setTriggered(false);
					a.stopAlarm();
				} else {
					a.setTriggered(false);
					a.setIsAlarmOn(false);
					a.stopAlarm();
				}
			}
		});
		while(a.getTriggered()) {
		}model.run();
	}
}
