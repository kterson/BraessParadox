/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mateusz Roggenbuk 180452 
 * checkbox
 * NetBeans IDE 8.2
 * java version "1.8.0_221"
Java(TM) SE Runtime Environment (build 1.8.0_221-b11)
 */
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class Rysunek extends Canvas{
    private int x = 600;
    private int y = 100;
    private Font f;
    ArrayList<Point> punkty = new ArrayList<Point>();
    JCheckBox czarny = new JCheckBox("czarny");
    JCheckBox czerwony = new JCheckBox("czerwony");

    Rysunek(){
        super();
        f = new Font ("Arial", Font.BOLD,12);
        setFont(f);

        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
                System.out.println("event");

                if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
                    x = x + 10;
                    punkty.add(new Point(x,y));
                    repaint();
                }
                if(ke.getKeyCode()==KeyEvent.VK_LEFT){
                    x = x - 10;
                    punkty.add(new Point(x,y));
                    repaint();
                }
                if(ke.getKeyCode()==KeyEvent.VK_UP){
                    y = y - 10;
                    punkty.add(new Point(x,y));
                    repaint();
                }
                if(ke.getKeyCode()==KeyEvent.VK_DOWN){
                    y = y +10;
                    punkty.add(new Point(x,y));
                    repaint();
                }

            }
        });
    }

    public void paint(Graphics z0){
        int x1,x2,y1,y2;
        Graphics2D z2=(Graphics2D) z0;

        if(czarny.isSelected()){
            z2.setColor(Color.BLACK);
        }
        else if(czerwony.isSelected()){
            z2.setColor(Color.RED);
        }
        else {
            z2.setColor(Color.yellow);
        }


        x2=100;
        y2=100;
        int l = 20;
        for(Point p:punkty){
            System.out.println("x: "+p.getX()+" y:"+ p.getY());
            x1=(int)p.getX();
            y1=(int)p.getY();
            z2.drawLine(x2, y2, x1, y1);
            x2=(int)p.getX();
            y2=(int)p.getY();
            z0.drawString("x: "+p.getX()+" y: "+p.getY(),20,l);
            l = l +15;
        }

    }
}




class Lab3 extends JFrame {

    Lab3(String nazwa){
        super (nazwa);
        setResizable(false);
        setSize(700, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static void main(String[] args) {
        Lab3 z = new Lab3("grafika");
        System.out.println("aaa");
        Rysunek r = new Rysunek();
        z.setLayout(new FlowLayout(FlowLayout.LEADING));
        JPanel p1 = new JPanel();
        //p1.setPreferredSize(new Dimension(z.getSize().width/2, z.getSize().height));
        JPanel p2 = new JPanel();
        p2.setSize(z.getSize().width/2, z.getSize().height);
        r.setPreferredSize(new Dimension(500, z.getSize().height));
        p1.setBackground(Color.BLUE);
        r.setFocusable(true);

        p1.add(r.czerwony);
        p1.add(r.czarny);
        p2.add(r);
        z.add(p1);
        z.add(p2);
        z.setVisible(true);

    }

}