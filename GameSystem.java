import java.awt.Graphics;
import java.util.ArrayList;

public class GameSystem implements Runnable{
	private ArrayList<Proyectil> shots;
	private ArrayList<Enemigo> flota;
	private AmbienteDeJuego master;
	private int tanda;
	public GameSystem(AmbienteDeJuego adj){
		Thread hilo=new Thread(this);
		this.shots=new ArrayList<>();
		this.flota= new ArrayList<>();
		this.master=adj;
		this.tanda=0;
		this.spawnEnemies(this.tanda);
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
	public void spawnEnemies(int tanda){
		int cordX=10;
		int cordY=10;
		int direction=1;
		for(int i=0;i<tanda*10+5;i++){
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
		if(this.flota.size()==0){
			tanda+=1;
			this.spawnEnemies(this.tanda);
		}
	}
	
	
	@Override
	public void run() {
		try {
			while (true){
				for(int i=0;i<this.shots.size();i++){
					if (this.master.getNave().collideProyectil(this.shots.get(i))){
						this.master.removeNave();
						Thread.sleep(5000);
						this.master.addNave();
					}
					if (this.shots.get(i).checkBoundaries()){
						this.removeShot(i);
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
			
		}
		// TODO Auto-generated method stub
		
	}

}
