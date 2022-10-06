package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Chip is the player's character, it can move and interact with entities and tiles.
 */
public class Chip extends MovingEntity {
	public Chip(){
		super(Direction.Down, new Coord(-1,-1));
	}
	public Chip(Direction facingDir, Coord c){
		super(facingDir, c);
	}

	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
		return false;
	}

	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Cell myCell) {
		return false;
	}

	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}
}
