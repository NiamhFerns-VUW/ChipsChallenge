package gp22.domain;

import java.util.ArrayList;

public class Cell {
	private FreeTile storedTile;
	private ArrayList<Entity> entities;
	public final Coord coordinate;
	
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
