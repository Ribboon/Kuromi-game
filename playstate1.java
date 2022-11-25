package javaapplication5;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;
public class playstate1 extends JPanel implements ActionListener {

    private final ImageIcon imgstate1 = new ImageIcon(this.getClass().getResource("s2.jpg"));
    private final ImageIcon imgstate2 = new ImageIcon(this.getClass().getResource("s1.jpg"));
    private final ImageIcon imgstate3 = new ImageIcon(this.getClass().getResource("s3.jpg"));
    private final ImageIcon imgmeleon = new ImageIcon(this.getClass().getResource("g0.png"));
    private final ImageIcon pause = new ImageIcon(this.getClass().getResource("p.png"));
    private final ImageIcon resum = new ImageIcon(this.getClass().getResource("play.png"));
    private final ImageIcon back = new ImageIcon(this.getClass().getResource("back.png"));
    
    KUROMI m = new KUROMI();

    home hg = new home();
    ImageIcon feildover = new ImageIcon(this.getClass().getResource("eezy_61-01.jpg"));
    ImageIcon exitover = new ImageIcon(this.getClass().getResource("exit.png"));
    ImageIcon restart = new ImageIcon(this.getClass().getResource("start.png"));
    JButton BStartover = new JButton(restart);
    JButton BExitover = new JButton(exitover);

    private JLabel score = new JLabel();
    public JButton BPause = new JButton(pause);
    public JButton BExithome = new JButton(back);
    public JButton Bresum = new JButton(resum);

    public ArrayList<cakeball> cakeball = new ArrayList<cakeball>();
    public ArrayList<Monster> Monster = new ArrayList<Monster>();
    public ArrayList<Monster2> ba5 = new ArrayList<Monster2>();
    public int times;
    public int HP = 0;
    public int rs1 = 1;
    public int rs2 = 2;
    public int amount = 50;
    boolean timestart = true;
    boolean startball = false;

    public int scor = 0;
    boolean paralyze1 = false;
    int time_paralyze = 5;

    Thread time = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }

                if (timestart == false) {
                    repaint();
                }
            }
        }
    });

    Thread actor = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
                repaint();
            }
        }
    });
    Thread tballs1 = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep((long) (Math.random() * 10000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    Monster.add(new Monster());
                }
            }
        }
    });
    Thread tballs5 = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep((long) (Math.random() * 10000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    ba5.add(new Monster2());
                }
            }
        }
    });
    Thread t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                if (timestart == false) {
                    times = (times - 1);
                    if (paralyze1) {
                        time_paralyze--;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    playstate1() {
        this.setFocusable(true);
        this.setLayout(null);
        //BPause.setBounds(850, 50, 40, 40);
        //BExithome.setBounds(500,30,30,30);
        //Bresum.setBounds(900, 50, 40, 40);
        BPause.addActionListener(this);
        BExithome.addActionListener(this);
        Bresum.addActionListener(this);
        BExithome.addActionListener(this);
        this.add(BPause);
        this.add(BExithome);
        this.add(score);
        this.add(Bresum);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                if (!paralyze1) {
                    if (a == KeyEvent.VK_A) {
                        if(m.x>0){m.x -= 10;}
                    } else if (a == KeyEvent.VK_D) {
                        if(m.x<1100){m.x += 10;}
                    }
                    if (m.count > 3) {
                        //m.count = 0;
                    } else if (a == KeyEvent.VK_UP || a == KeyEvent.VK_SPACE) {
                        m.count = 5;
                        if(amount>0){
                            for(int i=0;i<2;i++){
                                m.count = i;
                            }
                            cakeball.add(new cakeball(m.x+100, m.y)); // ตำแหน่งที่บอลออก
                            amount++;// 
                        }
                    }
                }
            }
            public void keyReleased(KeyEvent e) {
                m.count = 0;
            }
        });
        m.x = 300;// ตำแหน่งเกิด
        time.start();
        actor.start();
        t.start();
        tballs1.start();
        tballs5.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (times <= 0 || HP <= 0) // ก่อนเริ่มเกม หรือ ตาย
        {
            this.remove(BPause);
            this.remove(Bresum);
            this.remove(BExithome);
            this.setLayout(null);
            g.drawImage(feildover.getImage(), 0, 0, 1300, 800, this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 40));
            g.drawString("Points   " + scor, 380, 200);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 70));
            g.drawString("GAME OVER", 290, 150);
            g.drawImage(imgmeleon.getImage(), 580, 360, 400, 400, this);

        } 
        else if (times <= 60) // ไม่ตายและเวลาน้อยกว่า 50
        {
            g.drawImage(imgstate3.getImage(), 0, 0, 1300, 800, this);

            if (paralyze1) {
                // g.setColor(Color.WHITE);
                // g.setFont(new Font("Hobo Std", Font.BOLD, 50));
                // g.drawImage(img_paralyze.getImage(), m.x, 550, 100, 150, this);
                // g.drawString("AHHHH !!!", m.x + 100, 560);
            } else {
                // int xs=m.getX()
                //     ,ys=m.getY()
                //     ,ws=m.getW()
                //     ,hs=m.getH();
                //g.drawRect(xs, ys, ws, hs); กรอบเหลี่ยม
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 130, 110, this); //ขนาดตัวละคร
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < cakeball.size(); i++) {
                cakeball ba = cakeball.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 40, 40, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    cakeball.remove(i);
                }
            }
            //วาดซอมบี้
            for (int i = 0; i < Monster.size(); i++) {
                g.drawImage(Monster.get(i).getImage(), Monster.get(i).getX(), Monster.get(i).getY(), 64, 112, this);
            }
            //ทำดาเมจ monster
            for (int i = 0; i < cakeball.size(); i++) {
                for (int j = 0; j < Monster.size(); j++) {
                    if (Intersect(cakeball.get(i).getbound(), Monster.get(j).getbound())) { //intersect การชน

                        int HPmonster = Monster.get(j).getHP();
                        
                        if(HPmonster>1){
                            cakeball.remove(i);
                            Monster.get(j).setHP(HPmonster-1);
                        }else{
                            cakeball.remove(i);
                            Monster.remove(j);
                        }

                        scor += 5;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            for (int i = 0; i < ba5.size(); i++) {
                g.drawImage(ba5.get(i).getImage(), ba5.get(i).getX(),
                        ba5.get(i).getY(), 165, 164, this);

            }
            //monster
            for (int i = 0; i < cakeball.size(); i++) {
                for (int j = 0; j < ba5.size(); j++) {
                    if (Intersect(cakeball.get(i).getbound(), ba5.get(j).getbound())) {
                        int HPmonster2 = ba5.get(j).getHP();
                        
                        if(HPmonster2>1){
                            cakeball.remove(i);
                            ba5.get(j).setHP(HPmonster2-1);
                        }else{
                            cakeball.remove(i);
                            ba5.remove(j);
                        }

                        scor += 5;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //ชนคน
            for (int j = 0; j < Monster.size(); j++) {
                if (Intersect(Monster.get(j).getbound(),m.getbound() )) {
                    Monster.get(j).setX(Monster.get(j).getX()+50);
                    HP=HP-1;
                }
            }

            for (int j = 0; j < ba5.size(); j++) {
                if (Intersect(ba5.get(j).getbound(),m.getbound() )) {
                    ba5.get(j).setX(ba5.get(j).getX()+100);
                    HP=HP-2;
                }
            }

            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("POINTS =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        } 
        else if (times <= 80) 
        {
            g.drawImage(imgstate2.getImage(), 0, 0, 1300, 800, this);

            if (paralyze1) {
                // g.setColor(Color.WHITE);
                // g.setFont(new Font("Hobo Std", Font.BOLD, 50));
                // g.drawImage(img_paralyze.getImage(), m.x, 550, 100, 150, this);
                // g.drawString("AHHHH !!!", m.x + 100, 560);
            } else {
                // int xs=m.getX()
                //     ,ys=m.getY()
                //     ,ws=m.getW()
                //     ,hs=m.getH();
                //g.drawRect(xs, ys, ws, hs); กรอบเหลี่ยม
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 130, 110, this); //ขนาดตัวละคร
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < cakeball.size(); i++) {
                cakeball ba = cakeball.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 40, 40, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    cakeball.remove(i);
                }
            }
            //วาดซอมบี้
            for (int i = 0; i < Monster.size(); i++) {
                g.drawImage(Monster.get(i).getImage(), Monster.get(i).getX(), Monster.get(i).getY(), 64, 112, this);
            }
            //ทำดาเมจ monster
            for (int i = 0; i < cakeball.size(); i++) {
                for (int j = 0; j < Monster.size(); j++) {
                    if (Intersect(cakeball.get(i).getbound(), Monster.get(j).getbound())) { //intersect การชน

                        int HPmonster = Monster.get(j).getHP();
                        
                        if(HPmonster>1){
                            cakeball.remove(i);
                            Monster.get(j).setHP(HPmonster-1);
                        }else{
                            cakeball.remove(i);
                            Monster.remove(j);
                        }

                        scor += 5;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            for (int i = 0; i < ba5.size(); i++) {
                g.drawImage(ba5.get(i).getImage(), ba5.get(i).getX(),
                        ba5.get(i).getY(), 165, 164, this);

            }
            //monster
            for (int i = 0; i < cakeball.size(); i++) {
                for (int j = 0; j < ba5.size(); j++) {
                    if (Intersect(cakeball.get(i).getbound(), ba5.get(j).getbound())) {
                        int HPmonster2 = ba5.get(j).getHP();
                        
                        if(HPmonster2>1){
                            cakeball.remove(i);
                            ba5.get(j).setHP(HPmonster2-1);
                        }else{
                            cakeball.remove(i);
                            ba5.remove(j);
                        }

                        scor += 10;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //ชนคน
            for (int j = 0; j < Monster.size(); j++) {
                if (Intersect(Monster.get(j).getbound(),m.getbound() )) {
                    Monster.get(j).setX(Monster.get(j).getX()+50);
                    HP=HP-1;
                }
            }

            for (int j = 0; j < ba5.size(); j++) {
                if (Intersect(ba5.get(j).getbound(),m.getbound() )) {
                    ba5.get(j).setX(ba5.get(j).getX()+100);
                    HP=HP-2;
                }
            }

            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("POINTS =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);

        } 
        else if (times <= 0 || HP <= 0) // ตาย และหมดเวลา
        {
            this.remove(BPause);
            this.remove(Bresum);
            this.remove(BExithome);
            this.setLayout(null);
            g.drawImage(feildover.getImage(), 0, 0, 1300, 800, this);
            g.setColor(Color.gray);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 40));
            g.drawString("POINTS   " + scor, 380, 200);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 70));
            g.drawString("GAME OVER", 290, 150);
            g.drawImage(imgmeleon.getImage(), 580, 360, 400, 400, this);
        } 
        else // เล่นปกติ
        {
            g.drawImage(imgstate1.getImage(), 0, 0, 1300, 800, this);
            if (paralyze1) {
                // g.setColor(Color.RED);
                // g.setFont(new Font("Hobo Std", Font.BOLD, 50));
                // g.drawImage(img_paralyze.getImage(), m.x, 550, 100, 150, this);
                // g.drawString("-10", m.x + 100, 650);
                // g.drawString("AHHHH !!!", m.x + 100, 560);
            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 128, 102, this);
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < cakeball.size(); i++) {
                cakeball ba = cakeball.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 50, 50, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    cakeball.remove(i);
                }
            }
            //ball1
            for (int i = 0; i < Monster.size(); i++) {
                g.drawImage(Monster.get(i).getImage(), Monster.get(i).getX(), Monster.get(i).getY(), 64, 112, this);
            }
            //ทำดาเมจ monster
            for (int i = 0; i < cakeball.size(); i++) {
                for (int j = 0; j < Monster.size(); j++) {
                    if (Intersect(cakeball.get(i).getbound(), Monster.get(j).getbound())) {

                        int HPmonster = Monster.get(j).getHP();
                        
                        if(HPmonster>1){
                            cakeball.remove(i);
                            Monster.get(j).setHP(HPmonster-1);
                        }else{
                            cakeball.remove(i);
                            Monster.remove(j);
                        }

                        scor += 5;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            for (int i = 0; i < ba5.size(); i++) {
                g.drawImage(ba5.get(i).getImage(), ba5.get(i).getX(),
                        ba5.get(i).getY(), 165, 164, this);

            }
            //monster
            for (int i = 0; i < cakeball.size(); i++) {
                for (int j = 0; j < ba5.size(); j++) {
                    if (Intersect(cakeball.get(i).getbound(), ba5.get(j).getbound())) {
                        int HPmonster2 = ba5.get(j).getHP();
                        
                        if(HPmonster2>1){
                            cakeball.remove(i);
                            ba5.get(j).setHP(HPmonster2-1);
                        }else{
                            cakeball.remove(i);
                            ba5.remove(j);
                        }

                        scor += 5;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //ชนคน
            for (int j = 0; j < Monster.size(); j++) {
                if (Intersect(Monster.get(j).getbound(),m.getbound() )) {
                    Monster.get(j).setX(Monster.get(j).getX()+50);
                    HP=HP-1;
                }
            }

            for (int j = 0; j < ba5.size(); j++) {
                if (Intersect(ba5.get(j).getbound(),m.getbound() )) {
                    ba5.get(j).setX(ba5.get(j).getX()+100);
                    HP=HP-2;
                }
            }


            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("POINTS =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        }
    }

    public boolean Intersect(Rectangle2D a, Rectangle2D b) {
        return (a.intersects(b));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BStartover) {
            this.setSize(1000, 1000);
            this.add(hg);
            this.setLocation(null);
            timestart = true;
            startball = true;
        } else if (e.getSource() == BExitover) {
            System.exit(0);
        }
    }
}
