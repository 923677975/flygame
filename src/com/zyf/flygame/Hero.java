package com.zyf.flygame;

import java.awt.image.BufferedImage;

/**
 * 自己的飞机
 */

public class Hero extends FlyingObject {

    private int score = 0;
    private int count = 0;

    private BufferedImage[] heroImg = {FlyGame.hero0, FlyGame.hero1};

    Hero() {
        super(150, 400, FlyGame.hero0, 3);
    }

    @Override
    public void move() {
        count++;
        setImg(heroImg[count % 2]);
    }

    public void addScore(int score) {
        this.score = this.score + score;
    }

    public int getScore() {
        return score;
    }

    // 新增变量, 标记双倍火力剩余次数
    private int doubleFire = 0;

    public void addDoubleFire() {
        doubleFire += 20;
    }

    // 发射子弹, 生成新的子弹, 并返回
    public Bullet[] shoot() {
        Bullet[] bullets;
        if (doubleFire == 0) {
            bullets = new Bullet[1];
            bullets[0] = new Bullet(this.getX() + this.getWidth() / 2, this.getY());
        } else {
            bullets = new Bullet[2];
            bullets[0] = new Bullet(this.getX() + this.getWidth() / 4, this.getY());
            bullets[1] = new Bullet(this.getX() + this.getWidth() / 4 * 3, this.getY());
            doubleFire--;
        }
        return bullets;
    }

    public void addLife() {
        this.setLife(this.getLife() + 1);
    }
}
