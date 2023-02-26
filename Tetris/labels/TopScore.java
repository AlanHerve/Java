package labels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.text.DecimalFormat;

public class TopScore extends Labels {

	
		int top_score;
		static final DecimalFormat decFormat = new DecimalFormat("000000");
		
		public TopScore(){
			this.setBounds(695, 400, 400, 75);
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setFont(Standard_Font);
			this.setForeground(Color.white);
			this.setBorder(to_use);
			this.setText("Top Score      " + decFormat.format(top_score));
		}
		
		
		public void setScore(int update) {
			top_score = update;
			this.setText("Top Score      " + decFormat.format(top_score));
		}
		
		public int getScore() {
			return top_score;
		}
		
	}


