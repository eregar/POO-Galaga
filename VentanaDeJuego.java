import javax.swing.JFrame;

public class VentanaDeJuego extends JFrame{
	
	private AmbienteDeJuego aDJ;
	private PanelMenu PM;
	
	public VentanaDeJuego() {
		super("Galaga 2.0");
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.aDJ = new AmbienteDeJuego();
		this.aDJ.setFocusable(true);
		this.PM = new PanelMenu(this);
		this.add(aDJ);
		this.add(this.PM);
		this.pack();
		
		this.setResizable(false);
		this.setVisible(true);
	}
	public AmbienteDeJuego getADJ() {
		return this.aDJ;
	}
	
	public static void main(String[] args) {
		VentanaDeJuego ventana1 = new VentanaDeJuego();
	}
}
