package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class FigureSpawner extends Actor{
    //TODO: nicht random, sondern in nem sack
    //TODO: noch eine vorschau für den nächsten Block
    int figureCount = 7;
    int blockSize = 26;
    BlockSpawner mommy;
    
    public void generateFigure(BlockSpawner mommy) {
        int figure = Greenfoot.getRandomNumber(figureCount);
        this.mommy = mommy;
        spawnFigure(figure);
    }
    
    private void spawnFigure(int figure) {
        switch (figure) {
            case 0:
                //System.out.println("Figure 1");
                Figure1 f1 = new Figure1(blockSize, mommy);
                this.getWorld().addObject(f1, 0, 0);
                break;
            case 1:
                Figure2 f2 = new Figure2(blockSize, mommy);
                this.getWorld().addObject(f2, 0, 0);
                break;
            case 2:
                Figure3 f3 = new Figure3(blockSize, mommy);
                this.getWorld().addObject(f3, 0, 0);
                break;
            case 3:
                Figure4 f4 = new Figure4(blockSize, mommy);
                this.getWorld().addObject(f4, 0, 0);
                break;
            case 4:
                Figure5 f5 = new Figure5(blockSize, mommy);
                this.getWorld().addObject(f5, 0, 0);
                break;
            case 5:
                Figure6 f6 = new Figure6(blockSize, mommy);
                this.getWorld().addObject(f6, 0, 0);
                break;
            case 6:
                Figure7 f7 = new Figure7(blockSize, mommy);
                this.getWorld().addObject(f7, 0, 0);
                break;
        }
    }
}
