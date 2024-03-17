package me.philip.actors;

import greenfoot.Actor;

public class Wall extends Actor{
    int blockPerLine;
    int blockSize;
    boolean right;
    
    public Wall(boolean right, int blockSize, int blockPerLine) {
        this.blockSize = blockSize;
        this.blockPerLine = blockPerLine;
        this.right = right;
    }
    
    public void generateWall() {
        int sizeWorldX = this.getWorld().getWidth();
        int sizeWorldY = this.getWorld().getHeight();
        
        int sizeX = (sizeWorldX - (blockSize * blockPerLine))/2;
        int posX = 0;
        if (right) posX = sizeX / 2;
        else posX = sizeWorldX - (sizeX / 2);
        
        getImage().scale(sizeX, sizeWorldY);
        setLocation(posX, sizeWorldY / 2);
    }
}
