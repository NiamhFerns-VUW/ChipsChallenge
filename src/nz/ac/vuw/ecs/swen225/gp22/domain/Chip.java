package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Chip is the player's character, it can move and interact with entities and tiles.
 */
public class Chip extends MovingEntity {
	public Chip(Direction facingDir){
		super(facingDir);
	}

	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Coord c) {
		return false;
	}

	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Coord c) {
		return false;
	}

	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}
}
