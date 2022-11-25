package javaapplication5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class PlayGames extends JFrame implements ActionListener {

    home home1 = new home();
    playstate1 state1 = new playstate1();
    gameover gover = new gameover();

    public PlayGames() {
        this.setSize(1300, 800);
        this.add(home1);
        home1.BExit1.addActionListener(this);
        home1.BStart.addActionListener(this);
        
        state1.BExithome.addActionListener(this);
        state1.BPause.addActionListener(this);
        state1.Bresum.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home1.BStart) {
            this.setLocationRelativeTo(null);
            this.remove(home1);
            this.setSize(1300, 800);
            this.add(state1);
            state1.requestFocusInWindow();
            state1.timestart = false;
            state1.scor = 0;
            state1.HP = 2;
            state1.times = 100;
            state1.startball = false;
            state1.timestart = false;
        } else if (e.getSource() == state1.BExithome) {
            System.exit(0);
        } else if (e.getSource() == home1.BExit1) {
            System.exit(0);
        } else if (e.getSource() == state1.BPause) {
            this.setLocationRelativeTo(null);
            this.setSize(1300, 800);
            this.add(state1);
            state1.requestFocusInWindow();
            state1.t.suspend();
            state1.time.suspend();
            state1.actor.suspend();
            state1.tballs1.suspend();
            state1.tballs5.suspend();
        } else if (e.getSource() == state1.Bresum) {
            this.setLocationRelativeTo(null);
            this.setSize(1300, 800);
            this.add(state1);
            state1.requestFocusInWindow();
            state1.t.resume();
            state1.time.resume();
            state1.actor.resume();
            state1.tballs1.resume();
            state1.tballs5.resume();
        }
        this.validate();
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame jf = new PlayGames();
        jf.setSize(1000, 800);
        jf.setTitle("KUROMI GAME");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }
}
