package me.philip.worlds;

// WARNING: This file is auto-generated and any changes to it will be overwritten
import greenfoot.World;
import me.philip.actors.Bean;

/**
 * 
 */
public class MyWorld extends World {

	/**
	 * Constructor for objects of class MyWorld.
	 */
	public MyWorld() {
		super(600, 400, 1);

		var bean = new Bean();
		addObject(bean, 300, 200);
	}
}
