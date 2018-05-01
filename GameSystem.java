import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

public class GameSystem implements Runnable{
	private ArrayList<Proyectil> shots;
	private ArrayList<Enemigo> flota;
	private AmbienteDeJuego master;
	private int tanda;
	private boolean alive,waiting,paused;
	
	
	public GameSystem(AmbienteDeJuego adj){
		this.shots=new ArrayList<>();
		this.flota= new ArrayList<>();
		this.master=adj;
		this.tanda=0;
		this.alive=false;
		this.waiting=false;
		this.paused=false;	
	}
	public void startHilo(){
		Thread hilo=new Thread(this);
		this.alive=true;
		hilo.start();
	}
	public void setPaused(boolean pausa){
		this.paused=pausa;
		if(this.flota.size()!=0){
		this.flota.get(0).setPause(pausa);
		}
	}
	
	public void paintC(Graphics g){
		for(int i=0;i<this.shots.size();i++){
			this.shots.get(i).pintaProyectil(g);
		}
		for(int i=0;i<this.flota.size();i++){
			this.flota.get(i).pintaEnemigo(g);
		}
		if(waiting){
		g.drawString("ROUND "+this.tanda+" START", 225, 200);
		}
	}
	public void kill(){
		this.alive=false;
	}
	public boolean getAlive(){
		return alive;
	}
	
	public void addShot(int x, int y, boolean direction){
		if (direction){
		this.shots.add(new Proyectil(x,y,direction,this.shots.size()));
		}else{
		this.shots.add(new Proyectil(x,y,direction,this.shots.size(),5));
		}
	}
	public void removeShot(int adress){
		this.shots.remove(adress);
		for(int i= adress;i<this.shots.size();i++){
			this.shots.get(i).decAdress();
		}
	}
	public void addEnemy(int x, int y, int adress, int direction){
		this.flota.add(new Enemigo(x,y,this.master,adress,direction));
	}
	public void addBoss(){
		this.flota.add(new Boss(this.master,this,0,3));
	}
	public ArrayList<Proyectil> getShot(){
		return this.shots;
	}
	public void spawnEnemies(int tanda){
		int cordX=10;
		int cordY=10;
		int direction=1;
		for(int i=0;i<tanda*5+5;i++){
			this.addEnemy(cordX, cordY, i,direction);
			cordX+=75;
			if(cordX>=755){
				cordX=50;
				cordY+=75;
				direction*=-1;
			}
		}
	}
	public void removeEnemy(int adress){
		this.flota.remove(adress);
		for(int i= adress;i<this.flota.size();i++){
			this.flota.get(i).decAdress();
		}
	}
	
	
	@Override
	public void run() {
		try {
			
			while (this.alive){
				while (this.paused){
					Thread.sleep(100);
				}
				if(this.flota.size()==0){
					if(this.tanda<8){
						this.spawnEnemies(this.tanda);
						tanda+=1;
					}else{
						this.addBoss();
						break;
					}
						this.waiting=true;
						Thread.sleep(3000);
						this.waiting=false;
					
				}
				for(int i=0;i<this.shots.size();i++){
					if (this.master.getNave().collideProyectil(this.shots.get(i))){
						this.master.removeNave();
						if(this.master.getvidas()<=0){
							this.kill();
							return;
						}
						Thread.sleep(3000);
						this.master.addNave();
						break;
					}
				}
				for(int i=0;i<this.shots.size();i++){
					if (this.shots.get(i).checkBoundaries()){
						this.removeShot(i);
						break;
					}
				}
				for(int i=0;i<this.flota.size();i++){
					//c mueve
					if(this.flota.get(i).getx()>=755){
						this.flota.get(i).setDirection(-3);
						this.flota.get(i).sety();
					}
					else if(this.flota.get(i).getx()<=0){
						this.flota.get(i).setDirection(3);
						this.flota.get(i).sety();
					}
						this.flota.get(i).setx();
						//dispara alv
					if (Math.random()*100>98){
						this.addShot(this.flota.get(i).getx(),this.flota.get(i).gety(),false);
					}
					//navecita checa si le pegó un enemigo
				}
				for(int i=0;i<this.flota.size();i++){
					for(int f=0;f<this.shots.size();f++){
						try{
						if(this.flota.size()!=0 && this.shots.size()!=0){
							if(this.flota.get(i).collide(this.shots.get(f))){
								this.removeEnemy(i);
								this.removeShot(f);
								break;
							}
						}
						}catch(NullPointerException e){
							System.out.println("ke demonios "+e);
						}
					}
				}
				Thread.sleep(50);
			}
		}
		catch(InterruptedException e){
			System.out.println(e);
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
		// TODO Auto-generated method stub
		
	}

}
