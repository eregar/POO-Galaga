import javax.swing.JFrame;

public class VentanaDeJuego extends JFrame{
	
	private AmbienteDeJuego aDJ;
	private PanelMenu PM;
	private PanelControls PC;
	public VentanaDeJuego() {
		super("Galaga 2.0");
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.aDJ = new AmbienteDeJuego();
		this.PC = new PanelControls(this);
		this.aDJ.setFocusable(true);
		this.PM = new PanelMenu(this);
		this.add(this.aDJ);
		this.add(this.PC);
		this.add(this.PM);
		this.pack();
		
		this.setResizable(false);
		this.setVisible(true);
	}
	public AmbienteDeJuego getADJ() {
		return this.aDJ;
	}
	public PanelControls getPC() {
		return this.PC;
	}
	public PanelMenu getPM() {
		return this.PM;
	}
	
	public static void main(String[] args) {
		VentanaDeJuego ventana1 = new VentanaDeJuego();
	}
}
