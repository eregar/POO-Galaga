import javax.swing.JFrame;

public class VentanaDeJuego extends JFrame{
	
	private AmbienteDeJuego aDJ;
	
	public VentanaDeJuego() {
		super("Galaga 2.0");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.aDJ = new AmbienteDeJuego();
		this.aDJ.setFocusable(true);
		this.add(aDJ);
		this.pack();
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaDeJuego ventana1 = new VentanaDeJuego();
	}
}
