package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Teleporters teleport the target to a random different teleporter of the same colour when they are stepped on.
 */
public class Teleporter extends FreeTile {
	public String teleColour;

	public Teleporter(String teleColour) {
		this.teleColour = teleColour;
	}
	public Teleporter() {
		this.teleColour = "";
	}
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		return true;
	}

	@Override
	public boolean afterMoveInto(MovingEntity e, Direction d, Cell myCell) {
		if (!(e instanceof Chip)) return true;

		Cell[][] cells = e.level.cells;
		List<Coord> otherTeleporter = new ArrayList<Coord>();

		IntStream.range(0, cells.length).forEach(row -> {
			IntStream.range(0, cells[row].length).filter(col -> {
				return cells[row][col].getStoredTile() instanceof Teleporter t
						&& t.teleColour == this.teleColour && t != this;})
					.forEach(col -> otherTeleporter.add(new Coord(row, col)));
		});

		if (otherTeleporter.size() == 0) throw new Error("Teleporter missing destination!");
		if (otherTeleporter.size() > 1) throw new Error("Too many teleporter destinations! Max 1.");

		Cell newCell = cells[otherTeleporter.get(0).x()][otherTeleporter.get(0).y()];

		myCell.removeEntity(e);
		newCell.getEntities().add(e);
		e.coords = otherTeleporter.get(0);

		return true;
	}

	public String toString() {
		return "T";
	}

	public Image getImage() {
		throw new Error("No Teleporter image yet!");

		//return Img.Freetile.image;
	}
}
