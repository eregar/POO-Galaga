import java.awt.Image;

import javax.swing.ImageIcon;

public class NaveJugador {
	private Image iconoNaveJugador;
	
	private int nX, nY;
	
	public NaveJugador() {
		this.iconoNaveJugador = new ImageIcon("naveJugador.png").getImage();
		
		this.nX = 300;
		this.nY = 500;
	}
	public Image getNaveJugadorImage() {
		return this.iconoNaveJugador;
	}
	public int getNX() {
		return this.nX;
	}
	public int getNY() {
		return this.nY;
	}
	public void setNX(int nX) {
		this.nX += nX;
	}
	public void setNY(int nY) {
		this.nY += nY;
	}
	public boolean collideProyectil(Proyectil chec){//solamente con proyectiles
		if (Math.abs((this.nX+25)-(chec.getx()+10))<=25 && Math.abs(this.nY-chec.gety())<=25 && !chec.getDirection()){//checa hitboxes
			return true;
		}else{
			return false;//false si no pego
		}
	}
	public boolean collideEnemy(Enemigo chec){
		if (Math.abs((this.nX+25)-(chec.getx()+10))<=25 && Math.abs(this.nY-chec.gety())<=25){//checa hitboxes
			return true;
		}else{
			return false;
		}
	}
}
