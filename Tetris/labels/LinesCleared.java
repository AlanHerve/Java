package labels;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;


import java.awt.*;
import javax.swing.*;


import javax.swing.border.LineBorder;

public class LinesCleared extends Labels {

	
	int lines_cleared;
	DecimalFormat decFormat = new DecimalFormat("00");
	
	public LinesCleared(){
		this.setBounds(695, 600, 400, 75);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(Standard_Font);
		this.setForeground(Color.white);
		this.setBorder(to_use);
		this.setText(" Lines      " + decFormat.format(lines_cleared));
	}
	
	
	public void setLines(int update) {
		this.lines_cleared = update;
		this.setText(" Lines      " + decFormat.format(lines_cleared));
	}
	
	public int getLines() {
		return this.lines_cleared;
	}
	
}
