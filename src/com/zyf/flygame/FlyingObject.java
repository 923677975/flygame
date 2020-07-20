package com.zyf.flygame;

import java.awt.image.BufferedImage;

/**
 * 飞行物 所有飞行物的父类
 */
public abstract class FlyingObject {

    private int x;
    private int y;
    private BufferedImage img;
    private int width;
    private int height;
    private int life;

    FlyingObject(int x, int y, BufferedImage img,int life) {
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.x = x;
        this.y = y;
        this.life =life;
    }
    public int flyLife(){
        return life--;
    }

    public abstract void move();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
