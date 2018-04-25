import java.awt.Graphics;

public class Proyectil {
	int x,y,velocity,adress;
	boolean direction;
	

	public Proyectil(int x, int y,boolean direction,int adress){
		this.x=x+20;
		this.y=y;
		this.velocity=10;
		this.direction= direction;
		this.adress=adress;
	}
	public int directionCheck (boolean direction){
		if (direction){
			return -1;
		}
		return 1;
	}
	public boolean checkBoundaries(){
		if (this.y<-1 || this.y>800){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int getAdress(){
		return this.adress;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public boolean getDirection(){
		return this.direction;
	}
	public void decAdress(){
		this.adress-=1;
	}
	
	public void pintaProyectil(Graphics g){
		g.drawRect(this.x, this.y+=velocity*this.directionCheck(this.direction), 10, 10);
	}
}
