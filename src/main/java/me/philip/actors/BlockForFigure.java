package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class BlockForFigure extends Actor{
    private int blockSize;
    private int[] midPosition = new int[2];
    
    //Variables for Movement
    public boolean shouldMove = false;
    private int moveTimer = 0;
    private int fallspeed = 20;
    
    //Variables vor Speed Block down
    private int fastSpeed = 2;
    private boolean isSpeedDown = false;
    
    private boolean allowedToTurn = false;
    private int allowedToTurnCounter = 4;
    
    private int[] positionsBlock = new int[4];

    boolean goToSleep = false;
    
    public BlockForFigure(int blockSize, int[] startPosition, int[] positionsB) {
        this.getImage().scale(26, 26);
        this.blockSize = blockSize;
        this.midPosition = startPosition.clone();
        this.positionsBlock = positionsB.clone();
    }

    public void act() {
        if (goToSleep) {
            this.getWorld().removeObject(this);
        }
        if (shouldMove) move();
        turn();
    }
    
    private void move() {
        checkBlockMove();
        moveDown();
    }

    private void turn() {
        if (!allowedToTurn) allowedToTurnCounter--;
        if (allowedToTurnCounter == 0) allowedToTurn = true;
    }
    
    private void checkBlockMove() {
        if (checkBottom() || checkBlock()) {
            shouldMove = false;
        }
    }
    
    private void moveDown() {
        if (moveTimer == 0 && shouldMove) {
            moveTimer = fallspeed;
            isSpeedDown = false;
            setLocation(getX(), getY() + blockSize);
            midPosition[1] += blockSize;
        } else if (moveTimer != 0 && shouldMove) {
            moveTimer--;
        }
    }
    
    private boolean checkBottom() {
        if (getY() >= this.getWorld().getHeight() - blockSize) return true;
        return false;
    }
    
    private boolean checkBlock() {
        //if (ignoreBelow) return false;
        boolean blockBelow = getObjectsAtOffset(0, blockSize, BlockForFigure.class).size() > 0;
        if (blockBelow) {
            int[] blockDown = {0, blockSize};
            return !isBlockFriendly(blockDown);
        }
        return blockBelow;
        
    }
    
    public void speedDown() {
        if (Greenfoot.isKeyDown("s") && moveTimer != 0 && !isSpeedDown) {
            isSpeedDown = true;
            moveTimer = fastSpeed;
        }
    }
    
    public boolean checkRightSide() {  //returns true if right side is free
        boolean checkRight = getObjectsAtOffset(blockSize, 0, null).size() == 0;
        if (!checkRight) {
            int[] blockRight = {blockSize, 0};
            return isBlockFriendly(blockRight);
        }
        return checkRight;
    }
    
    public boolean checkLeftSide() {  //returns true if right side is free
        boolean checkLeft = getObjectsAtOffset(-blockSize, 0, null).size() == 0;
        if (!checkLeft) {
            int[] blockLeft = {-blockSize, 0};
            return isBlockFriendly(blockLeft);
        }
        return checkLeft;
    }
    
    public void moveToRight() {
        midPosition[0] += blockSize;
        setLocation(getX() + blockSize, getY());
    }
    
    public void moveToLeft() {
        midPosition[0] -= blockSize;
        setLocation(getX() - blockSize, getY());
    }
    
    public int getRow() {
        int row = (this.getWorld().getHeight() + blockSize/2 - getY())/blockSize;
        return row;
    }
    
    public void delete() {
        goToSleep = true;
    }
    
    public void moveDownManuel() {
        setLocation(getX(), getY() + blockSize);
    }
    
    public boolean checkTurn(int nextPosNum) {
        if (!allowedToTurn) return false;
        int[] nextBlockPos = getNextBlockPos(positionsBlock[nextPosNum]);
        if (nextBlockPos[0] == getX() && nextBlockPos[1] == getY()) return true;
        
        int[] shift = {nextBlockPos[0] - getX(), getY() - nextBlockPos[1]};
        boolean isFree = getObjectsAtOffset(shift[0], shift[1], null).size() == 0;
        tester t = new tester();
        this.getWorld().addObject(t, nextBlockPos[0], nextBlockPos[1]);
        boolean isntAWall = t.isWall();
        t.bye();
        if (isFree && isntAWall) return true;
        else if (!isntAWall) return false;
        else {
            return isBlockFriendly(shift); 
        }
    }
    
    private boolean isBlockFriendly(int[] nextBlockPos) {
        try {
            BlockForFigure b = (BlockForFigure) getOneObjectAtOffset(nextBlockPos[0],nextBlockPos[1], BlockForFigure.class);
            if (b == null) {
                System.out.println("NULL");
                return false;
            }
            if (b.shouldMove) return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
        return false;
    }
    
    public void turnToSide(int nextPosNum) {
        int[] nextBlockPos = getNextBlockPos(positionsBlock[nextPosNum]);
        if (nextBlockPos[0] == getX() && nextBlockPos[1] == getY()) return;
        setLocation(nextBlockPos[0], nextBlockPos[1]);
    }
    
    private int[] getNextBlockPos(int position) {
        int[] outPos = new int[2];
        int posX;
        switch (position) {
            case 3:
            case 7:
                posX = midPosition[0] - blockSize - blockSize/2;
                outPos[0] = posX;
                break;
            case 1:
            case 4:
            case 8:
            case 11:
                posX = midPosition[0] - blockSize/2;
                outPos[0] = posX;
                break;
            case 2:
            case 5:
            case 9:
            case 12:
                posX = midPosition[0] + blockSize/2;
                outPos[0] = posX;
                break;
            case 6:
            case 10:
                posX = midPosition[0] + blockSize + blockSize/2;
                outPos[0] = posX;
                break;
        }
        
        int posY;
        switch (position) {
            case 1:
            case 2:
                posY = midPosition[1] - blockSize - blockSize/2;
                outPos[1] = posY;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                posY = midPosition[1] - blockSize/2;
                outPos[1] = posY;
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                posY = midPosition[1] + blockSize/2;
                outPos[1] = posY;
                break;
            case 11:
            case 12:
                posY = midPosition[1] + blockSize + blockSize/2;
                outPos[1] = posY;
                break;
        }
        return outPos;
    }
}
