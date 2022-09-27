package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;

/**
 * Each Cell keeps track of the tile it is made up of and the entities on it.
 */
public class Cell {
	private FreeTile storedTile;
	private final Coord coordinate;
	private ArrayList<Entity> entities;
	
	public Cell(FreeTile storedTile, Coord coordinate) {
		this.storedTile = storedTile;
		this.coordinate = coordinate;
		entities = new ArrayList<Entity>();
	}
	public boolean beforeMoveInto(MovingEntity e, Direction d) {
		return storedTile.onMoveInto(e, d) && entities.stream().allMatch(a -> a.interactBefore(e, d, coordinate));
	}

	public boolean afterMoveInto(MovingEntity e, Direction d) {
		return entities.stream().allMatch(a -> a.interactAfter(e, d, coordinate));
	}
	
	public FreeTile getStoredTile() {
		return storedTile;
	}
	public void setStoredTile(FreeTile newTile) {
		storedTile = newTile;
	}
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void removeEntity(Entity e) {
		entities = (ArrayList<Entity>) entities.stream().filter(entity -> entity != e).toList();
	}
}
