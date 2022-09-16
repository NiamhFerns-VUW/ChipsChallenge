package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Level {
	private int remainingTreasures;
	private Cell[][] cells;
	private Entity[] inventory;
	
	public Level(int remainingTreasures, Cell[][] cells) {
		this.remainingTreasures = remainingTreasures;
		this.cells = cells;
		this.inventory = new Entity[8];
	}
	public Level(int remainingTreasures, Cell[][] cells, Entity[] inventory) {
		this.remainingTreasures = remainingTreasures;
		this.cells = cells;
		this.inventory = inventory;
	}
	
	public int getRemainingTreasures() {
		return remainingTreasures;
	}
	public void setRemainingTreasures(int newTreasures) {
		remainingTreasures = newTreasures;
	}
	public Cell[][] getCells() {
		return cells;
	}
	public Entity[] getInventory() {
		return inventory;
	}
}
