package nz.ac.vuw.ecs.swen225.gp22.domain;

/**
 * The four directions Up, Down, Left, Right, as well as None - used for movement.
 * 
 */
public enum Direction {
	Up(0,-1), Down(0,1), Left(-1,0), Right(1,0), None(0,0);
	final int x, y;
	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
