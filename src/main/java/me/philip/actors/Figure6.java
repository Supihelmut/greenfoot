package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

public class Figure6 extends Actor{
    int blockSize;
    BlockSpawner mommy;
    World w;
    BlockForFigure[] figure = new BlockForFigure[4];
    
    private int moveTimer = 0;
    private int maxMoveTimer = 10;
    private boolean endMovement = false;
    private boolean checkSides = true;
    
    private int turnTimer = 0;
    private int maxTurnTimer = 20;
    
    private int startX = 300;
    private int startY;
    private int[] startPos = new int[2];
    
    private int positionsState = 0;
    
    public Figure6(int blockSize, BlockSpawner mommy) {
        this.blockSize = blockSize;
        this.mommy = mommy;
        this.startY = blockSize/2;
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
                b.shouldMove = true;
            }
        }
        checkMovementState();
        if (!endMovement) {
            moveBlockSideways();
            inputTurnBlock();
            moveBlockFast();
        }
    }
    
    private void TestBlock() {
        //int[] positions = {0, 0, 0, 8};
        int[] positions = {5, 1, 5, 1};
        BlockForFigure b = new BlockForFigure(blockSize, startPos, positions);
        int spawnX = (mommy.getWorld().getWidth() + blockSize) / 2;
        mommy.getWorld().addObject(b, spawnX + (2 * blockSize), 0);
        figure[0] = b;        
    }
    
    private void TestBlock2() {
        //int[] positions = {7, 1, 6, 12};
        int[] positions = {6, 4, 6, 4};
        BlockForFigure b = new BlockForFigure(blockSize, startPos, positions);
        int spawnX = (mommy.getWorld().getWidth() + blockSize) / 2;
        mommy.getWorld().addObject(b, spawnX + blockSize, 0);
        figure[1] = b;        
    }
    
    private void TestBlock3() {
        //int[] positions = {0, 0, 9, 0};
        int[] positions = {8, 5, 8, 5};
        BlockForFigure b = new BlockForFigure(blockSize, startPos, positions);
        int spawnX = (mommy.getWorld().getWidth() + blockSize) / 2;
        mommy.getWorld().addObject(b, spawnX + blockSize, blockSize);
        figure[2] = b;        
    }
    
    private void TestBlock4() {
        //int[] positions = {0, 5, 0, 0};
        int[] positions = {9, 9, 9, 9};
        BlockForFigure b = new BlockForFigure(blockSize, startPos, positions);
        int spawnX = (mommy.getWorld().getWidth() + blockSize) / 2;
        System.out.println("spawnX: " + spawnX);
        mommy.getWorld().addObject(b, spawnX, blockSize);
        figure[3] = b;        
    }
    
    private void moveBlockSideways() {
        if (Greenfoot.isKeyDown("a") && moveTimer == 0) {
            if (!movementLeft()) return;
            moveTimer = maxMoveTimer;
        }
        else if (Greenfoot.isKeyDown("d") && moveTimer == 0) {
            if (!movementRight()) return;
            moveTimer = maxMoveTimer;
        }
        else if (moveTimer != 0) moveTimer--;
    }
    
    private boolean movementRight() {
        for (BlockForFigure b : figure) {
                if (!b.checkRightSide()) return false;
            }
        for (BlockForFigure b : figure) { 
            b.moveToRight();
        }
        return true;
    }
    
    private boolean movementLeft() {
        for (BlockForFigure b : figure) {
                if (!b.checkLeftSide()) return false;
            }
        for (BlockForFigure b : figure) {
            b.moveToLeft();
        }
        return true;
    }
    
    private void inputTurnBlock() {
        if (Greenfoot.isKeyDown("e") && turnTimer == 0) {
            //turn right
            positionsState += 1;
            if (positionsState == 4) positionsState = 0;
            if (!turnBlock()) {
                if (positionsState != 0) positionsState -= 1;
                else positionsState = 3;
            };
        }
        else if (Greenfoot.isKeyDown("q") && turnTimer == 0) {
            //turn left
            positionsState -= 1;
            if (positionsState == -1) positionsState = 3;
            if (!turnBlock()) {
                if (positionsState != 3) positionsState += 1;
                else positionsState = 0;
            };
        }
        else if (turnTimer != 0) turnTimer--;
    }
    
    private boolean turnBlock() {
        for (BlockForFigure b : figure) {
            if (!b.checkTurn(positionsState)) {
                return false;
            }
        }
        for (BlockForFigure b : figure) {
            b.turnToSide(positionsState);
        }
        turnTimer = maxTurnTimer;
        return true;
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
