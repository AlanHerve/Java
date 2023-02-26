package Shape;

import java.awt.Color;

import enumer.type;

public class IShape extends BasicShape {

	public IShape() {
		this.Couleur = Color.cyan;
		this.Dark_Couleur = Color.cyan.darker();
		this.Light_Couleur = new Color(170, 255,255);
		this.type_object = type.I;
		this.setXY(3 * width +10, 10, 0);
		this.orientation = 2;
		this.forgeCoor();
	}
	
	@Override
	public void forgeCoor() {
		switch(orientation) {
		case 1 :
			
			setXY(CoorX[0], CoorY[0] + width,1);
			setXY(CoorX[0], CoorY[0] + 2*width,2);
			setXY(CoorX[0], CoorY[0] + 3*width,3);
			break;
		case 2 : 
		setXY(CoorX[0]+width, CoorY[0],1);
		setXY(CoorX[0]+2*width, CoorY[0],2);
		setXY(CoorX[0]+3*width, CoorY[0],3);
			break;
		case 3 :
		setXY(CoorX[0], CoorY[0] + width,1);
		setXY(CoorX[0], CoorY[0] + 2*width,2);
		setXY(CoorX[0], CoorY[0] + 3*width,3);
			break;
		case 4 :
		setXY(CoorX[0]-width, CoorY[0], 1);
		setXY(CoorX[0]-2*width, CoorY[0], 2);
		setXY(CoorX[0]-3*width, CoorY[0], 3);
			break;
		default : System.out.println("--Erreur Orientation--");
			break;
		}
		
	}

	@Override
	public boolean isCoorValid(type[][] Grid) {
		this.collision =0;
		if(this.orientation +1 > 4) {
			verif_orientation = 1;
		}else {
			verif_orientation = this.orientation+1;
		}
	
		switch(verif_orientation) {
		case 1 :
			
			if(this.getY(0) + 1 > 19) {
				collision++;
			}else if(Grid[this.getY(0) + 1][this.getX(0)] != null) {
				collision++;
			}
			
			if(this.getY(0) + 2 * 1> 19) {
				collision++;
			}else if(Grid[this.getY(0) +2* 1][this.getX(0)] != null) {
				collision++;
			}
			
			if(this.getY(0) + 3* 1 > 19) {
				collision++;
			}else {
				if(Grid[this.getY(0) + 3][this.getX(0)] != null) {
					collision++;
				}
			}
			
			
			break;
		case 2 : 
			System.out.print(" c le 2");
			if(this.getX(0) + 1 >9) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) + 1] != null) {
				collision++;
			}
			
			if(this.getX(0) + 2* 1 > 9) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) + 2 ] != null) {
				collision++;
			}
			
			if(this.getX(0) + 3* 1 > 9 ) {
				collision++;
			}else {
				if(Grid[this.getY(0)][this.getX(0) + 3] != null) {
					collision++;
				}
			}
			
			break;
		case 3 :
			
			
			if(this.getY(0)+ 1 > 19) {
				collision++;
			}else if(Grid[this.getY(0) + 1][this.getX(0)] != null) {
				collision++;
			}
			
			if(this.getY(0) + 2* 1 >19) {
				collision++;
			}else if(Grid[this.getY(0) +2][this.getX(0)] != null) {
				collision++;
			}
			
			if(this.getY(0) + 3* 1 > 19) {
				collision++;
			}else {
				if(Grid[this.getY(0) + 3][this.getX(0)] != null) {
					collision++;
				}
			}
			
			break;
		case 4 :

			if(this.getX(0) - 1 < 0) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) - 1] != null) {
				collision++;
			}
			
			if(this.getX(0) -2 < 0) {
				collision++;
			}else if(Grid[this.getY(0)][this.getX(0) - 2] != null) {
				collision++;
			}
			
			if(this.getX(0) - 3 <  0) {
				collision++;
			}else {
				if(Grid[this.getY(0)][this.getX(0) - 3] != null) {
					collision++;
				}
			}
			
			break;
		default : System.out.println("--Erreur Orientation--");
			break;
		
	}
		if(collision != 0) {
			return false;
		}else {
			return true;
		}

}
}