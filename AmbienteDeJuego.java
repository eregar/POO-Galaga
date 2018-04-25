import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AmbienteDeJuego extends JPanel implements KeyListener{
	private NaveJugador nJ;
	
	public AmbienteDeJuego () {
		super();
		this.setPreferredSize(new Dimension(800, 650));
		
		this.nJ = new NaveJugador();
		
		this.addKeyListener(this);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.nJ.getNaveJugadorImage(), 300 + this.nJ.getNX(), 500 + this.nJ.getNY(), 50, 50, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(this.nJ.getNX() + 300 <= 755) {  
				this.nJ.setNX(7);
				repaint();
			}
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(this.nJ.getNX() + 300 >= 5) {
				this.nJ.setNX(-7);
				repaint();
			}
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(this.nJ.getNY() + 500 >= 5) {
				this.nJ.setNY(-7);
				repaint();
			}
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(this.nJ.getNY() + 500 <= 600) {
				this.nJ.setNY(7);
				repaint();
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
