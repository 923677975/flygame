package com.zyf.flygame;

/**
 * 大敌机
 */
public class BigPlane extends FlyingObject implements Enemy, Award {

    private int speed;
    private int score = 10;
    private int awardType;

    BigPlane() {
        super((int) (Math.random() * (FlyGame.WIDTH - FlyGame.bigplane.getWidth())),
                -FlyGame.bigplane.getHeight(), FlyGame.bigplane, 5);
        speed = 2;
        awardType = (int) (Math.random() * 2);
    }

    @Override
    public void move() {
        setY(getY() + speed);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int getAwardType() {
        return awardType;
    }
}
