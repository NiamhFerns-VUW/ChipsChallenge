package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;

/**
 * Can be pushed around by moving into it - it blocks movement if it cannot be pushed forwards.
 */
public class MoveableBlock extends MovingEntity {

	public MoveableBlock() {
		super(Direction.None, new Coord(-1, -1));
	}
	public MoveableBlock(Coord c) {
		super(Direction.None,c);
	}
	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Cell myCell) {
		return move(d);
	}
	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}
	@Override
	public int drawHierarchy() {
		return 6;
	}

	public String toString() {
		return "b";
	}

	public Image getImage() {
		return Img.Movingblock.image;
	}
}
