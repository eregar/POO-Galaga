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

}
