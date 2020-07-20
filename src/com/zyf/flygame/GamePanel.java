package com.zyf.flygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TimerTask;

class GamePanel extends JPanel {

    private java.util.Timer time = new java.util.Timer();
    public static Hero hero = new Hero();
    private Bee bee = new Bee();
    private AirPlane airPlane = new AirPlane();
    private BigPlane bigPlane = new BigPlane();
    private Bullet bullet = new Bullet(getX(), getY());
    private ArrayList<FlyingObject> flyings;
    private ArrayList<FlyingObject> flyBullet;
    private int flyingIndex = 0;
    private int flyingBullet = 0;
    public final int START = 0;
    public final int RUNING = 1;
    public final int PAUSE = 2;
    public final int GAMEOVER = 3;

    private int state = START;

    GamePanel() {
        flyings = new ArrayList<>();
        flyBullet = new ArrayList<>();
    }


    public void action() {
        //TimerTask  需要重复的代码
        //long 定时器开始的时间
        //long 时间间隔
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNING) {
                    //创建敌机
                    createFlyingObject();
                    //创建敌机的移动
                    paintFlyMove();

                    //创建子弹
                    createBullet();
                    //创建子弹的移动
                    bulletMove();

                    //越界
                    outOfBoundsAction();

                    //碰撞
                    boonAction();
                }
                repaint();
            }
        }, 1000, 30);

        MouseAdapter Mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (state == START) {
                    state = RUNING;
                } else if (state == GAMEOVER) {
                    state = START;
                    hero = new Hero();
                    flyings = new ArrayList<>();
                    flyBullet = new ArrayList<>();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (state == PAUSE) {
                    state = RUNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (state == RUNING) {
                    state = PAUSE;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (state == RUNING) {
                    hero.setX(e.getX() - FlyGame.hero0.getWidth() / 2);
                    hero.setY(e.getY() - FlyGame.hero0.getHeight() / 2);
                    bullet.setX(hero.getX());
                    bullet.setY(hero.getY());
                }
            }
        };
        this.addMouseListener(Mouse);
        this.addMouseMotionListener(Mouse);
    }

    //显示绘图内容
    @Override
    public void paint(Graphics g) {
        //清除绘画内容
        super.paint(g);

        g.drawImage(FlyGame.background, 0, 0, this);

        paintFly(g);
        paintBullet(g);

        g.drawImage(hero.getImg(), hero.getX(), hero.getY(), this);

        Font font = new Font("幼圆", 1, 20);
        g.setFont(font);
        g.drawString("life:" + hero.getLife(), 10, 20);
        g.drawString("score:" + hero.getScore(), 10, 40);
        //paintEmber(g);

        switch (state) {
            case START:
                g.drawImage(FlyGame.start, 0, 0, this);
                break;
            case GAMEOVER:
                g.drawImage(FlyGame.gameover, 0, 0, this);
                break;
            case PAUSE:
                g.drawImage(FlyGame.pause, 0, 0, this);
                break;
        }
    }

    //创建多个飞行物
    private void createFlyingObject() {
        if (flyingIndex % 10 == 0) {
            int ran = (int) (Math.random() * 20);
            FlyingObject fly;
            if (ran == 0) {
                fly = new Bee();
            } else if (ran == 1 || ran == 2) {
                fly = new BigPlane();
            } else {
                fly = new AirPlane();
            }
            flyings.add(fly);
        }
        flyingIndex++;
    }

    //飞行物移动
    private void paintFlyMove() {
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject fly = flyings.get(i);
            fly.move();//设置飞行物的移动
        }
    }

    //显示飞行物
    private void paintFly(Graphics g) {
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject fly = flyings.get(i);
            g.drawImage(fly.getImg(), fly.getX(), fly.getY(), this);
        }
    }

    //敌机和子弹越界消失
    private void outOfBoundsAction() {
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject fly = flyings.get(i);
            if (fly.getY() > FlyGame.HEIGHT + 100) {
                flyings.remove(i);
                i--;
            }
        }
        for (int i = 0; i < flyBullet.size(); i++) {
            FlyingObject fly = flyBullet.get(i);
            if (fly.getY() < -FlyGame.HEIGHT) {
                flyBullet.remove(i);
                i--;
            }
        }
    }

    //创建子弹
    private void createBullet() {
        if (flyingBullet % 8 == 0) {
            Bullet[] bs = hero.shoot();
            for (int i = 0; i < bs.length; i++) {
                flyBullet.add(bs[i]);
            }
        }
        flyingBullet++;
    }

    //子弹移动
    private void bulletMove() {
        for (int i = 0; i < flyBullet.size(); i++) {
            FlyingObject fly = flyBullet.get(i);
            fly.move();//设置飞行物的移动
        }
    }

    //绘制子弹
    private void paintBullet(Graphics g) {
        for (int i = 0; i < flyBullet.size(); i++) {
            FlyingObject fly = flyBullet.get(i);
            g.drawImage(fly.getImg(), fly.getX(), fly.getY(), this);
        }
    }

//    public void paintEmber(Graphics g){
//        for (int i = 0; i < flyings.size(); i++) {
//            FlyingObject fly = flyings.get(i);
//            if(fly.getLife()==0){
//                if(fly instanceof AirPlane){
//                    g.drawImage(FlyGame.airplane_ember0, fly.getX(), fly.getY(), this);
//                    g.drawImage(FlyGame.airplane_ember1, fly.getX(), fly.getY(), this);
//                    g.drawImage(FlyGame.airplane_ember2, fly.getX(), fly.getY(), this);
//                    g.drawImage(FlyGame.airplane_ember3, fly.getX(), fly.getY(), this);
//                }
//            }
//
//        }
//    }

    //子弹和敌人的碰撞,以及英雄和敌机的碰撞
    private void boonAction() {
        for (int i = 0; i < flyBullet.size(); i++) {
            FlyingObject bul = flyBullet.get(i);
            for (int j = 0; j < flyings.size(); j++) {
                FlyingObject fly = flyings.get(j);
                //敌机和子弹的碰撞
                if (bul.getX() > fly.getX() && bul.getX() < fly.getX() + fly.getWidth()
                        && bul.getY() > fly.getY() && bul.getY() < fly.getY() + fly.getWidth()) {
                    flyBullet.remove(i);
                    fly.flyLife();
                    if (fly.getLife() == 0) {
                        flyings.remove(j);
                        if (fly instanceof Enemy) {
                            Enemy enemy = (Enemy) fly;
                            hero.addScore(enemy.getScore());
                        }
                        if (fly instanceof Award) {
                            Award award = (Award) fly;
                            if (award.getAwardType() == Award.ADD_LIFE)
                                hero.addLife();
                            else {
                                hero.addDoubleFire();
                            }
                        }
                    }
                    i--;
                    j--;
                    break;
                }
                //敌机和英雄机的碰撞
                if (hero.getX() + FlyGame.hero0.getWidth() / 2 > fly.getX()
                        && hero.getX() + FlyGame.hero0.getWidth() / 2 < fly.getX() + fly.getWidth()
                        && hero.getY() < fly.getY() && hero.getY() + FlyGame.hero0.getHeight() > fly.getY()) {
                    flyings.remove(j);
                    j--;
                    hero.setLife(hero.getLife() - 1);
                    if (hero.getLife() == 0) {
                        state = GAMEOVER;
                        break;
                    }
                }
            }
        }
    }
}


