import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemigo {
	int x,y,adress,direction;//coordenadas y posicion en el arraylist
	AmbienteDeJuego master;//el ambiente de juego para que se vea la imagen
	
	static Image iconoEnemigo= new ImageIcon("enemiga.png").getImage();
	
	public Enemigo(int x, int y, AmbienteDeJuego adj, int address,int direction){
		this.x=x;
		this.y=y;
		this.master=adj;
		this.adress=address;
		this.direction=3*direction;
	}
	public void setPause(boolean pausa){
		
	}
	
	public void pintaEnemigo(Graphics g){//pinta al enemigo en su x y y
		g.drawImage(iconoEnemigo, x, y, 50,50,this.master);
	}
	public int getDirection(){
		return this.direction;
	}
	public void setDirection(int direction){
		this.direction=direction;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public void setx(){
		this.x+=this.direction;
	}
	public void sety(){
		this.y+=75;
	}
	public void decAdress(){// cuando se desaparezca que el adress se mueva para llenar el que desaparecio
		this.adress-=1;
	}
	
	public boolean collide(Proyectil chec){//solamente con proyectiles
		if (Math.abs((this.x+25)-(chec.getx()+10))<=25 && Math.abs(this.y-chec.gety())<=50 && chec.getDirection()){//checa hitboxes
			return true;
		}else{
			return false;//false si no pego
		}
	}
}
