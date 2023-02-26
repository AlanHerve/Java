package Shape;

import java.awt.Color;
import java.awt.Graphics;

import enumer.type;




public class LShape extends BasicShape {
	
	public LShape(){
		this.Couleur = Color.BLUE;
		this.Dark_Couleur = Color.blue.darker();
		this.Light_Couleur = new Color(51,153,255);
		
		this.type_object = type.L;
		
		this.setXY(4 * width +10, 10 + width, 0);
		this.orientation = 4;
		this.forgeCoor();
	}
	

public void rotatePiece(int direction) {
		
	}
	
	


	@Override
	public void forgeCoor() {
		switch(orientation) {
		case 1 : 
			
			setXY(CoorX[0], CoorY[0] + width, 1);
			setXY(CoorX[0], CoorY[0] + 2* width, 2);
			setXY(CoorX[0] + width, CoorY[0] + 2*width, 3);
			
			break;
		case 2 : 
			
			setXY(CoorX[0] - width, CoorY[0], 1);
			setXY(CoorX[0] - width, CoorY[0] + width, 2);
			setXY(CoorX[0] + width, CoorY[0], 3);
			
			

			break;
		case 3 :
			
			setXY(CoorX[0] - width, CoorY[0], 1);
			setXY(CoorX[0], CoorY[0] + width, 2);
			setXY(CoorX[0], CoorY[0] + 2 * width, 3);
			
		
			break;
		case 4 :
			
			setXY(CoorX[0] - width, CoorY[0], 1);
			setXY(CoorX[0] + width, CoorY[0], 2);
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
			
			if(this.getY(0) + 1 > 19) {
				collision++;
			}else if(Grid[this.getY(0) + 1][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getY(0) + 2 > 19) {
				collision++;
			}else if(Grid[this.getY(0) + 2][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getY(0) + 2 >19 || this.getX(0) +1 >9) {
				collision++;
			}else if(Grid[this.getY(0) + 2][this.getX(0) +1] !=null) {
				collision++;
			}
			
			
			//setXY(CoorX[0], CoorY[0] + width, 1);
			//setXY(CoorX[0], CoorY[0] + 2* width, 2);
			//setXY(CoorX[0] + width, CoorY[0] + 2*width, 3);
			
			break;
		case 2: 
			
			if(this.getX(0) - 1 < 0) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) - 1] !=null) {
				collision++;
			}
			
			if(this.getX(0) - 1 < 0 || this.getY(0) + 1> 19) {
				collision++;
			}else if(Grid[this.getY(0) + 1][this.getX(0) - 1] !=null) {
				collision++;
			}
			
			if(this.getX(0) + 1 >9) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) + 1] !=null) {
				collision++;
			}
			
			
			//setXY(CoorX[0] - width, CoorY[0], 1);
			//setXY(CoorX[0] - width, CoorY[0] + width, 2);
			//setXY(CoorX[0] + width, CoorY[0], 3);
			
			break;
		case 3:
			
			if(this.getX(0) - 1 < 0) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) - 1] !=null) {
				collision++;
			}
			
			if(this.getY(0) + 1 > 19) {
				collision++;
			}else if(Grid[this.getY(0) + 1][this.getX(0)] !=null) {
				collision++;
			}
			
			if(this.getY(0) + 2 >19) {
				collision++;
			}else if(Grid[this.getY(0) + 2][this.getX(0)] !=null) {
				collision++;
			}
			
			//setXY(CoorX[0] - width, CoorY[0], 1);
			//setXY(CoorX[0], CoorY[0] + width, 2);
			//setXY(CoorX[0], CoorY[0] + 2 * width, 3);
			
			break;
		case 4:
			
			if(this.getX(0) - 1 < 0) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) - 1] !=null) {
				collision++;
			}
			
			if(this.getX(0) + 1 > 9 ) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) + 1] !=null) {
				collision++;
			}
			
			if(this.getX(0) + 1 > 9 || this.getY(0) -1 <0) {
				collision++;
			}else if(Grid[this.getY(0) - 1][this.getX(0) + 1] !=null) {
				collision++;
			}
			
			
			//setXY(CoorX[0] - width, CoorY[0], 1);
			//setXY(CoorX[0] + width, CoorY[0], 2);
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
