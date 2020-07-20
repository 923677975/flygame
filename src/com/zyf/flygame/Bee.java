package com.zyf.flygame;

/**
 * 蜜蜂
 */
public class Bee extends FlyingObject implements Award{

    private int x_speed;
    private int y_speed;
    private int awardType;
    Bee() {
        super((int) (Math.random() * (FlyGame.WIDTH - FlyGame.bee.getWidth())), -FlyGame.bee.getHeight(), FlyGame.bee, 1);
        y_speed = 2;
        x_speed = 3;
        awardType = (int) (Math.random() * 2);
    }

    @Override
    public void move() {
        setY(getY() + y_speed);
        setX(getX() + x_speed);
        if (getX() > FlyGame.WIDTH - FlyGame.bee.getWidth() - 10) {
            x_speed = -x_speed;
        } else if (getX() < 0) {
            x_speed = -x_speed;
        }
    }

    @Override
    public int getAwardType() {
        return awardType;
    }
}
