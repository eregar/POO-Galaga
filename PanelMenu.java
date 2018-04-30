import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMenu extends JPanel implements ActionListener{
	
	private JButton begin, controls, exit;
	private Image fondo;
	private JLabel tittle;
	private VentanaDeJuego VDJ;
	
	public PanelMenu(VentanaDeJuego alpha) {
		super();
		this.setPreferredSize(new Dimension(800, 650));
		this.VDJ = alpha;
		this.fondo = new ImageIcon("fondoMenu.jpg").getImage();
		this.begin = new JButton("BEGIN");
		this.controls = new JButton("CONTROLS");
		this.exit = new JButton("EXIT");
		Font tfont = new Font("AR DESTINE", Font.PLAIN, 120);
		this.tittle = new JLabel("GALAGA 2.0");
		this.tittle.setFont(tfont);
		this.tittle.setForeground(new Color(102, 255, 51));
		
		Font fBegin = new Font("AR DESTINE", Font.PLAIN, 60);
		this.begin.setFont(fBegin);
		this.begin.setForeground(new Color(255, 255, 255));
		this.begin.setBackground(new Color(255, 51, 153));
		
		this.controls.setFont(new Font("AR DESTINE", Font.PLAIN, 40));
		this.controls.setForeground(new Color(255, 255, 255));
		this.controls.setBackground(new Color(255, 51, 153));
		
		this.exit.setFont(new Font("AR DESTINE", Font.PLAIN, 20));
		this.exit.setForeground(new Color(255, 255, 255));
		this.exit.setBackground(new Color(255, 51, 153));
		this.add(this.tittle);
		
		this.add(this.begin);
		this.add(new JLabel("                                                                                                                                                                       "));
		this.add(new JLabel("                                                                                                                                                                                                    "));
		this.add(this.controls);
		this.add(new JLabel("                                                                                                                                                              "));
		this.add(new JLabel("                                                                                                                                                                                                                                                      "));
		
		this.add(this.exit);
		this.add(new JLabel("                                                                                                                                                                                                            "));
		this.begin.addActionListener(this);
		this.exit.addActionListener(this);
		this.controls.addActionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.fondo, 0, 0, this.getWidth(), this.getHeight(), this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.begin) {
			this.setVisible(false);
			this.VDJ.add(this.VDJ.getADJ());
			this.VDJ.getADJ().getGS().startHilo();
		}else if(e.getSource() == this.exit) {
			this.VDJ.dispose(); 
			System.exit(0);
		}else if(e.getSource() == this.controls) {
			this.setVisible(false);
			this.VDJ.add(this.VDJ.getPC());
			this.VDJ.getPC().setVisible(true);
		}
	}
	
}
