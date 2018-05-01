import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelControls extends JPanel implements ActionListener{
	private JButton jbHome;
	private VentanaDeJuego VDJ;
	public PanelControls(VentanaDeJuego alpha) {
		super();
		this.setPreferredSize(new Dimension(800, 650));
		
		this.VDJ = alpha;
		Font fHome = new Font("AR DESTINE", Font.PLAIN, 40);
		this.jbHome = new JButton("HOME");
		this.jbHome.setFont(fHome);
		this.jbHome.setForeground(new Color(255, 255, 255));
		this.jbHome.setBackground(new Color(0, 0, 0));
		this.add(this.jbHome);
		this.add(new JLabel("                                                                                                                                                                                                                     "));
		this.jbHome.addActionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(10));
		
		Image fondo = new ImageIcon("fondoControles.jpg").getImage();
		g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), this);
		 
		g.setColor(Color.MAGENTA);
		g.setFont(new Font("AR DESTINE", Font.PLAIN, 40));
		g.drawString("LEFT", 150, 480);
		g.drawRect(150, 500, 75, 75);
		g.drawLine(197, 537, 165, 537);
		g.drawLine(185, 510, 160, 535);
		g.drawLine(160, 535, 185, 560);
		
		g.setColor(Color.GREEN);
		g.setFont(new Font("AR DESTINE", Font.PLAIN, 40));
		g.drawString("DOWN", 240, 620);
		g.drawRect(250, 500, 75, 75);
		g.drawLine(315, 535, 290, 560);
		g.drawLine(290, 560, 265, 535);
		g.drawLine(290, 523, 290, 555);
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("AR DESTINE", Font.PLAIN, 40));
		g.drawString("RIGHT", 340, 480);
		g.drawRect(350, 500, 75, 75);
		g.drawLine(375, 537, 410, 537);
		g.drawLine(412, 538, 387, 563);
		g.drawLine(387, 512, 412, 537);
		
		
		g.setColor(Color.RED);
		g.setFont(new Font("AR DESTINE", Font.PLAIN, 40));
		g.drawString("UP", 270, 380);
		g.drawRect(250, 400, 75, 75);
		g.drawLine(289, 420, 289, 450);
		g.drawLine(288, 415, 263, 440);
		g.drawLine(313, 440, 288, 415);
		
		g.setColor(Color.CYAN);
		g.drawRect(475, 500, 300, 75);
		g.setFont(new Font("AR DESTINE", Font.PLAIN, 40));
		g.drawString("SPACE BAR", 510, 550);
		g.drawString("SHOOT", 560, 620);
		
		
		g.setColor(new Color(255, 255, 255));
		g.setFont(new Font("AR DESTINE", Font.PLAIN, 80));
		g.drawString("HOW TO PLAY", 200, 100);
		g2d.setStroke(new BasicStroke());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.jbHome) {
			this.setVisible(false);
			this.VDJ.getPM().setVisible(true);
		}
		
	}
}
