package me.philip.actors;

import greenfoot.Actor;

public class DummyBlock extends Actor {
    BlockSpawner mommy;
    public DummyBlock(BlockSpawner mommy) {
        this.mommy = mommy;
        this.getImage().scale(26, 26);
    }

    public void die() {
        mommy.getWorld().removeObject(this);
    }
}
