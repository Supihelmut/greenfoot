package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class BlockSpawner extends Actor {
    //TODO: Add a possibility to losse
    //TODO: Add a score system
    //TODO: Make the game faster with higher score
    
    BlockForFigure[][] rowsF = new BlockForFigure[20][10];
    int[] rowCounterF = new int[20];
    
    FigureSpawner daddySpawner;
    int spawnTime = 20;
    int spawnCounter = 0;
    
    boolean checkRowsNext = false;
    int rowCheckerTimer = -1;
    
    int[] fullRows = new int[20];
    int moveBlocksDown = 0;
    
    public BlockSpawner(FigureSpawner daddySpawner) {
        this.daddySpawner = daddySpawner;
    }
    
    public void act()
    {
        SpawnManuel();
        if (spawnCounter != 0) spawnCounter--;
        if (rowCheckerTimer > 0) rowCheckerTimer--;
        if (rowCheckerTimer == 0) checkAllRows();
        //checkRowsF();
    }
    
    private void SpawnManuel() {
        if (Greenfoot.getKey() == "space") {
            spawnBlock();
        }
    }
    
    public void spawnBlockAutomatic() {
        spawnBlock();
    }
    
        private void spawnBlock() {
        if (spawnCounter == 0) {
            daddySpawner.generateFigure(this);
            spawnCounter = spawnTime;
            //System.out.println("Spawn one");
        }
    }
    
    public void saveRowF(BlockForFigure[] inputs, int[] rows) {
        for (int i = 0; i < 4; i++) {
            int row = rows[i];
            int counter = rowCounterF[row];
            rowsF[row][counter] = inputs[i];
            rowCounterF[row]++;
        }
        rowCheckerTimer = 5;
    }
    
    private void checkAllRows() {
        int countPos = 0;
        for (int i = 0; i < 20; i++) { 
            if (rowCounterF[i] == 10) {
                fullRows[countPos] = i + 1;
                countPos++;
                moveBlocksDown++;
            }
        }
        rowCheckerTimer = -1;
        deleteBlocks();
    }
    
    private void deleteBlocks() {
        for (int i = 0; i < fullRows.length; i++) {
            if (fullRows[i] != 0) {
                for (BlockForFigure b : rowsF[fullRows[i] - 1]) {
                        b.delete();
                }
                rowCounterF[fullRows[i] - 1] = 0;
            }
        }
        moveAllBlocksDown();
    }
    
    private void moveAllBlocksDown() {
        int offset = 0;
        for (int row : fullRows) {
            if (row != 0) {
                for (int i = row - offset; i < 20; i++) {
                    for (BlockForFigure b : rowsF[i]) {
                        if (b != null) b.moveDownManuel();
                    }
                    rowsF[i - 1] = rowsF[i];
                    rowCounterF[i - 1] = rowCounterF[i];
                }
                rowCounterF[19] = 0;
                rowsF[19] = new BlockForFigure[10];
                offset++;
            }
        }
        fullRows = new int[20];
    }
}
