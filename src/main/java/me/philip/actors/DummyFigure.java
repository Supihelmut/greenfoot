package me.philip.actors;

import greenfoot.Actor;

public class DummyFigure extends Actor {
    private int mid = 313;
    private int blockSize = 26;
    BlockSpawner mommy;
    DummyBlock[] dummy = new DummyBlock[4];

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


    public DummyFigure(BlockSpawner mommy, int figure) {
        this.mommy = mommy;
        int extraOffsetX = 0;
        int extraOffsetY = -13;
        switch (figure) {
            case 0:
                extraOffsetX = 13;
                break;
            case 4:
                extraOffsetX = 26;
                break;
            case 5:
                extraOffsetX = -13;
            case 6:
                extraOffsetX = 13;
                extraOffsetY = 0;
                break;
        }


        for (int i = 0; i < 4; i++) {
            int offsetX = 200;
            int offsetY = 100;
            int[] coords = blockCoords[figure][i];
            DummyBlock d = new DummyBlock(mommy);
            mommy.getWorld().addObject(d, coords[0] + offsetX + extraOffsetX, coords[1] + offsetY + extraOffsetY);
            dummy[i] = d;
        }
    }

    public void delete() {
        for (DummyBlock d : dummy) {
            d.die();
        }
        mommy.getWorld().removeObject(this);
    }
}
