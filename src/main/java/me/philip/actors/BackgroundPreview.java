package me.philip.actors;

import greenfoot.Actor;

public class BackgroundPreview extends Actor{
    private int size = 26 * 5;
    int offsetX = 200;
    int offsetY = 100;

    public BackgroundPreview() {
        this.getImage().scale(size, size);
    }

    public void moveToPlace() {
        int coordX = 313 + offsetX;
        int coordY = offsetY;
        setLocation(coordX, coordY);
    }
}
