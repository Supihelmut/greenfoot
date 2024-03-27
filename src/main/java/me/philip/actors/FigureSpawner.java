package me.philip.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class FigureSpawner extends Actor {
    int figureCount = 7;
    int blockSize = 26;
    BlockSpawner mommy;

    static int lastFigure = 0;
    static DummyFigure lastDummy = null;

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
        DummyFigure d = new DummyFigure(mommy, figure);

        if (lastDummy == null) {
            lastDummy = d;
            lastFigure = figure;
            generateFigure(mommy);
        } else {
            lastDummy.delete();
            lastDummy = d;
            Figure f = new Figure(lastFigure, mommy);
            mommy.getWorld().addObject(f, 0, 0);
            lastFigure = figure;
        }
    }
}
