package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

public class Figure1 extends Actor {
    //TODO: remove unused stuff
    int blockSize;
    BlockSpawner mommy;
    World w;
    BlockForFigure[] figure = new BlockForFigure[4];
    
    private int moveTimer = 0;
    private int maxMoveTimer = 10;
    private boolean endMovement = false;
    private boolean checkSides = true;
    
    private int turnTimer = 0;
    private int maxTurnTimer = 10;
    
    private int startX = 300;
    private int startY;
    private int[] startPos = new int[2];
    
    public Figure1(int blockSize, BlockSpawner mommy) {
        this.blockSize = blockSize;
        this.mommy = mommy;
        this.startY = blockSize;
        startPos[0] = startX;
        startPos[1] = startY;
        TestBlock();
        TestBlock2();
        TestBlock3();
        TestBlock4();
    }
    
    public void act() {
        if (checkSides) {
            checkSides = false;
            for (BlockForFigure b : figure) {
                //b.setIngoreBlocks();
                b.shouldMove = true;
            }
        }
        checkMovementState();
        if (!endMovement) {
            moveBlockSideways();
            turnBlock();
            moveBlockFast();
        }
    }
    
    private void TestBlock() {
        int[] positions = {4, 4, 4, 4};
        BlockForFigure b = new BlockForFigure(blockSize, startPos, positions, mommy);
        int spawnX = (mommy.getWorld().getWidth() + blockSize) / 2;
        mommy.getWorld().addObject(b, spawnX, 0);
        figure[0] = b;        
    }
    
    private void TestBlock2() {
        int[] positions = {5, 5, 5, 5};
        BlockForFigure b = new BlockForFigure(blockSize, startPos, positions, mommy);
        int spawnX = (mommy.getWorld().getWidth() + blockSize) / 2;
        mommy.getWorld().addObject(b, spawnX - blockSize, 0);
        figure[1] = b;        
    }
    
    private void TestBlock3() {
        int[] positions = {8, 8, 8, 8};
        BlockForFigure b = new BlockForFigure(blockSize, startPos, positions, mommy);
        int spawnX = (mommy.getWorld().getWidth() + blockSize) / 2;
        mommy.getWorld().addObject(b, spawnX, blockSize);
        figure[2] = b;        
    }
    
    private void TestBlock4() {
        int[] positions = {9, 9, 9, 9};
        BlockForFigure b = new BlockForFigure(blockSize, startPos, positions, mommy);
        int spawnX = (mommy.getWorld().getWidth() + blockSize) / 2;
        mommy.getWorld().addObject(b, spawnX - blockSize, blockSize);
        figure[3] = b;        
    }

    private void moveBlockSideways() {
        if (Greenfoot.isKeyDown("a") && moveTimer == 0) {
            moveTimer = maxMoveTimer;
            for (BlockForFigure b : figure) {
                if (!b.checkLeftSide()) return;
            }
            for (BlockForFigure b : figure) {
                b.moveToLeft();
            }
        }
        else if (Greenfoot.isKeyDown("d") && moveTimer == 0) {
            moveTimer = maxMoveTimer;
            for (BlockForFigure b : figure) {
                if (!b.checkRightSide()) return;
            }
            for (BlockForFigure b : figure) { 
                b.moveToRight();
            }
        }
        else if (moveTimer != 0) moveTimer--;
    }
    
    private void turnBlock() {
        if (Greenfoot.isKeyDown("q") && turnTimer == 0) {
            //turn left
            for (BlockForFigure b : figure) {
                //b.checkLeftTurn(figure);
            }
            turnTimer = maxTurnTimer;
        }
        else if (turnTimer != 0) turnTimer--;
    }
    
    private void moveBlockFast() {
        if (Greenfoot.isKeyDown("s")) {
            for (BlockForFigure b : figure) {
                b.speedDown();
            }
        }
    }
    
    private void checkMovementState() {
        for (BlockForFigure b : figure) {
            if (!b.shouldMove) endMovement = true;
        }
        if (endMovement) endMovementForAll();
    }
    
    private void endMovementForAll() {
        for (BlockForFigure b : figure) {
            b.shouldMove = false;
        }
        sendAllData();
        generateNewBlock();
        killYourself();
    }
    
    private void sendAllData() {
        int[] rows = new int[4];
        for (int i = 0; i < 4; i++) {
            rows[i] = figure[i].getRow();
        }
        mommy.saveRowF(figure, rows);
    }
    
    private void generateNewBlock() {
        mommy.spawnBlockAutomatic();
    }
    
    private void killYourself() {
        this.getWorld().removeObject(this);
    }
}
