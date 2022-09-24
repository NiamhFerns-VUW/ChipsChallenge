package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	private FreeTile storedTile;
	private List<Entity> entities;
	public final Coord coordinate;
	
	public Cell(FreeTile storedTile, Coord coordinate) {
		this.storedTile = storedTile;
		this.coordinate = coordinate;
		entities = new ArrayList<Entity>();
	}
	public Cell(){
		this.coordinate = null;
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
	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
}
