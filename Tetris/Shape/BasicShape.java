package Shape;

import java.awt.Color;
import java.awt.Graphics;

import enumer.type;



public abstract class BasicShape {
	
	 int width = 45;
	
	type type_object;
	
	Color Couleur;
	Color Light_Couleur;
	Color Dark_Couleur;
	
	int orientation;
	
	int collision;
	
	int verif_orientation;

	
	int[] Valeur_x = new int[3];
	int[] Valeur_y = new int[3];
	int relief = 5;
	
	int[] CoorX = new int[4];
	int[] CoorY = new int[4];
	
	abstract public void forgeCoor();
	abstract public boolean isCoorValid(type[][] Grid);
	
	
	public boolean canYouGenerateHere(type[][] Grid) {
		collision = 0;
		
		
		for(int i =0; i<4; i++) {
			if(Grid[this.getY(i)][this.getX(i)]!=null) {
				collision++;
			}
		}
		
		
		if(collision !=0) {
		return false;
		}else {
			return true;
		}
	}
	
	public boolean descend(type[][] tableau) {
		
		collision = 0;
		
		for (int i=0; i<4; i++) {
			if(CoorY[i] + width > width*20) {
			collision++;
			}else if(tableau[this.getY(i) + 1 ][this.getX(i)] !=null) {
				collision++;
				
		}
					 
			
			
		}
		if(collision ==0 ) {
		
		for (int i=0; i<4; i++) {
			
			CoorY[i]=CoorY[i] + width;
		}
		return false;
			}else {
				
				return true;
			}
				 
		
		
			
	}
	
	public void finalisePosition(type[][] Grid) {
	for(int i = 0; i<4; i++) {
		Grid[this.getY(i)][this.getX(i)] = this.getType();
	}
}
	
	public void rotate() {
	
		if(this.orientation +1 > 4) {
			this.orientation = 1;
		}else {
			this.orientation = this.orientation+1;
		}
		this.forgeCoor();
		
}
		
	public void drawPiece( Graphics g) {
	
		
		for(int i = 0; i <4; i++) {
			this.drawPixel( CoorX[i], CoorY[i], g);
		}
		
	}
	
	public void drawPixel(int x ,int y, Graphics g){
		g.setColor(this.getColor());
		g.fillRect(x, y, width, width);
		g.setColor(this.getDarkColor());
		
		
		
		
		if (width !=45) {
			relief = 3;
		}
		//vertical
		
				//x +largeur -relief, y +relief, relief, largeur-relief
				g.fillRect(x +width-relief, y +relief, relief, width -relief);
				
			
				
				//horizontal
				
				//x +relief, y+largeur-relief, largeur-relief, relief
				g.fillRect(x + relief, y +width-relief, width-relief, relief);
				
				
				
				
				
				this.Valeur_x[0] = x;
				this.Valeur_x[1]=x+relief;
				this.Valeur_x[2]=x+relief;
				
				this.Valeur_y[0]= y+width;
				this.Valeur_y[1]= y+width;
				this.Valeur_y[2]=y+width-relief;
				
				g.fillPolygon(Valeur_x, Valeur_y, 3);
				
				this.Valeur_x[0] = x +width-relief;
				this.Valeur_x[1]=x+width;
				this.Valeur_x[2]=x+width;
				
				this.Valeur_y[0]= y+relief;
				this.Valeur_y[1]= y+relief;
				this.Valeur_y[2]=y;
				
				g.fillPolygon(Valeur_x, Valeur_y, 3);
				
				
				
				g.setColor(this.getLightColor());
				
				g.fillRect(x, y, relief, width - relief);
				g.fillRect(x, y,width - relief, relief);
				
				
				this.Valeur_x[0] = x +width-relief;
				this.Valeur_x[1]=x+width-relief;
				this.Valeur_x[2]=x+width;
				
				this.Valeur_y[0]= y+relief;
				this.Valeur_y[1]= y;
				this.Valeur_y[2]=y;
				
				g.fillPolygon(Valeur_x, Valeur_y, 3);
				
				this.Valeur_x[0] = x;
				this.Valeur_x[1]=x+relief;
				this.Valeur_x[2]=x;
				
				this.Valeur_y[0]= y+width-relief;
				this.Valeur_y[1]= y+width-relief;
				this.Valeur_y[2]=y+width;
				
				g.fillPolygon(Valeur_x, Valeur_y, 3);
				
				g.setColor(Color.GRAY);
				
	}
	

	public type getType() {
		return this.type_object;
	}
	
	public int getY(int i) {
		return (CoorY[i] -10)/width;
	}
	
	public int getX(int i) {
		return (CoorX[i] - 10) / width;
	}
	
	
	public Color getColor() {
		return this.Couleur;
	}
	
	public Color getDarkColor() {
		return this.Dark_Couleur;
	}
	
	public Color getLightColor() {
		return this.Light_Couleur;
	}
	
	public void setWidth() {
		this.width = 30;
	}
	
	public void setXY(int setx, int sety, int i) {
		CoorX[i] = setx;
		CoorY[i] = sety;
	}
	
}
