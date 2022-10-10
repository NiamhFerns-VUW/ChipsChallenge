package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * Blocks movement until it is unlocked by Chip with a Key of the same colour.
 */
public class LockedDoor extends FreeTile {
	private String lockColour;

	public String getLockColour() {
		return lockColour;
	}

	public void setLockColour(String lockColour) {
		this.lockColour = lockColour;
	}

	public LockedDoor() {
		this.lockColour = "";
	}
	public LockedDoor(String lockColour) {
		this.lockColour = lockColour;
	}
	@Override
	public boolean onMoveInto(MovingEntity e, Direction d, Cell myCell) {
		if (e instanceof Chip c &&
				c.level.getInventory().stream()
						.filter(t->t instanceof Key)
						.anyMatch(t-> ((Key) t).keyColour.equals(lockColour))) {
			myCell.setStoredTile(new FreeTile());
			return true;
		}

		return false;
	}
}
