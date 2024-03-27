package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Figure extends Actor {
    private int figureNum;
    private int blockNum = 0;
    
    BlockSpawner mommy;
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

    private int[][][] blockValues =  {
        //Figure 1 Values
        {
            {4, 4, 4, 4},
            {5, 5, 5, 5},
            {8, 8, 8, 8},
            {9, 9, 9, 9}
        }, 
        //Figure 2 Values
        {
            {4, 1, 4, 5},
            {7, 4, 5, 8},
            {8, 5, 6, 9},
            {9, 8, 9, 12}
        },
        //Figure 3 Values
        {
            {5, 1, 4, 4},
            {7, 4, 5, 5},
            {8, 8, 6, 9},
            {9, 9, 8, 12}
        },
        //Figure 4 Values
        {
            {4, 4, 3, 2},
            {8, 5, 4, 5},
            {9, 8, 5, 8},
            {10, 11, 9, 9}
        },
        //Figure 5 Values
        {
            {3, 2, 3, 2},
            {4, 4, 4, 4},
            {8, 5, 8, 5},
            {9, 8, 9, 8}
        },
        //Figure 6 Values
        {
            {5, 1, 5, 1},
            {6, 4, 6, 4},
            {8, 5, 8, 5},
            {9, 9, 9, 9}
        },
        //Figure 7 Values
        {
            {7, 2, 7, 2},
            {8, 5, 8, 5},
            {9, 9, 9, 9},
            {10, 12, 10, 12}
        }
    };

    private int mid = 313;
    private int blockSize = 26;

    private int[][][] blockCoords = {
        //Figure 1 Coords
        {
            {mid, 0},
            {mid - blockSize, 0},
            {mid, blockSize},
            {mid - blockSize, blockSize}
        },
        //Figure 2 Coords
        {
            {mid, 0},
            {mid - blockSize, blockSize},
            {mid, blockSize},
            {mid + blockSize, blockSize}
        },
        //Figure 3 Coords
        {
            {mid + blockSize, 0},
            {mid - blockSize, blockSize},
            {mid, blockSize},
            {mid + blockSize, blockSize}
        },
        //Figure 4 Coords
        {
            {mid - blockSize, 0},
            {mid - blockSize, blockSize},
            {mid, blockSize},
            {mid + blockSize, blockSize}
        },
        //Figure 5 Coords
        {
            {mid - (2 * blockSize), 0},
            {mid - blockSize, 0},
            {mid - blockSize, blockSize},
            {mid, blockSize}
        },
        //Figure 6 Coords
        {
            {mid, 0},
            {mid - blockSize, 0},
            {mid - blockSize, blockSize},
            {mid - (2 * blockSize), blockSize}
        },
        //Figure 7 Coords
        {
            {mid - (2 * blockSize), 0},
            {mid - blockSize, 0},
            {mid, 0},
            {mid + blockSize, 0}
        }
    };

    public Figure(int figureNum, BlockSpawner mommy) {
        this.figureNum = figureNum;
        this.mommy = mommy;
        this.startY = blockSize/2;
        startPos[0] = startX;
        startPos[1] = startY;
        createBlock();
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

    public void createBlock() {
        for (int i = 0; i < 4; i++) {
            int[] positions = blockValues[figureNum][blockNum];
            int[] coords = blockCoords[figureNum][blockNum];
            BlockForFigure b = new BlockForFigure(blockSize, startPos, positions);
            mommy.getWorld().addObject(b, coords[0], coords[1]);
            figure[blockNum] = b;
            blockNum++;
        }
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
