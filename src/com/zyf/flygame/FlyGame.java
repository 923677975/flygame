package com.zyf.flygame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FlyGame {

    static BufferedImage airplane;
    static BufferedImage airplane_ember0;
    static BufferedImage airplane_ember1;
    static BufferedImage airplane_ember2;
    static BufferedImage airplane_ember3;
    static BufferedImage background;
    static BufferedImage bee;
    static BufferedImage bee_ember0;
    static BufferedImage bee_ember1;
    static BufferedImage bee_ember2;
    static BufferedImage bee_ember3;
    static BufferedImage bigplane;
    static BufferedImage bigplane_ember0;
    static BufferedImage bigplane_ember1;
    static BufferedImage bigplane_ember2;
    static BufferedImage bigplane_ember3;
    static BufferedImage bullet;
    static BufferedImage extraplane_ember0;
    static BufferedImage extraplane_ember1;
    static BufferedImage extraplane_ember2;
    static BufferedImage extraplane_ember3;
    static BufferedImage extraplane_ember4;
    static BufferedImage extraplane_ember5;
    static BufferedImage extraplane_hit;
    static BufferedImage extraplane_n1;
    static BufferedImage extraplane_n2;
    static BufferedImage gameover;
    static BufferedImage hero_ember0;
    static BufferedImage hero_ember1;
    static BufferedImage hero_ember2;
    static BufferedImage hero_ember3;
    static BufferedImage hero0;
    static BufferedImage hero1;
    static BufferedImage pause;
    static BufferedImage start;

    static {
        //一次性读取全部图片
        try {
            airplane = ImageIO.read(FlyGame.class.getResourceAsStream("res/airplane.png"));
            airplane_ember0 = ImageIO.read(FlyGame.class.getResourceAsStream("res/airplane_ember0.png"));
            airplane_ember1 = ImageIO.read(FlyGame.class.getResourceAsStream("res/airplane_ember1.png"));
            airplane_ember2 = ImageIO.read(FlyGame.class.getResourceAsStream("res/airplane_ember2.png"));
            airplane_ember3 = ImageIO.read(FlyGame.class.getResourceAsStream("res/airplane_ember3.png"));
            background = ImageIO.read(FlyGame.class.getResourceAsStream("res/background.png"));
            bee = ImageIO.read(FlyGame.class.getResourceAsStream("res/bee.png"));
            bee_ember0 = ImageIO.read(FlyGame.class.getResourceAsStream("res/bee_ember0.png"));
            bee_ember1 = ImageIO.read(FlyGame.class.getResourceAsStream("res/bee_ember1.png"));
            bee_ember2 = ImageIO.read(FlyGame.class.getResourceAsStream("res/bee_ember2.png"));
            bee_ember3 = ImageIO.read(FlyGame.class.getResourceAsStream("res/bee_ember3.png"));
            bigplane = ImageIO.read(FlyGame.class.getResourceAsStream("res/bigplane.png"));
            bigplane_ember0 = ImageIO.read(FlyGame.class.getResourceAsStream("res/bigplane_ember0.png"));
            bigplane_ember1 = ImageIO.read(FlyGame.class.getResourceAsStream("res/bigplane_ember1.png"));
            bigplane_ember2 = ImageIO.read(FlyGame.class.getResourceAsStream("res/bigplane_ember2.png"));
            bigplane_ember3 = ImageIO.read(FlyGame.class.getResourceAsStream("res/bigplane_ember3.png"));
            bullet = ImageIO.read(FlyGame.class.getResourceAsStream("res/bullet.png"));
            extraplane_ember0 = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_ember0.png"));
            extraplane_ember1 = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_ember1.png"));
            extraplane_ember2 = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_ember2.png"));
            extraplane_ember3 = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_ember3.png"));
            extraplane_ember4 = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_ember4.png"));
            extraplane_ember5 = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_ember5.png"));
            extraplane_hit = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_hit.png"));
            extraplane_n1 = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_n1.png"));
            extraplane_n2 = ImageIO.read(FlyGame.class.getResourceAsStream("res/extraplane_n2.png"));
            gameover = ImageIO.read(FlyGame.class.getResourceAsStream("res/gameover.png"));
            hero_ember0 = ImageIO.read(FlyGame.class.getResourceAsStream("res/hero_ember0.png"));
            hero_ember1 = ImageIO.read(FlyGame.class.getResourceAsStream("res/hero_ember1.png"));
            hero_ember2 = ImageIO.read(FlyGame.class.getResourceAsStream("res/hero_ember2.png"));
            hero_ember3 = ImageIO.read(FlyGame.class.getResourceAsStream("res/hero_ember3.png"));
            hero0 = ImageIO.read(FlyGame.class.getResourceAsStream("res/hero0.png"));
            hero1 = ImageIO.read(FlyGame.class.getResourceAsStream("res/hero1.png"));
            pause = ImageIO.read(FlyGame.class.getResourceAsStream("res/pause.png"));
            start = ImageIO.read(FlyGame.class.getResourceAsStream("res/start.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final int WIDTH = 400;    //定义常量 窗口的宽
    public static final int HEIGHT = 650;   //定义常量 窗口的高

    public static void main(String[] args) {

        JFrame window = new JFrame("飞机大战");     //创建对象 并设定窗口的标题
        GamePanel g = new GamePanel();

        window.add(g);
        g.action();

        window.setSize(WIDTH, HEIGHT);                                      //设置窗口的长宽
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);     //设置窗口的关闭
        window.setLocationRelativeTo(null);                                 //窗口居中
        window.setVisible(true);                                            //显示窗口
    }
}
