package Shape;

import java.awt.Color;
import java.awt.Graphics;

import enumer.type;



public class OShape extends BasicShape {

	public OShape(){
		this.Couleur = Color.yellow;
		this.Dark_Couleur = Color.yellow.darker();
		this.Light_Couleur = new Color(255,255,153);
		this.type_object = type.O;
		
		
		this.setXY(4 * width +10, 10, 0);
		this.orientation = 2;
		this.forgeCoor();
	}
	
public void rotatePiece(int direction) {
		
	}
	
	
		
		
		
		
		
	



	@Override
	public void forgeCoor() {
		setXY(CoorX[0] + width, CoorY[0],1);
		setXY(CoorX[0], CoorY[0] + width,2);
		setXY(CoorX[0] + width, CoorY[0] + width,3);
		
	}

	@Override
	public boolean isCoorValid(type[][] Grid) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
