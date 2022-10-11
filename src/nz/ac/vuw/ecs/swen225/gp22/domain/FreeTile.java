package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;

/**
 * The FreeTile is an empty tile that doesn't prevent movement.
 */
public class FreeTile {
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}

	public boolean afterMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}

	public Image getImage() {
		return Img.Freetile.image;
	}

	public String toString() {
		return "_";
	}
}
