import java.awt.Graphics;
import java.util.ArrayList;

public class GameSystem implements Runnable{
	private ArrayList<Proyectil> shots;
	private ArrayList<Enemigo> flota;
	private AmbienteDeJuego master;
	
	public GameSystem(AmbienteDeJuego adj){
		Thread hilo=new Thread(this);
		this.shots=new ArrayList<>();
		this.flota= new ArrayList<>();
		this.master=adj;
		//se eliminara
		this.flota.add(new Enemigo(this.master,0));
		hilo.start();
	}
	
	
	public void paintC(Graphics g){
		for(int i=0;i<this.shots.size();i++){
			this.shots.get(i).pintaProyectil(g);
		}
		for(int i=0;i<this.flota.size();i++){
			this.flota.get(i).pintaEnemigo(g);
		}
	}
	
	
	public void addShot(int x, int y, boolean direction){
		this.shots.add(new Proyectil(x,y,direction,this.shots.size()));
	}
	public void removeShot(int adress){
		this.shots.remove(adress);
		for(int i= adress;i<this.shots.size();i++){
			this.shots.get(i).decAdress();
		}
	}
	public void addEnemy(int x, int y, int adress){
		this.flota.add(new Enemigo(x,y,this.master,adress));
		
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
			while (true){
				for(int i=0;i<this.shots.size();i++){
					if (this.shots.get(i).checkBoundaries()){
						this.removeShot(i);
					}
				}
				for(int i=0;i<this.flota.size();i++){
					if (Math.random()*100>90){
						this.addShot(this.flota.get(i).getx(),this.flota.get(i).gety(),false);
					}
				}
				for(int i=0;i<this.flota.size();i++){
					for(int f=0;f<this.shots.size();f++){
						if(this.flota.size()!=0 && this.shots.size()!=0){
							if(this.flota.get(i).collide(this.shots.get(f))){
								this.removeEnemy(i);
							}
						}
					}
				}
				
			    
				Thread.sleep(100);
			}
		}
		catch(InterruptedException e){
			
		}
		// TODO Auto-generated method stub
		
	}

}
