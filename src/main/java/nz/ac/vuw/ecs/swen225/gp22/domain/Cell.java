package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Each Cell keeps track of the tile it is made up of and the entities on it.
 */
public class Cell {
	private FreeTile storedTile;
	private ArrayList<Entity> entities;

	/**
	 * Default Constructor for the cell leaves the tile as a blank FreeTile
	 */
	public Cell() {
		this.storedTile = new FreeTile();
		entities = new ArrayList<Entity>();
	}
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
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(ArrayList<Entity> entList) {
		entities = entList;
	}

	public void removeEntity(Entity e) {
		entities = (ArrayList<Entity>) entities.stream().filter(entity -> entity != e).toList();
	}
}
