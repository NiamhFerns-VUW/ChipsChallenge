package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;

/**
 * Each Cell keeps track of the tile it is made up of and the entities on it.
 */
public class Cell {
	private FreeTile storedTile;
	private ArrayList<Entity> entities;
	
	public Cell(FreeTile storedTile) {
		this.storedTile = storedTile;
		entities = new ArrayList<Entity>();
	}
	public boolean beforeMoveInto(MovingEntity e, Direction d) {
		return storedTile.onMoveInto(e, d, this) && entities.stream().allMatch(a -> a.interactBefore(e, d, this));
	}

	public boolean afterMoveInto(MovingEntity e, Direction d) {
		return entities.stream().allMatch(a -> a.interactAfter(e, d, this));
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
