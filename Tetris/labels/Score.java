package labels;

import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;



public class Score extends Labels {

	
	int score;
	static final DecimalFormat decFormat = new DecimalFormat("000000");
	
	public Score(){
		this.setBounds(695, 500, 400, 75);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(Standard_Font);
		this.setForeground(Color.white);
		this.setBorder(to_use);
		this.setText(" Score      " + decFormat.format(score));
		
	}
	
	
	public void setScore(int update) {
	this.score = update;
	this.setText(" Score      " + decFormat.format(score));
	}
	
	public int getScore() {
		return this.score;
	}
}
