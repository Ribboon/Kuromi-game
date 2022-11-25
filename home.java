package javaapplication5;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class home extends JPanel{
        private ImageIcon feild = new ImageIcon(this.getClass().getResource("eezy_61-01.jpg"));
	private ImageIcon exit = new ImageIcon(this.getClass().getResource("exit.png"));
	private ImageIcon starts = new ImageIcon(this.getClass().getResource("start.png"));
	public JButton BStart = new JButton(starts);
	public JButton BExit1  = new JButton(exit);
	home(){
            setLayout(null);
            BExit1.setBounds(280, 650, 140,80);
            add(BExit1);
            add(BStart);
            BStart.setBounds(550,650,140,80);
            add(BStart);
	}
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(feild.getImage(),0,0,1000,800,this);
            g.setColor(Color.BLUE);
            g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,90));		
            g.drawString("KUROMI GAME",150,200);	
	}
}