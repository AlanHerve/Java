package labels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;

import javax.swing.Timer;

public class TimeLabel extends Labels implements ActionListener {
	
	int seconds;
	int minutes;
	
	final static int DELAY=1000;
	
	
	final static DecimalFormat decFormat = new DecimalFormat("00");
	
	Timer timer_label;
	
	public TimeLabel(){
		this.setBounds(695, 10, 282, 125);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(Standard_Font);
		this.setForeground(Color.white);
		this.setBorder(to_use);
		this.setText("Time : " + decFormat.format(minutes) + ":"  + decFormat.format(seconds));
		timer_label = new Timer(DELAY, this);
		timer_label.start();
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		seconds++;
		
		if(seconds == 60) {
			seconds = 0;
			minutes++;
		}
		this.setText("Time : " + decFormat.format(minutes) + ":"  + decFormat.format(seconds));
		
	}
	
	
	public void setTimerStop() {
	
		this.timer_label.stop();
	}

	public void resetTime() {
		this.seconds=0;
	}

	
	public void setTimerStart() {
		timer_label.start();
	}
	
}
