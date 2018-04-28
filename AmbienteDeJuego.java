import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AmbienteDeJuego extends JPanel implements KeyListener, Runnable{
	private NaveJugador nJ;
	private GameSystem gS;
	private boolean w,a,s,d,space,nave;
	private int vidas;
	private Image fondo;
	public AmbienteDeJuego () {
		super();
		this.setPreferredSize(new Dimension(800, 650));
		this.fondo = new ImageIcon("fondo.jpg").getImage();
		this.gS= new GameSystem(this);
		this.nJ = new NaveJugador();
		Thread hilo=new Thread(this);
		this.w=false;
		this.a=false;
		this.s=false;
		this.d=false;
		this.space=false;
		this.nave=true;
		this.vidas=3;
		
		this.addKeyListener(this);
		hilo.start();
	}
	// ESTO ES LO QUE CAMBIASTEEE
	public NaveJugador getNave(){
		return this.nJ;
	}
	public void removeNave(){
		this.nave=false;
		this.vidas-=1;
	}
	public void addNave(){
		if(this.vidas==0){
			System.out.println("perdiste");
			this.gS.kill();
		}else{
		this.nave=true;
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.fondo, 0, 0, this.getWidth(), this.getHeight(), this);
		if(nave){
		g.drawImage(this.nJ.getNaveJugadorImage(),this.nJ.getNX(),this.nJ.getNY(), 50, 50, this);
		}
		gS.paintC(g);
		for(int i=0;i<this.vidas;i++){
			g.drawImage(this.nJ.getNaveJugadorImage(), 10+i*25, 600, 25, 25, this);
		}
		if(this.vidas==0){
		g.drawString("PERDISTE :(", 300, 300);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && !this.d) {
			this.d=true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT && !this.a) {
			this.a=true;
		}else if(e.getKeyCode() == KeyEvent.VK_UP && !this.w) {
			this.w=true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN && !this.s) {
			this.s=true;
		}else if(e.getKeyCode()== KeyEvent.VK_SPACE && !this.space &&this.nave){
			gS.addShot(this.nJ.getNX(), this.nJ.getNY(), true);
			this.space=true;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.d=false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.a=false;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			this.w=false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.s=false;
		}else if(e.getKeyCode()== KeyEvent.VK_SPACE){
			this.space=false;
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void run() {
		try {
			while (true){
			if(this.w){
				if(this.nJ.getNY() >= 5) {
					this.nJ.setNY(-4);
				}
			}
			if(this.a){
				if(this.nJ.getNX()>= 5) {
					this.nJ.setNX(-7);
				}
			}
			if(this.s){
				if(this.nJ.getNY() <= 600) {
					this.nJ.setNY(4);
				}
			}
			if(this.d){
				if(this.nJ.getNX() <= 755) {  
					this.nJ.setNX(7);
				}
			}
			
			Thread.sleep(10);
			repaint();
			}} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
