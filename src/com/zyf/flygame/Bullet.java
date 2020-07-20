package com.zyf.flygame;

/**
 * 子弹
 */
public class Bullet extends FlyingObject {

    private int speed;

    public Bullet(int x,int y) {
        super(x,y, FlyGame.bullet, 1);
        speed = 10;
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }
}
