package nz.ac.vuw.ecs.swen225.gp22.app;

/**
 * Represents an external input system to send inputs to an instance of GameHandler without having to use key events.
 *
 * @author niamh
 */
public class InputGenerator {
    private final GameHandler game;

    /**
     * Call move up on your GameHandler instance.
     *
     * @author niamh
     */
    public void up() { game.getInput().mvUp(); }

    /**
     * Call move down on your GameHandler instance.
     *
     * @author niamh
     */
    public void down() { game.getInput().mvDown(); }

    /**
     * Call move left on your GameHandler instance.
     *
     * @author niamh
     */
    public void left() { game.getInput().mvLeft(); }

    /**
     * Call move right on your GameHandler instance.
     *
     * @author niamh
     */
    public void right() { game.getInput().mvRight(); }

    /**
     * Constructs an instance of input generator and ties it to a Gamehandler instance.
     * @param game Instance of GameHandler to bind with.
     *
     * @author niamh
     */
    public InputGenerator(GameHandler game) { this.game = game; }
}
