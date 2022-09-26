package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;

/**
 * Each Cell keeps track of the tile it is made up of and the entities on it.
 */
public class Cell {
	private FreeTile storedTile;
	private ArrayList<Entity> entities;
	
	public Cell(FreeTile storedTile, Coord coordinate) {
		this.storedTile = storedTile;
		this.coordinate = coordinate;
		entities = new ArrayList<Entity>();
	}
	public boolean onMoveInto(Entity e, Direction d) {
		return storedTile.onMoveInto(e, d);
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
}
