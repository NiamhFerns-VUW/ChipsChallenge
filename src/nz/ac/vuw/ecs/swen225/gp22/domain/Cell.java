package nz.ac.vuw.ecs.swen225.gp22.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Each Cell keeps track of the tile it is made up of and the entities on it.
 */
public class Cell {
	private FreeTile storedTile;
	private List<Entity> entities;

	private Coord coord;
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

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public Cell(FreeTile freeTile, List<Entity> entities) {
		this.storedTile = freeTile;
		this.entities = entities;
	}
	public Cell(FreeTile freeTile, List<Entity> entities, Coord coord) {
		this.storedTile = freeTile;
		this.entities = entities;
		this.coord = coord;
	}
    public boolean beforeMoveInto(MovingEntity e, Direction d) {
		return storedTile.onMoveInto(e, d, this) &&
				entities.stream().allMatch(a -> a.interactBefore(e, d, this));
	}

	public boolean afterMoveInto(MovingEntity e, Direction d) {
		return storedTile.afterMoveInto(e, d, this) &&
				entities.stream().allMatch(a -> a.interactAfter(e, d, this));
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
	@JsonIgnore
	public Optional<Image> getImage() {
		Optional<Entity> entity = entities.stream().reduce((a, b)->{
			return a.drawHierarchy() < b.drawHierarchy() ? a : b;
		});

		if (entity.isEmpty()) return Optional.empty();
		return Optional.of(entity.get().getImage());
	}

	@JsonIgnore
	public Image getTileImage() {
		return storedTile.getImage();
	}

	public void removeEntity(Entity e) {
		if (!entities.contains(e))
			throw new IllegalArgumentException("Cannot remove an entity from a tile it is not in!");

		entities = new ArrayList<Entity>(entities.stream().filter(entity -> entity != e).toList());
	}
	@Override
	public int hashCode() {
		return storedTile.hashCode()+entities.hashCode()+coord.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Cell && obj.hashCode() == this.hashCode();
	}
}
