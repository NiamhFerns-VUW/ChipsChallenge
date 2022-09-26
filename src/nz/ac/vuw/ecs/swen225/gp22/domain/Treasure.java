package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * When Chip steps onto the Treasure's cell, it is picked up and the number of remaining treasures decreases, unlocking
 * the ExitLocks once it reaches 0.
 */
public class Treasure implements Entity {
	@Override
	public boolean interact(Entity e, Direction d, Coord c) {
		throw new Error("Code not done!");	//TODO
	}
	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}

}
