import java.awt.Graphics;

public class Proyectil {
	int x,y,velocity;
	boolean direction;

	public Proyectil(int x, int y,boolean direction){
		this.x=x;
		this.y=y;
		this.velocity=20;
		this.direction= direction;
	}
	public int directionCheck (boolean direction){
		if (direction){
			return 1;
		}
		return -1;
	}
	public void pintaProyectil(Graphics g){
		g.drawRect(this.x, this.y+=velocity*this.directionCheck(this.direction), 10, 10);
	}
}
