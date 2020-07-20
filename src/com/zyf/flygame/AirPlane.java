package com.zyf.flygame;

/**
 * 敌机
 */
public class AirPlane extends FlyingObject implements Enemy {

    private int score = 5;
    private int speed;

    AirPlane() {
        super((int) (Math.random() * (FlyGame.WIDTH - FlyGame.airplane.getWidth())), -FlyGame.airplane.getHeight(), FlyGame.airplane, 1);
        speed = 5;
    }

    @Override
    public void move() {
        setY(getY() + speed);
    }

    public int getScore() {
        return score;
    }
}
