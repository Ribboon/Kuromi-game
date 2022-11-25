package javaapplication5;

import javax.swing.*;
public class javagame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame jf = new PlayGames();
        jf.setSize(1300, 800);
        jf.setTitle("KUROMI GAME");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }

}
