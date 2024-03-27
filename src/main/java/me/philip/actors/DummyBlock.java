package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Color;

public class DummyBlock extends Actor {
    BlockSpawner mommy;
    public DummyBlock(BlockSpawner mommy) {
        this.mommy = mommy;
        this.getImage().scale(26, 26);
    }

    public void setColor(Color color) {
        this.getImage().setColor(color);
        this.getImage().fill();
    }

    public void die() {
        mommy.getWorld().removeObject(this);
    }
}
