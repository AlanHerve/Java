package Shape;

import java.awt.Color;
import java.awt.Graphics;

import enumer.type;

public class SShape extends BasicShape {
	
	public SShape(){
		this.Couleur = Color.green;
		this.Dark_Couleur = Color.green.darker();
		this.Light_Couleur = new Color(102,255,102);
		
		this.type_object = type.S;
		
		
		this.setXY(4 * width +10, 10 + width, 0);
		this.orientation = 2;
		this.forgeCoor();
	}

public void rotatePiece(int direction) {
		
	}
	
	

	public void fallingPiece(int ancrage_x, int ancrage_y) {
		
	}

	@Override
	public void forgeCoor() {
		switch(orientation) {
		case 1 : 
			setXY(CoorX[0], CoorY[0] - width, 1);
			setXY(CoorX[0] + width, CoorY[0], 2);
			setXY(CoorX[0] + width, CoorY[0] + width, 3);
			
			
			break;
		case 2 :
			setXY(CoorX[0] - width, CoorY[0], 1);
			setXY(CoorX[0], CoorY[0] - width, 2);
			setXY(CoorX[0] + width, CoorY[0] - width, 3);
			
			
			break;
		case 3 :
			
			setXY(CoorX[0], CoorY[0] - width, 1);
			setXY(CoorX[0] + width, CoorY[0], 2);
			setXY(CoorX[0] + width, CoorY[0] + width, 3);
			
			
			break;
		case 4 :
			
			setXY(CoorX[0] - width, CoorY[0], 1);
			setXY(CoorX[0], CoorY[0] - width, 2);
			setXY(CoorX[0] + width, CoorY[0] - width, 3);
			
			break;
		default : System.out.println("--Erreur Orientation--");
			break;
		}
		
	}

	@Override
	public boolean isCoorValid(type[][] Grid) {
		this.collision = 0;
		
		if(this.orientation +1 > 4) {
			verif_orientation = 1;
		}else {
			verif_orientation = this.orientation+1;
		}
		
		switch(verif_orientation) {
		case 1:
			
			if(this.getY(0) - 1 < 0) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getX(0) + 1 > 9) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getY(0) + 1 >19 || this.getX(0) + 1 > 9) {
				collision++;
			}else if(Grid[this.getY(0) + 1][this.getX(0) + 1] !=null) {
				collision++;
			}
			
			//setXY(CoorX[0], CoorY[0] - width, 1);
			//setXY(CoorX[0] + width, CoorY[0], 2);
			//setXY(CoorX[0] + width, CoorY[0] + width, 3);
			
			break;
		case 2: 
			
			if(this.getX(0) - 1 <0) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) - 1] !=null) {
				collision++;
			}
			
			if(this.getY(0) - 1<0) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getX(0) + 1 > 9 || this.getY(0) -1 <0) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0) + 1] !=null) {
				collision++;
			}
			
			
			//setXY(CoorX[0] - width, CoorY[0], 1);
			//setXY(CoorX[0], CoorY[0] - width, 2);
			//setXY(CoorX[0] + width, CoorY[0] - width, 3);
			
			break;
		case 3:
			
			if(this.getY(0) - 1 < 0) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getX(0) + 1 > 9) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getY(0) + 1 >19 || this.getX(0) + 1 > 9) {
				collision++;
			}else if(Grid[this.getY(0) + 1][this.getX(0) + 1] !=null) {
				collision++;
			}
			
			//setXY(CoorX[0], CoorY[0] - width, 1);
			//setXY(CoorX[0] + width, CoorY[0], 2);
			//setXY(CoorX[0] + width, CoorY[0] + width, 3);
			
			break;
		case 4:
			
			if(this.getX(0) - 1 <0) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) - 1] !=null) {
				collision++;
			}
			
			if(this.getY(0) - 1<0) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getX(0) + 1 > 9 || this.getY(0) -1 <0) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0) + 1] !=null) {
				collision++;
			}
			
			
			//setXY(CoorX[0] - width, CoorY[0], 1);
			//setXY(CoorX[0], CoorY[0] - width, 2);
			//setXY(CoorX[0] + width, CoorY[0] - width, 3);
			
			break;
		default: System.out.println("--Erreur Orientation--");
			break;
		}
		
		
		if(collision != 0) {
			return false;
		}else {
			return true;
		}
	}
	
	
}
