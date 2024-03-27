package me.philip.worlds;

// WARNING: This file is auto-generated and any changes to it will be overwritten
import greenfoot.World;
import me.philip.actors.BackgroundPreview;
import me.philip.actors.BlockSpawner;
import me.philip.actors.FigureSpawner;
import me.philip.actors.Wall;

/**
 * 
 */
public class MyWorld extends World {

	public MyWorld() {
		// Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 500, 1); 
        FigureSpawner FSpawn = new FigureSpawner();
        addObject(FSpawn, 0, 0);
        
        BlockSpawner b = new BlockSpawner(FSpawn);
        addObject(b, 0, 0);
        
        Wall wallright = new Wall(true, 26, 10);
        Wall wallleft = new Wall(false, 26, 10);
        BackgroundPreview bPreview = new BackgroundPreview();
        
        addObject(wallright, 0, 0);
        addObject(wallleft, 0, 0);
        addObject(bPreview, 0, 0);
        
        wallright.generateWall();
        wallleft.generateWall();
        bPreview.moveToPlace();
	}
}
