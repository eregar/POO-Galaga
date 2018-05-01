import java.awt.Color;
import java.awt.Graphics;

public class Boss extends Enemigo implements Runnable{
	int vida;
	boolean labelBoss,pause;
	AmbienteDeJuego adJ; // añadi esto
	public Boss(AmbienteDeJuego adj,GameSystem gs, int address, int direction) {
		super(300, 10, adj, address, direction);
		this.vida=79;
		this.labelBoss=false;
		this.adJ = adj;
		Thread bosshilo= new Thread(this);
		bosshilo.start();
	}
	public void setPause(boolean pausa){
		this.pause=pausa;
	}
	
	public void pintaEnemigo(Graphics g){
		super.pintaEnemigo(g);
		g.setColor(Color.RED);
		g.fillRect(5, 10, this.vida*10, 10);
		g.setColor(Color.WHITE);
		if(this.labelBoss){
			g.drawString("FINAL BOSS FIGHT!", 200, 300);
		}
		if (this.vida<=0){
			g.drawString("YOU WIN!!!", 250, 200);
			this.adJ.restart.setVisible(true); // añadi esto
		}
	}
	@Override
	public void run() {
		try {
			labelBoss=true;
			Thread.sleep(3000);
			labelBoss=false;
			while (true){
				while(this.pause){
					Thread.sleep(100);
				}
				for(int i=0;i<this.master.getGS().getShot().size();i++){
					if (this.master.getNave().collideProyectil(this.master.getGS().getShot().get(i))){
						this.master.removeNave();
						if(this.master.getvidas()<=0){
							this.master.getGS().kill();
							return;
						}
						Thread.sleep(3000);
					this.master.addNave();
					}
					if (this.collide(this.master.getGS().getShot().get(i))){
						this.vida-=1;
						this.master.getGS().removeShot(i);
						if (this.vida<=0){
							return;
						}
						break;
					}
					
				}
				for(int i=0;i<this.master.getGS().getShot().size();i++){
					if (this.master.getGS().getShot().get(i).checkBoundaries()){
					this.master.getGS().removeShot(i);
					break;
					}
				}
				if(this.getx()>=755){
					this.setDirection(-9);
				}
				else if(this.getx()<=0){
					this.setDirection(9);
				}
				this.setx();
// Chance de que aparezcan diversas balas--------------------------------------------------
				double chance=Math.random()*100;
				if(this.master.getGS().getShot().size()<210){
					if (chance>99.5){
						this.master.getGS().addShot(this.getx()+20,this.gety()+10,false);
						this.master.getGS().addShot(this.getx(),this.gety()+10,false);
						this.master.getGS().addShot(this.getx()-20,this.gety()+10,false);
						this.master.getGS().addShot(this.getx()+100,this.gety()+20,false);
						this.master.getGS().addShot(this.getx()+50,this.gety()+20,false);
						this.master.getGS().addShot(this.getx()-100,this.gety()+20,false);
					}
					if (chance>99){
						this.master.getGS().addShot(this.getx()+80,this.gety()-10,false);
						this.master.getGS().addShot(this.getx(),this.gety()-10,false);
						this.master.getGS().addShot(this.getx()-80,this.gety()-10,false);
					}
					else if(chance>95){
						this.master.getGS().addShot(this.getx(),this.gety()+10,false);
					}
					if (chance<1){
						for(int i=0;i<10;i++){
							this.master.getGS().addShot(10+i*25,this.gety(),false);
						}
					}
					else if (chance<2){
						for(int i=0;i<10;i++){
							this.master.getGS().addShot(800-i*25,this.gety(),false);
						}
					}
					if (chance<5){
						for(int i=0;i<10;i++){
							this.master.getGS().addShot(this.getx(),10+i*15,false);
						}
					}
				}
// c acaba lo de la chance de las balas-------------------------------------------------
				Thread.sleep(50);
			}	
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
	}
	
	
	
}
