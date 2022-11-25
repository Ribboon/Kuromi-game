package javaapplication5;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.net.URL;
public class Monster {

    Image img;
    public int x = 1300;
    public int y = 600;
    public int count = 0;
    public int HP = 2;

    Monster() {
        String imageLocation = "monster1.png";
        URL imageURL = this.getClass().getResource(imageLocation);
        img = Toolkit.getDefaultToolkit().getImage(imageURL);
        runner.start();
    }
    Thread runner = new Thread(new Runnable() {
        public void run() {
            while (true) {
                x -= 2;
                if (x <= 0) {
                    img = null;
                    runner = null ;
                    x = 1400 ;
                } 
                try {
                    Thread.sleep(35);
                } catch (InterruptedException e) {
                }
            }
        }
    });

    public int getHP(){
        return this.HP ;
    }
    public void setHP(int hp){
        this.HP = hp ;
    }


    public Thread getRunner() {
        return runner;
    }

    public void setRunner(Thread runner) {
        this.runner = runner;
    }

    public Image getImage() {
        return img;
    }

    public int getX() {
        return x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, 64, 112));
    }
}
