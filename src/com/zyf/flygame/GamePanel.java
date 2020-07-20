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

    //定义一个计时器
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
                    //越界处理
                    outOfBoundsAction();
                    //碰撞
                    boonAction();
                }
                //重新绘制
                repaint();
            }
        }, 1000, 30);

        //创建一个鼠标的适配器
        MouseAdapter Mouse = new MouseAdapter() {
            //重写鼠标点击
            @Override
            public void mouseClicked(MouseEvent e) {
                //判断状态
                if (state == START) {
                    state = RUNING;
                } else if (state == GAMEOVER) {     //若游戏结束
                    state = START;
                    //重新生成英雄机、飞行物、子弹
                    hero = new Hero();
                    flyings = new ArrayList<>();
                    flyBullet = new ArrayList<>();
                }
            }

            //重写鼠标移入窗口  实现继续游戏
            @Override
            public void mouseEntered(MouseEvent e) {
                if (state == PAUSE) {
                    state = RUNING;
                }
            }

            //重写鼠标移出窗口  实现暂停
            @Override
            public void mouseExited(MouseEvent e) {
                if (state == RUNING) {
                    state = PAUSE;
                }
            }

            //重写鼠标的移动   并实现使用鼠标控制英雄机
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
        //接受鼠标事件
        this.addMouseListener(Mouse);
        this.addMouseMotionListener(Mouse);
    }

    //创建多个飞行物
    private void createFlyingObject() {
        //利用flyingIndex来控制飞行物的生成数量
        if (flyingIndex % 10 == 0) {
            //随机生成小飞机、大飞机、蜜蜂
            int ran = (int) (Math.random() * 20);
            FlyingObject fly;
            if (ran == 0) {
                fly = new Bee();
            } else if (ran == 1 || ran == 2) {
                fly = new BigPlane();
            } else {
                fly = new AirPlane();
            }
            //将对应的值存入ArrayList
            flyings.add(fly);
        }
        flyingIndex++;
    }

    //实现飞行物移动
    private void paintFlyMove() {
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject fly = flyings.get(i);
            fly.move();
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

    //实现子弹的移动
    private void bulletMove() {
        for (int i = 0; i < flyBullet.size(); i++) {
            FlyingObject fly = flyBullet.get(i);
            fly.move();
        }
    }

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
                        //利用接口实现分数的累加
                        if (fly instanceof Enemy) {
                            Enemy enemy = (Enemy) fly;
                            hero.addScore(enemy.getScore());
                        }
                        //利用接口实现奖励机制
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

    //显示绘图内容
    @Override
    public void paint(Graphics g) {
        //清除绘画内容
        super.paint(g);
        //绘制背景
        g.drawImage(FlyGame.background, 0, 0, this);
        //绘制飞行物
        paintFly(g);
        //绘制子弹
        paintBullet(g);
        //绘制英雄机的移动
        g.drawImage(hero.getImg(), hero.getX(), hero.getY(), this);
        //设置字体格式
        Font font = new Font("幼圆", 1, 20);
        g.setFont(font);
        //在左上角输出文字
        g.drawString("life:" + hero.getLife(), 10, 20);
        g.drawString("score:" + hero.getScore(), 10, 40);
        //判断窗口当前状态，并输出相应的图片
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

    //绘图显示飞行物
    private void paintFly(Graphics g) {
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject fly = flyings.get(i);
            g.drawImage(fly.getImg(), fly.getX(), fly.getY(), this);
        }
    }

    //绘制子弹
    private void paintBullet(Graphics g) {
        for (int i = 0; i < flyBullet.size(); i++) {
            FlyingObject fly = flyBullet.get(i);
            g.drawImage(fly.getImg(), fly.getX(), fly.getY(), this);
        }
    }
}


