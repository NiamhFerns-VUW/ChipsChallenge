package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;

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
    public void call(Integer keyCode) {
        game.getInput().call(keyCode);
    }

    /**
     * Constructs an instance of input generator and ties it to a Gamehandler instance.
     *
     * @author niamh
     */
    public InputGenerator() { this.game = GameHandler.get(); }
}
