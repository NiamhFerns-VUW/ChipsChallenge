package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Teleporters teleport the target to a random different teleporter of the same colour when they are stepped on.
 */
public class Teleporter extends FreeTile {
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		throw new Error("Code not done!");	//TODO
	}

	@Override
	public boolean afterMoveInto(MovingEntity e, Direction d, Cell myCell) {
		throw new Error("Code not done!");	//TODO

		/*
		List<Cell> teleCells = Arrays.stream(e.level.cells)
				.flatMap(cArray -> Arrays.stream(cArray))
				.filter(c -> c.getStoredTile() instanceof Teleporter t && c.getStoredTile() != this).toList();

		if (teleCells.size() == 0) return true;

		Cell newCell = teleCells.get((int) (Math.random() * teleCells.size()));

		myCell.removeEntity(e);
		newCell.getEntities().add(e);
		e.coords = newCell.*/
	}

	public String toString() {
		return "T";
	}
}
