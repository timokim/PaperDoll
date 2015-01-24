import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.util.Vector;
import javax.swing.KeyStroke;

public class Main {


    public static void main(String[] args) {

        SpriteCanvas canvas = new SpriteCanvas();
        canvas.addSprite(Main.makeSprite());
        
        JFrame f = new JFrame();
        f.setTitle("Assignment 2: Paper Dolls");
        f.setJMenuBar(Main.makeMenuBar(canvas));
        f.getContentPane().add(canvas);
        f.getContentPane().setLayout(new GridLayout(1, 1));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(700, 600);
        f.setVisible(true);
    }

    private static Sprite makeSprite() {
        
        Sprite head = new EllipseSprite("head", 40,70);
        Sprite torso = new EllipseSprite("torso", 80, 140);
        Sprite ruleg = new EllipseSprite("ruleg", 20, 100);
        Sprite rlleg = new EllipseSprite("rlleg", 20, 80);
        Sprite rfoot = new EllipseSprite("rfoot", 40, 25);
        Sprite luleg = new EllipseSprite("luleg", 20, 100);
        Sprite llleg = new EllipseSprite("llleg", 20, 80);
        Sprite lfoot = new EllipseSprite("lfoot", 40, 25);
        Sprite ruarm = new EllipseSprite("ruarm", 20, 80);
        Sprite rlarm = new EllipseSprite("rlarm", 20, 70);
        Sprite luarm = new EllipseSprite("luarm", 20, 80);
        Sprite llarm = new EllipseSprite("llarm", 20, 70);
        Sprite rhand = new EllipseSprite("rhand", 25, 25);
        Sprite lhand = new EllipseSprite("lhand", 25, 25);
        
        head.transform(AffineTransform.getRotateInstance(Math.PI));
        head.transform(AffineTransform.getTranslateInstance(0, 5));
        torso.transform(AffineTransform.getTranslateInstance(340, 150));
       
        ruarm.transform(AffineTransform.getTranslateInstance(-40, 5));
        rlarm.transform(AffineTransform.getTranslateInstance(0, 80));
        rhand.transform(AffineTransform.getTranslateInstance(0, 70));
        ruarm.transform(AffineTransform.getRotateInstance(25 * Math.PI / 180));
        rlarm.transform(AffineTransform.getRotateInstance(-30 * Math.PI / 180));
        luarm.transform(AffineTransform.getTranslateInstance(40, 5));
        llarm.transform(AffineTransform.getTranslateInstance(0, 80));
        lhand.transform(AffineTransform.getTranslateInstance(0, 70));
        luarm.transform(AffineTransform.getRotateInstance(-25 * Math.PI / 180));
        llarm.transform(AffineTransform.getRotateInstance(30 * Math.PI / 180));
        
        
        ruleg.transform(AffineTransform.getTranslateInstance(-20, 140));
        rlleg.transform(AffineTransform.getTranslateInstance(0, 100));
        rfoot.transform(AffineTransform.getTranslateInstance(-10, 80));
        luleg.transform(AffineTransform.getTranslateInstance(20, 140));
        llleg.transform(AffineTransform.getTranslateInstance(0, 100));
        lfoot.transform(AffineTransform.getTranslateInstance(10, 80));
        
        
        
        
        torso.addChild(head);
        torso.addChild(ruleg); torso.addChild(luleg);
        ruleg.addChild(rlleg); luleg.addChild(llleg);
        rlleg.addChild(rfoot); llleg.addChild(lfoot);
        torso.addChild(ruarm); torso.addChild(luarm);
        ruarm.addChild(rlarm); luarm.addChild(llarm);
        rlarm.addChild(rhand); llarm.addChild(lhand);
        
        ruleg.setneighbour(luleg);
        rlleg.setneighbour(luleg);
        luleg.setneighbour(ruleg);
        llleg.setneighbour(ruleg);

        return torso;
    
    }
    
    
    
    private static Sprite CloneSprite() {
        Sprite head = new EllipseSprite("head", 40,70);
        Sprite torso = new EllipseSprite("torso", 80, 140);
        Sprite ruleg = new EllipseSprite("ruleg", 20, 100);
        Sprite rlleg = new EllipseSprite("rlleg", 20, 80);
        Sprite rfoot = new EllipseSprite("rfoot", 40, 25);
        Sprite luleg = new EllipseSprite("luleg", 20, 100);
        Sprite llleg = new EllipseSprite("llleg", 20, 80);
        Sprite lfoot = new EllipseSprite("lfoot", 40, 25);
        Sprite ruarm = new EllipseSprite("ruarm", 20, 80);
        Sprite rlarm = new EllipseSprite("rlarm", 20, 70);
        Sprite luarm = new EllipseSprite("luarm", 20, 80);
        Sprite llarm = new EllipseSprite("llarm", 20, 70);
        Sprite rhand = new EllipseSprite("rhand", 25, 25);
        Sprite lhand = new EllipseSprite("lhand", 25, 25);
        
        head.transform(AffineTransform.getRotateInstance(Math.PI));
        head.transform(AffineTransform.getTranslateInstance(0, 5));
        torso.transform(AffineTransform.getTranslateInstance(210, 150));
       
        ruarm.transform(AffineTransform.getTranslateInstance(-40, 5));
        rlarm.transform(AffineTransform.getTranslateInstance(0, 80));
        rhand.transform(AffineTransform.getTranslateInstance(0, 70));
        ruarm.transform(AffineTransform.getRotateInstance(25 * Math.PI / 180));
        rlarm.transform(AffineTransform.getRotateInstance(-30 * Math.PI / 180));
        luarm.transform(AffineTransform.getTranslateInstance(40, 5));
        llarm.transform(AffineTransform.getTranslateInstance(0, 80));
        lhand.transform(AffineTransform.getTranslateInstance(0, 70));
        luarm.transform(AffineTransform.getRotateInstance(-25 * Math.PI / 180));
        llarm.transform(AffineTransform.getRotateInstance(30 * Math.PI / 180));
        
        
        ruleg.transform(AffineTransform.getTranslateInstance(-20, 140));
        rlleg.transform(AffineTransform.getTranslateInstance(0, 100));
        rfoot.transform(AffineTransform.getTranslateInstance(-10, 80));
        luleg.transform(AffineTransform.getTranslateInstance(20, 140));
        llleg.transform(AffineTransform.getTranslateInstance(0, 100));
        lfoot.transform(AffineTransform.getTranslateInstance(10, 80));
        
        
        
        
        torso.addChild(head);
        torso.addChild(ruleg); torso.addChild(luleg);
        ruleg.addChild(rlleg); luleg.addChild(llleg);
        rlleg.addChild(rfoot); llleg.addChild(lfoot);
        torso.addChild(ruarm); torso.addChild(luarm);
        ruarm.addChild(rlarm); luarm.addChild(llarm);
        rlarm.addChild(rhand); llarm.addChild(lhand);
        
        ruleg.setneighbour(luleg);
        rlleg.setneighbour(luleg);
        luleg.setneighbour(ruleg);
        llleg.setneighbour(ruleg);
        
        
        Sprite head2 = new EllipseSprite("head", 40,70);
        Sprite torso2 = new EllipseSprite("torso", 80, 140);
        Sprite ruleg2 = new EllipseSprite("ruleg", 20, 100);
        Sprite rlleg2 = new EllipseSprite("rlleg", 20, 80);
        Sprite rfoot2 = new EllipseSprite("rfoot", 40, 25);
        Sprite luleg2 = new EllipseSprite("luleg", 20, 100);
        Sprite llleg2 = new EllipseSprite("llleg", 20, 80);
        Sprite lfoot2 = new EllipseSprite("lfoot", 40, 25);
        Sprite ruarm2 = new EllipseSprite("ruarm", 20, 80);
        Sprite rlarm2 = new EllipseSprite("rlarm", 20, 70);
        Sprite luarm2 = new EllipseSprite("luarm", 20, 80);
        Sprite llarm2 = new EllipseSprite("llarm", 20, 70);
        Sprite rhand2 = new EllipseSprite("rhand", 25, 25);
        Sprite lhand2 = new EllipseSprite("lhand", 25, 25);
        
        head2.transform(AffineTransform.getRotateInstance(Math.PI));
        head2.transform(AffineTransform.getTranslateInstance(0, 5));
        torso2.transform(AffineTransform.getTranslateInstance(200, 0));
       
        ruarm2.transform(AffineTransform.getTranslateInstance(-40, 5));
        rlarm2.transform(AffineTransform.getTranslateInstance(0, 80));
        rhand2.transform(AffineTransform.getTranslateInstance(0, 70));
        ruarm2.transform(AffineTransform.getRotateInstance(25 * Math.PI / 180));
        rlarm2.transform(AffineTransform.getRotateInstance(-30 * Math.PI / 180));
        luarm2.transform(AffineTransform.getTranslateInstance(40, 5));
        llarm2.transform(AffineTransform.getTranslateInstance(0, 80));
        lhand2.transform(AffineTransform.getTranslateInstance(0, 70));
        luarm2.transform(AffineTransform.getRotateInstance(-25 * Math.PI / 180));
        llarm2.transform(AffineTransform.getRotateInstance(30 * Math.PI / 180));
        
        
        ruleg2.transform(AffineTransform.getTranslateInstance(-20, 140));
        rlleg2.transform(AffineTransform.getTranslateInstance(0, 100));
        rfoot2.transform(AffineTransform.getTranslateInstance(-10, 80));
        luleg2.transform(AffineTransform.getTranslateInstance(20, 140));
        llleg2.transform(AffineTransform.getTranslateInstance(0, 100));
        lfoot2.transform(AffineTransform.getTranslateInstance(10, 80));
        
           
        torso2.addChild(head2);
        torso2.addChild(ruleg2); torso2.addChild(luleg2);
        ruleg2.addChild(rlleg2); luleg2.addChild(llleg2);
        rlleg2.addChild(rfoot2); llleg2.addChild(lfoot2);
        torso2.addChild(ruarm2); torso2.addChild(luarm2);
        ruarm2.addChild(rlarm2); luarm2.addChild(llarm2);
        rlarm2.addChild(rhand2); llarm2.addChild(lhand2);
        
        ruleg2.setneighbour(luleg2);
        rlleg2.setneighbour(luleg2);
        luleg2.setneighbour(ruleg2);
        llleg2.setneighbour(ruleg2);

        torso.addChild(torso2);
        return torso;
    
    }
    
    /* Menu with recording and playback. */
    private static JMenuBar makeMenuBar(final SpriteCanvas canvas) {
        JMenuBar mbar = new JMenuBar();

        JMenu filemenu = new JMenu("File");
                
        final JMenuItem reset = new JMenuItem("Reset");
        final JMenuItem quit = new JMenuItem("Quit");
        
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
		canvas.setColor(Color.BLACK);
		canvas.setBGColor(Color.WHITE);

                canvas.resetter();
                canvas.addSprite(Main.makeSprite());
                canvas.repaint();
            }
        });
        reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        
        
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        
        
        filemenu.add(reset);
        filemenu.addSeparator();
        filemenu.add(quit);

        
        mbar.add(filemenu);
        
        JMenu script = new JMenu("Scripting");
        final JMenuItem record = new JMenuItem("Start recording");
        final JMenuItem play = new JMenuItem("Start script");

        script.add(record);
        script.add(play);
        
        final SpriteCanvas savingcanvas = new SpriteCanvas();
        
        
        record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (record.getText().equals("Start recording")) {
                    canvas.resetter();
                    canvas.addSprite(Main.makeSprite());
                    canvas.repaint();
                    reset.setEnabled(false);
                    record.setText("Stop recording");
                    canvas.startRecording();
                } else if (record.getText().equals("Stop recording")) {
                    reset.setEnabled(true);
                    record.setText("Start recording");
                    canvas.stopRecording();
                } else {
                    assert false;
                }
            }
        });

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (play.getText().equals("Start script")) {
                    canvas.resetter();
                    canvas.addSprite(Main.makeSprite());
                    canvas.repaint();
                    play.setText("Stop script");
                    record.setEnabled(false);
                    canvas.startDemo();
                } else if (play.getText().equals("Stop script")) {
                    play.setText("Start script");
                    record.setEnabled(true);
                    canvas.stopRecording();
                    
                } else {
                    assert false;
                }
            }
        });
        mbar.add(script);
       
        
        JMenu features = new JMenu("Other Features");
        
        final JMenuItem more = new JMenuItem("Add Another Doll");
        final JMenuItem clone = new JMenuItem("Add Two simultaneous dolls");
        
        more.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.addSprite(Main.makeSprite());
                canvas.repaint();
            }
        });
        clone.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.addSprite(Main.CloneSprite());
                canvas.repaint();
            }
        });
        
        
        JMenu colourmenu = new JMenu("Colours");
                
        final JMenuItem black = new JMenuItem("Black");
        final JMenuItem red = new JMenuItem("Red");
        final JMenuItem green = new JMenuItem("Green");
        final JMenuItem blue = new JMenuItem("Blue");
        final JMenuItem yellow = new JMenuItem("Yellow");
        final JMenuItem white = new JMenuItem("White");
        
        black.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setColor(Color.BLACK);
                canvas.repaint();
            }
        });
        red.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setColor(Color.RED);
                canvas.repaint();
            }
        });
        green.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setColor(Color.GREEN);
                canvas.repaint();
            }
        });
        blue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setColor(Color.BLUE);
                canvas.repaint();
            }
        });
        yellow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setColor(Color.YELLOW);
                canvas.repaint();
            }
        });
        white.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setColor(Color.WHITE);
                canvas.repaint();
            }
        });
        
        colourmenu.add(black);
        colourmenu.add(red);
        colourmenu.add(green);
        colourmenu.add(blue);
        colourmenu.add(yellow);
        colourmenu.add(white);
        
        
        JMenu bgcolourmenu = new JMenu("Background Colours");
                
        final JMenuItem bgblack = new JMenuItem("Black");
        final JMenuItem bgred = new JMenuItem("Red");
        final JMenuItem bggreen = new JMenuItem("Green");
        final JMenuItem bgblue = new JMenuItem("Blue");
        final JMenuItem bgyellow = new JMenuItem("Yellow");
        final JMenuItem bgwhite = new JMenuItem("White");
        
        
        bgblack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setBGColor(Color.BLACK);
                canvas.repaint();
            }
        });
        bgred.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setBGColor(Color.RED);
                canvas.repaint();
            }
        });
        bggreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setBGColor(Color.GREEN);
                canvas.repaint();
            }
        });
        bgblue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setBGColor(Color.BLUE);
                canvas.repaint();
            }
        });
        bgyellow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setBGColor(Color.YELLOW);
                canvas.repaint();
            }
        });
        bgwhite.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                canvas.setBGColor(Color.WHITE);
                canvas.repaint();
            }
        });
        bgcolourmenu.add(bgblack);
        bgcolourmenu.add(bgred);
        bgcolourmenu.add(bggreen);
        bgcolourmenu.add(bgblue);
        bgcolourmenu.add(bgyellow);
        bgcolourmenu.add(bgwhite);

        
        features.add(more);
        features.add(clone);
        features.addSeparator();
        features.add(colourmenu);
        features.add(bgcolourmenu);
        mbar.add(features);
        
        JMenu info = new JMenu("\"To scale legs, use right-click\"");
        mbar.add(info);
        
        return mbar;
    }
}
