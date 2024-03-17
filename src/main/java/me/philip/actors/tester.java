package me.philip.actors;

import greenfoot.Actor;

public class tester extends Actor{
    public boolean isWall() {
        return !isTouching(Wall.class);
    }
    
    public void bye() {
        this.getWorld().removeObject(this);
    }
}
