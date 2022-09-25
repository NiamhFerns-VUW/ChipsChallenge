package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Arrays;

/**
 * When Chip steps onto the Treasure's cell, it is picked up and the number of remaining treasures decreases, unlocking
 * the ExitLocks once it reaches 0.
 */
public class Treasure implements Entity {
	@Override
	public boolean interactBefore(MovingEntity e, Direction d, Coord c) {
		return true;
	}

	@Override
	public boolean interactAfter(MovingEntity e, Direction d, Coord c) {
		e.level.cells[c.x()][c.y()].removeEntity(this);
		e.level.setRemainingTreasures(e.level.getRemainingTreasures()-1);

		if (e.level.getRemainingTreasures() == 0) {	// if there are no more treasures left, the ExitLocks disappear
			Arrays.stream(e.level.cells)
					.flatMap(cl-> Arrays.stream(cl))
					.filter(cell->cell.getStoredTile() instanceof ExitLock)
					.forEach(cell->cell.setStoredTile(new FreeTile(cell)));
		}

		return true;
	}
	@Override
	public int drawHierarchy() {
		throw new Error("Code not done!");	//TODO
	}

}
