package gameframe_package;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


//import Buttons.Restart;

//import Buttons.Pause.PauseListener;
import labels.*;
import Shape.*;

import enumer.type;

import java.util.Random;

public class GameFrame extends JFrame implements ActionListener {

	
	//labels
	LevelLabel level;
	TimeLabel time;
	TopScore Top_Score;
	LinesCleared Lines;
	Score score;
	//buttons
	Restart restart;
	Pause pause;
	
	//Score 
	DecimalFormat decFormat = new DecimalFormat("000000");
	
	int[]allscores = new int[5];
	int difficulty;
	
	//File Manipulation
	FileWriter ScoreWriter;
	BufferedReader ScoreReader;
	String res = null;
	
	
	//Panel where most of the game happens
	GamePanel game;
	
	//timer that will check the state of the game an update the frame
	MoveListener keyboard;
	
	
	Timer update;
	
	

	//state of the game
	boolean running = true;
	boolean over = false;
	
	
	public GameFrame(int choice){
		
		
		this.difficulty = choice;
		this.setLayout(null);
		setSize(1150, 1000);
		setTitle("essai");
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addLabels(difficulty);
		
		
		keyboard = new MoveListener();
		this.addKeyListener(keyboard);
		
		
		//Pause essai2 = new Pause();
		//this.add(essai2);
		
		//this.button_panel = new ButtonPanel();
		//this.button_panel.setFocusable(false);
		
		//add(button_panel);
		
		
		
		//adding buttons
		restart = new Restart();
		this.add(restart);
		
		pause = new Pause();
		this.add(pause);

		//we get the previous scores
		this.fileManipulator(choice);
		
		update = new Timer(1, this);
		
		//start the game
		game = new GamePanel(choice);
		add(game);
		
		
		update.start();
		
		
		
		setVisible(true);
	}
	
	
	
	


	
private void fileManipulator(int choice) {
	
	
	try {
	      File myObj = new File("topscores.txt");
	      //if we create a new file we initialise the scores of every category at 0
	      if (myObj.createNewFile()) {
	    	  
	    	  ScoreWriter=new FileWriter("topscores.txt");
	    	 
	    	  for(int j =0; j<5; j++) {
	    		  ScoreWriter.write("000000\n");
	    	  }
	    	  ScoreWriter.close();
	        
	      } else {
	        //else we store them in an Array by converting the string we get to an int
	        ScoreReader = new BufferedReader(new FileReader(myObj));
       
	        //convert all scores to int
	        for(int i = 0; i<5; i++) {
	        	this.allscores[i] = Integer.parseInt(ScoreReader.readLine()); 
	        }
	        
	        //set top score to the difficulty we've chosen
	        this.Top_Score.setScore(allscores[choice-1]);
	        
	       
	       
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
}
	
private void addLabels(int choix){
		
		
		//tells the user the difficulty
	    level = new LevelLabel(choix);
		this.add(level);
		
		//tells the user their current score
		score = new Score();
		this.add(score);
		
		//tells the user the top score
		Top_Score = new TopScore();
		this.add(Top_Score);
		
		//tells the user the number of lines they've cleared
		Lines = new LinesCleared();
		this.add(Lines);
		
		//tells the user the time since the game started
		time = new TimeLabel();
		this.add(time);
		
		
		
	}

//timer checking the state of the game and updating the main frame
	@Override
	public void actionPerformed(ActionEvent e) {
	
	
		
		if(game.running == false) {
			//indicate the game is over
			
			this.over=true;
			
			//stop timers
			this.time.setTimerStop();
			this.update.stop();
try {
				//write the top scores in the file
				File myObj = new File("topscores.txt");
				allscores[this.difficulty-1] = this.Top_Score.getScore();
				ScoreWriter=new FileWriter("topscores.txt");
			
				for(int i=0; i<5; i++) {
					
					ScoreWriter.write(decFormat.format(allscores[i]) + "\n");
				}
				
				
				ScoreWriter.close();
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		//if score has changed, modify it
		if(game.scoreUpdate() > this.score.getScore()) {
			score.setScore(game.scoreUpdate());
		}
		//if score is higher than top score, change top score
		if(score.getScore() > Top_Score.getScore()) {
			Top_Score.setScore(score.getScore());
		}
		//if number of lines has changed, modify it
		if(game.lineUpdate()>this.Lines.getLines()) {
		this.Lines.setLines(game.lineUpdate());
	}
	
}

//listen to keyboard for potential moves made by the user	
	class MoveListener implements KeyListener{
		
		
			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(running == true) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_RIGHT :
						game.lateralMove(1);
						break;
					case KeyEvent.VK_LEFT :
						game.lateralMove(-1);
						break;
					case KeyEvent.VK_DOWN:
						//make pieces fall down faster
						game.modifyTimer();
						break;
					case KeyEvent.VK_UP :
						game.changedirection();
						break;
						
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(running == true) {
					if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						//set falling of pieces back to normal speed
						game.resetTimer();
					}
				}
			}
		}



	
	private	class Pause extends JButton implements ActionListener {
		//variable telling us if the button is set in position pause or resume
		
		
		public Pause(){
			
			
			this.setFont(new Font("Default", Font.PLAIN, 20));
			this.setForeground(Color.white);
			this.setText("Pause");
			this.setFocusable(false);
			this.setBounds(695, 825, 280, 100);
			this.setBorder(new LineBorder(Color.white, 3, true));
			this.setBackground(Color.DARK_GRAY);
			this.addActionListener(this);
		}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(over==false) {
			//the button behaves differently according to its position
			if(this.getText() == "Pause") {
				
				setText("Resume");
				game.setTimerStop();
				time.setTimerStop();
				update.stop();
				
				running = false;
				
				
				
				
			} else {


				
				setText("Pause");
				update.start();
				time.setTimerStart();
				game.setTimerStart();
			
				running = true;
			}
		}
		
	}
	}	
	

	
	
	
private	class Restart extends JButton implements ActionListener {

		public Restart(){
			
			
			this.setFont(new Font("Default", Font.PLAIN, 20));
			this.setForeground(Color.white);
			this.setText("Restart");
			this.setFocusable(false);
			this.setBounds(695, 700, 280, 100);
			this.setBorder(new LineBorder(Color.white, 3, true));
			this.setBackground(Color.DARK_GRAY);
			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO
			GameFrame RestartFrame = new GameFrame(difficulty);
			game.setTimerStop();
			dispose();
		}
		
	}


private class GamePanel extends JPanel implements ActionListener{

	//colors used to draw pixels that are not part of the moving piece
	Color Couleur;
	Color Light_Couleur;
	Color Dark_Couleur;
	
	//size of the pixels
	final int WIDTH = 45;
	//where to put the panel on the frame
	final int PANEL_ANCHORAGE = 10;
	
	Timer timer;
	
	
	int lines_cleared;
	int lines_cleared_total;
	int tetris_count;
	int square_filled;
	int score;
	
	int Delay;
	
	
	
	
	int[] Valeur_x = new int[3];
	int[] Valeur_y = new int[3];
	
	
	int move_collision;
	boolean is_rotation_possible;
	
	type[][] Grid = new type[20][10];
	
	BasicShape[] comes_next = new BasicShape[3];
	BasicShape falling_piece;
	
	Random random;
	int[] Resultstable = new int[3];
	
	
	boolean running = true;
	
	
	
	GamePanel(int choice){
		
		
		this.setLayout(null);
		this.setDifficulty(choice);
		this.setBounds(this.PANEL_ANCHORAGE, this.PANEL_ANCHORAGE, WIDTH*10 + WIDTH * 4 + 20, this.WIDTH*20 + 20);
		this.setBackground(Color.black);
		Border border = new LineBorder(Color.white, 3, true);
		
	
		
		this.setBorder(border);
		
		
		
		
		random = new Random();
		this.setFirst();
		this.takeAShot();
		
		
		timer = new Timer(Delay, this);
		timer.start();
	

		
		
	}
	
	
	private void setTimerStop() {
		this.timer.stop();
	}
	
	public void setTimerStart() {
		this.timer.start();
		this.running = true;
	}
	

	//check game's parameters
	
	
	public int scoreUpdate(){
		return score;
	}
	
	public int lineUpdate(){
		return lines_cleared_total;
	}
	
	private void setDifficulty(int choice) {
		switch(choice) {
		case 1:
			this.Delay=1000;
			break;
		case 2 :
			this.Delay = 500;
			break;
		case 3 : 
			this.Delay = 250;
			break; 
		case 4: 
			this.Delay = 125;
			break;
		case 5:
			this.Delay = 65;
			break;
			default :
				System.out.println("--Erreur Difficulte--");
		}
	}
	


public void setFirst() {
	switch(random.nextInt(7)+1){
	case 1:
		falling_piece = new IShape();
		break;
	case 2:
		falling_piece = new JShape();
		break;
	case 3: 
		falling_piece = new LShape();
		break;
	case 4:
		falling_piece = new OShape();
		break;
	case 5:
		falling_piece = new ReverseSShape();
		break;
	case 6:
		falling_piece = new SShape();
		break;
	case 7:
		falling_piece = new TShape();
		break;
	}
}
	
private void takeAShot() {
		
		
		for(int i = 0; i<3; i++) {
			this.Resultstable[i] = random.nextInt(7)+1;
			
		}
	}
	
	private void nextOne() {
		
		this.Resultstable[0]=this.Resultstable[1];
		this.Resultstable[1]=this.Resultstable[2];
		this.Resultstable[2] = random.nextInt(7)+1;;
		
		
	}
	
	private void cementShot() {
		for(int i =0; i<3; i++) {
			switch(Resultstable[i]){
			case 1:
				comes_next[i] = new IShape();
				comes_next[i].setXY(500, i * 100 + 100, 0);
				break;
			case 2:
				comes_next[i] = new JShape();
				comes_next[i].setXY(500 + WIDTH , i * 100 + 100, 0);
				break;
			case 3: 
				comes_next[i] = new LShape();
				comes_next[i].setXY(500 + WIDTH , i * 100 + 100, 0);
				break;
			case 4:
				comes_next[i] = new OShape();
				comes_next[i].setXY(500 + WIDTH/2, i * 100 + 100, 0);
				break;
			case 5:
				comes_next[i] = new ReverseSShape();
				comes_next[i].setXY(500 + WIDTH , i * 100 + 100, 0);
				break;
			case 6:
				comes_next[i] = new SShape();
				comes_next[i].setXY(500 + WIDTH , i * 100 + 100, 0);
				break;
			case 7:
				comes_next[i] = new TShape();
				comes_next[i].setXY(500 + WIDTH , i * 100 + 100, 0);
				break;
			}
			comes_next[i].setWidth();
			//comes_next[i].setXY(500, i * 100 + 10, 0);
			comes_next[i].forgeCoor();
		}
	}
	
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPred(g);
		drawMoving(g);
		drawGrid(g);
		draw(g);
		
		
	}
	
public void draw(Graphics g) {
		
		
		//draw the grid of the game
		g.setColor(Color.white);
		g.drawLine(20 + WIDTH * 10, 0, 20 + WIDTH *10, 20 + WIDTH *20);
		g.drawLine(19 + WIDTH * 10, 0, 19 + WIDTH *10, 20 + WIDTH *20);
		g.drawLine(21 + WIDTH * 10, 0, 21 + WIDTH *10, 20 + WIDTH *20);
		
		g.setColor(Color.DARK_GRAY);
		
		for (int i = 0; i<this.WIDTH*21; i= i+this.WIDTH) {
			g.drawLine(10, i+this.PANEL_ANCHORAGE, this.WIDTH*10+10 , i+this.PANEL_ANCHORAGE);
		}
		
		for (int j = 10; j<this.WIDTH*11; j=j+this.WIDTH) {
			
			g.drawLine(j, PANEL_ANCHORAGE, j, this.WIDTH*20+this.PANEL_ANCHORAGE);
		}
	}
	
	//draw the actual piece
	public void drawMoving(Graphics g) {
		falling_piece.drawPiece(g);
	}
	//draw every pixel on the game
	public void drawGrid(Graphics g) {
		for(int i = 0; i <20; i++) {
			for (int j = 0; j<10; j++) {
				if(Grid[i][j] != null) {
					
					
					this.setCouleurs(Grid[i][j]);
					this.drawPixel(j * WIDTH +10,i*WIDTH +10, g);
					
				}
			}
		}	
	}
	
private void drawPred(Graphics g) {
		
		
		
		this.cementShot();
		
		for(BasicShape todraw : this.comes_next) {
			todraw.drawPiece(g);
		}
		
	//	g.drawString("Next", 500 + 10 ,100);
		
	}
	
	
	public void changedirection() {
		
		this.is_rotation_possible = falling_piece.isCoorValid(Grid);
		if(this.is_rotation_possible == true) {
			falling_piece.rotate();
		}
		repaint();
	}
	
	public void modifyTimer() {
		timer.setDelay(Delay/10);
	}
	
	public void resetTimer() {
		timer.setDelay(Delay);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		
		
		
		if(falling_piece.descend(this.Grid) ==true) {

		
				falling_piece.finalisePosition(Grid);
			
				
				switch(comes_next[0].getType()){
				case I:
					falling_piece = new IShape();
					break;
				case J:
					falling_piece = new JShape();
					break;
				case L: 
					falling_piece = new LShape();
					break;
				case O:
					falling_piece = new OShape();
					break;
				case ReverseS:
					falling_piece = new ReverseSShape();
					break;
				case S:
					falling_piece = new SShape();
					break;
				case T:
					falling_piece = new TShape();
					break;
				}
				this.nextOne();
				
				this.isLineCompleted();
				
			
			if(falling_piece.canYouGenerateHere(Grid) == true) {
				
			}else {
				
				this.timer.stop();
				
				
				//TODO
				JDialog GameOver = new JDialog();
				//GameOver.setAlignment();
				GameOver.add(new JLabel("Game Over"));
				GameOver.setSize(300,300);
				GameOver.setVisible(true);
				
				
				this.running = false;
			}
			
			
			}
		
		repaint();
		
	}
	
	
	//set the colors for each different type of piece that is now on the board
	public void setCouleurs(type genre){
		switch(genre) {
		case I: this.Couleur = Color.cyan;
		this.Dark_Couleur = Color.cyan.darker();
		this.Light_Couleur = new Color(170, 255,255);
			break;
		case J:this.Couleur = Color.orange;
		this.Dark_Couleur = Color.orange.darker();
		this.Light_Couleur = new Color(250,210,110);
			break;
		case L:this.Couleur = Color.BLUE;
		this.Dark_Couleur = Color.blue.darker();
		this.Light_Couleur = new Color(51,153,255);
			break;
		case O:this.Couleur = Color.yellow;
		this.Dark_Couleur = Color.yellow.darker();
		this.Light_Couleur = new Color(255,255,153);
			break;
		case ReverseS:this.Couleur = Color.red;
		this.Dark_Couleur = Color.red.darker();
		this.Light_Couleur = new Color(255, 51, 51);
			break;
		case S:this.Couleur = Color.green;
		this.Dark_Couleur = Color.green.darker();
		this.Light_Couleur = new Color(102,255,102);
			break;
		case T :this.Couleur = new Color(138, 102,226);
		this.Dark_Couleur = new Color(102,51,153);
		this.Light_Couleur = new Color(200,125,250);
			break;
		}
		}
	
	public void drawPixel(int x ,int y, Graphics g){
		g.setColor(this.Couleur);
		g.fillRect(x, y, WIDTH, WIDTH);
		g.setColor(this.Dark_Couleur);
		int décalage = 5;
		
		
		
		
		//vertical
		
				//x +largeur -décalage, y +décalage, décalage, largeur-décalage
				g.fillRect(x +WIDTH-décalage, y +décalage, décalage, WIDTH -décalage);
				
			
				
				//horizontal
				
				//x +décalage, y+largeur-décalage, largeur-décalage, décalage
				g.fillRect(x + décalage, y +WIDTH-décalage, WIDTH-décalage, décalage);
				
				
				
				
				
				this.Valeur_x[0] = x;
				this.Valeur_x[1]=x+décalage;
				this.Valeur_x[2]=x+décalage;
				
				this.Valeur_y[0]= y+WIDTH;
				this.Valeur_y[1]= y+WIDTH;
				this.Valeur_y[2]=y+WIDTH-décalage;
				
				g.fillPolygon(Valeur_x, Valeur_y, 3);
				
				this.Valeur_x[0] = x +WIDTH-décalage;
				this.Valeur_x[1]=x+WIDTH;
				this.Valeur_x[2]=x+WIDTH;
				
				this.Valeur_y[0]= y+décalage;
				this.Valeur_y[1]= y+décalage;
				this.Valeur_y[2]=y;
				
				g.fillPolygon(Valeur_x, Valeur_y, 3);
				
				
				
				g.setColor(this.Light_Couleur);
				
				g.fillRect(x, y, décalage, WIDTH - décalage);
				g.fillRect(x, y,WIDTH - décalage, décalage);
				
				
				this.Valeur_x[0] = x +WIDTH-décalage;
				this.Valeur_x[1]=x+WIDTH-décalage;
				this.Valeur_x[2]=x+WIDTH;
				
				this.Valeur_y[0]= y+décalage;
				this.Valeur_y[1]= y;
				this.Valeur_y[2]=y;
				
				g.fillPolygon(Valeur_x, Valeur_y, 3);
				
				this.Valeur_x[0] = x;
				this.Valeur_x[1]=x+décalage;
				this.Valeur_x[2]=x;
				
				this.Valeur_y[0]= y+WIDTH-décalage;
				this.Valeur_y[1]= y+WIDTH-décalage;
				this.Valeur_y[2]=y+WIDTH;
				
				g.fillPolygon(Valeur_x, Valeur_y, 3);
				
				g.setColor(Color.GRAY);
				//g.drawLine(x + WIDTH, y + WIDTH, x + WIDTH -5, y + WIDTH -5);
	}
	

	
	public void lateralMove(int direction) {
	this.move_collision = 0;
		for(int i =0; i<4; i++) {
			if(falling_piece.getX(i) + direction <0 || falling_piece.getX(i) + direction >9) {
			this.move_collision++;
			
			
			
			}else if(Grid[falling_piece.getY(i)][falling_piece.getX(i) + direction] != null) {
				this.move_collision++;
			}
			
			
			
		}
		if(this.move_collision==0) {
			for(int i = 0; i<4; i++) {
				falling_piece.setXY(falling_piece.getX(i) * WIDTH + 10  + direction * WIDTH, falling_piece.getY(i) * WIDTH +10, i);
			}
		}
		
		repaint();
	}

	public void isLineCompleted() {
		lines_cleared = 0;
		
		for(int i =0; i<20; i++) {
			this.square_filled = 0;
			for(int j = 0; j<10; j ++) {
				
				if(Grid[i][j] != null) {
				
					square_filled++;
						//System.out.print(" + ");
				}else {
					//System.out.print(" - ");
				}
				
				
				if(square_filled==10) {
					lines_cleared++;
					lines_cleared_total++;
					for(int k = 0; k<10; k++) {
						Grid[i][k] = null;
					}
					
					for(int h = i; h>0; h--) {
						for(int hi=0; hi<10; hi++) {
							Grid[h][hi] = Grid[h -1][hi];
						}
						
						
					}
				}
				
			}
			//System.out.println("  ");
		}
		updateScore();
		
		}
	
	public void updateScore() {
		switch(lines_cleared) {
		case 1:
			score = score + 100;
			tetris_count = 0;
			break;
		case 2:
			score = score + 200;
			tetris_count = 0;
			break;
		case 3:
			score = score + 300;
			tetris_count=0;
			break;
		case 4: 
			if(tetris_count != 0) {
			score  = score + 1200;
			}else {
			score = score + 800;
			}
			tetris_count++;
			break;
		default:
			
			break;	
		}
		
		
	}	
}
}
 