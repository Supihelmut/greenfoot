package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class FigureSpawner extends Actor{
    //TODO: noch eine vorschau für den nächsten Block
    int figureCount = 7;
    int blockSize = 26;
    BlockSpawner mommy;

    int[] leftFiguresArr = new int[7];
    int leftFiguresCounter = 0;
    
    public void generateFigure(BlockSpawner mommy) {
        int figure = generateRandomNumber();
        this.mommy = mommy;
        spawnFigure(figure);
    }

    private int generateRandomNumber() {
        if (leftFiguresCounter == 0) {
            leftFiguresCounter = 7;
            int[] newArr = {1, 2, 3, 4, 5, 6, 7, 8};
            leftFiguresArr = newArr.clone();
        }
        int outputNum = 0;
        do {
            outputNum = leftFiguresArr[Greenfoot.getRandomNumber(7)];
        } while (outputNum == 0);
        leftFiguresCounter--;
        leftFiguresArr[outputNum - 1] = 0;
        return outputNum - 1;
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
