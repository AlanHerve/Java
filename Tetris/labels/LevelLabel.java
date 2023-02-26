package labels;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.*;
import javax.swing.*;

public class LevelLabel extends Labels {
	
	
	public LevelLabel(int choix){
		
		this.setBounds(695, 150, 280, 75);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(Standard_Font);
		this.setForeground(Color.white);
		this.setBorder(to_use);
		this.setText("Level " + choix) ;
	}

}
