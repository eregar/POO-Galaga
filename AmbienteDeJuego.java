import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AmbienteDeJuego extends JPanel implements KeyListener, Runnable{
	private NaveJugador nJ;
	private GameSystem gS;
	private boolean w,a,s,d,space,nave,paused;
	private int vidas;
	private Image fondo;
	private VentanaDeJuego vDJ;
	public JButton resume,restart; // cambie esto
	
	public AmbienteDeJuego (VentanaDeJuego vdj) {
		super();
		this.vDJ=vdj;
		this.setPreferredSize(new Dimension(800, 650));
		 
		this.resume= new JButton("Resume");
		this.restart= new JButton("Main Menu");
		Font fHome = new Font("AR DESTINE", Font.PLAIN, 40);
		
		this.resume.setFont(fHome);
		this.resume.setForeground(new Color(255, 255, 255));
		this.resume.setBackground(new Color(0, 0, 0));
		
		this.restart.setFont(fHome);
		this.restart.setForeground(new Color(255, 255, 255));
		this.restart.setBackground(new Color(0, 0, 0));
		
		this.add(this.resume);
		this.add(this.restart);
		this.setLayout(null);
		this.resume.setBounds(300,200,200,70);
		this.restart.setBounds(200,300,400, 70);
		
		this.add(this.resume);
		this.add(this.restart);
		this.setLayout(null);
		this.resume.setBounds(300,200,200,70);
		
		this.fondo = new ImageIcon("fondo.jpg").getImage();
		this.gS= new GameSystem(this);
		this.nJ = new NaveJugador();
		Thread hilo=new Thread(this);
		this.w=false;
		this.a=false;
		this.s=false;
		this.d=false;
		this.space=false;
		this.paused=false;
		this.nave=true;
		this.vidas= 5;
		this.resume.setVisible(false);
		this.restart.setVisible(false);
		
		this.addKeyListener(this);
		this.resume.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setPause(false);
			}});
		this.restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				vDJ.nuke();
			}});
		hilo.start();
	}
	public GameSystem getGS(){
		return this.gS;
	}

	public NaveJugador getNave(){
		return this.nJ;
	}
	public void removeNave(){
		this.nave=false;
		this.vidas-=1;
		if (vidas==0){
			this.restart.setVisible(true);
		}
	}
	public void addNave(){
		if(this.vidas==0){
			System.out.println("perdiste");
		}else{
		this.nave=true;
		}
	}
	public int getvidas(){
		return this.vidas;
	}
	public void setPause(boolean pausa){
		this.paused=pausa;
		this.gS.setPaused(pausa);
		this.resume.setVisible(pausa);
		this.restart.setVisible(pausa);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont( new Font("AR DESTINE", Font.PLAIN, 40)); 
		g.drawImage(this.fondo, 0, 0, this.getWidth(), this.getHeight(), this);
		if(nave){
		g.drawImage(this.nJ.getNaveJugadorImage(),this.nJ.getNX(),this.nJ.getNY(), 50, 50, this);
		}
		gS.paintC(g);
		for(int i=0;i<this.vidas;i++){
			g.drawImage(this.nJ.getNaveJugadorImage(), 10+i*25, 600, 25, 25, this);
		}
		if(this.vidas==0){
		g.drawString("Game Over", 300, 200);
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
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(this.paused){
				this.setPause(false);
			}else{
				if(this.gS.getAlive()){
					this.setPause(true);
				}
			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
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
				while (this.paused){
					Thread.sleep(100);
				}
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
