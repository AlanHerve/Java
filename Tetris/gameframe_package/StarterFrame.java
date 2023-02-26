package gameframe_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;



public class StarterFrame extends JFrame implements ActionListener {
	
	final private Dimension set = new Dimension(150,30);
	
	private JButton Bouton_Difficile, Bouton_Facile, Bouton_Moyen, Bouton_Legendaire, Bouton_Avance;
	
	GameFrame Second_Frame;
	
	
	
	public StarterFrame() {
		
		this.openAddButtons();
		this.openSetSize();
	}
	
	
	
	private void openSetSize() {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,300);
		getContentPane().setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		
	}
	
	private void openAddButtons() {
		
		
		
		
		
		
		 this.Bouton_Difficile = new JButton("Difficile");
		 this.Bouton_Facile = new JButton("Facile");
		 this.Bouton_Moyen = new JButton("Moyen");
		 this.Bouton_Avance = new JButton("Avance");
		 this.Bouton_Legendaire = new JButton("Legendaire");
		
		this.Bouton_Difficile.setPreferredSize(set);
		this.Bouton_Moyen.setPreferredSize(set);
		this.Bouton_Facile.setPreferredSize(set);
		this.Bouton_Legendaire.setPreferredSize(set);
		this.Bouton_Avance.setPreferredSize(set);
		
		this.Bouton_Facile.addActionListener(this);
		this.Bouton_Moyen.addActionListener(this);
		this.Bouton_Difficile.addActionListener(this);
		this.Bouton_Avance.addActionListener(this);
		this.Bouton_Legendaire.addActionListener(this);
		
		
		personnalise(Bouton_Facile);
		personnalise(Bouton_Moyen);
		personnalise(Bouton_Avance);
		personnalise(Bouton_Difficile);
		personnalise(Bouton_Legendaire);
		
		this.add(Bouton_Facile);
		this.add(Bouton_Moyen);
		this.add(Bouton_Avance);
		this.add(Bouton_Difficile);
		this.add(Bouton_Legendaire);
		
		this.setLayout(new FlowLayout());
		
	}
	
	private void personnalise(JButton bouton) {
		bouton.setBorder(new LineBorder(Color.white, 3, true));
		bouton.setBackground(Color.DARK_GRAY);
		bouton.setFont(new Font("Default", Font.PLAIN, 20));
		bouton.setForeground(Color.white);
	}
	
	
		
		public void actionPerformed(ActionEvent e) {
			
if(e.getSource() == Bouton_Facile) {
				
				Second_Frame = new GameFrame(1);
				
				dispose();
		
				
			}else if(e.getSource() == Bouton_Moyen) {
				
				Second_Frame = new GameFrame(2);
				
				dispose();
				
				
			}else if(e.getSource() == Bouton_Difficile) {
				
				Second_Frame = new GameFrame(4);
				
				dispose();
				
				
			}else if(e.getSource() == Bouton_Avance) {
				
				Second_Frame = new GameFrame(3);
				
				dispose();
				
				
			}else if(e.getSource() == Bouton_Legendaire) {
				
				Second_Frame = new GameFrame(5);
				
				dispose();
				
				
			}
			
			
		}
		
	

	
	
}

