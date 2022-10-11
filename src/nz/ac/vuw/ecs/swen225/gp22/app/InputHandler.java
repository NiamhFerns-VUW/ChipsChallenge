package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.Direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;
import nz.ac.vuw.ecs.swen225.gp22.recorder.Recorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Responsible for handling user input for a game of chips challenge.
 * Captures events then translates them for an instance of domain and renderer based on a pair of maps of keybindings
 *
 * @author niamh
 */
class InputHandler implements KeyListener {
    private final Domain domain;
    private final Recorder recorder;
    private HashMap<Integer, Runnable> currentPressedMap;
    private HashMap<Integer, Runnable> currentReleasedMap;
    private final HashMap<Integer, Runnable> pressed;
    private final HashMap<Integer, Runnable> released;
    private final HashMap<Integer, Runnable> alternatePressed;
    private final HashMap<Integer, Runnable> alternateReleased;

    /**
     * Sets up an InputHandler and ties it to an instance of domain and recorder.
     * @param domain the domain that will be receiving translated key events.
     * @param recorder the recorder that will be receiving translated key events.
     *
     * @author niamh
     */
    InputHandler(Domain domain, Recorder recorder) {
        this.domain = domain;
        this.recorder = recorder;
        pressed = new HashMap<>();
        released = new HashMap<>();
        alternatePressed= new HashMap<>();
        alternateReleased = new HashMap<>();

        currentPressedMap = pressed;
        currentReleasedMap = released;
    }

    /**
     * Command callable by other classes to add a binding and its pressed and released functionality to the relevant default map.
     * @param key an integer code representing a key both in the maps and on the keyboard.
     * @param pressed a Runnable lambda or class method passed as the functionality on pressing a key.
     * @param released a Runnable lambda or class method passed as the functionality on releasing a key.
     *
     * @author niamh
     */
    protected void addBinding(Integer key, Runnable pressed, Runnable released) {
        this.pressed.put(key, pressed);
        this.released.put(key, released);
    }

    /**
     * Command callable by other classes to add a binding and its pressed and released functionality to the relevant alternate map.
     * @param key an integer code representing a key both in the maps and on the keyboard.
     * @param pressed a Runnable lambda or class method passed as the functionality on pressing a key.
     * @param released a Runnable lambda or class method passed as the functionality on releasing a key.
     *
     * @author niamh
     */
    protected void addAlternateBinding(Integer key, Runnable pressed, Runnable released) {
        this.alternatePressed.put(key, pressed);
        this.alternateReleased.put(key, released);
    }

    /**
     * Move a player up/north and then record the movement to a recorder.
     *
     * @author niamh
     */
    protected void mvUp() {
        domain.movePlayer(Direction.Up);
        recorder.up();
    }

    /**
     * Move a player south/south and then record the movement to a recorder.
     *
     * @author niamh
     */
    protected void mvDown() {
        domain.movePlayer(Direction.Down);
        recorder.down();
    }

    /**
     * Move a player left/east and then record the movement to a recorder.
     *
     * @author niamh
     */
    protected void mvLeft() {
        domain.movePlayer(Direction.Left);
        recorder.left();
    }

    /**
     * Move a player right/east and then record the movement to a recorder.
     *
     * @author niamh
     */
    protected void mvRight() {
        domain.movePlayer(Direction.Right);
        recorder.right();
    }

    /**
     * Sets the InputHandler to expect an alternate control with the control key modifier.
     *
     * @author niamh
     */
    protected void setAlternateControls() {
        currentPressedMap = alternatePressed;
        currentReleasedMap = alternateReleased;
    }

    /**
     * Sets the InputHandler to expect standard controls without the control key modifier.
     *
     * @author niamh
     */
    protected void unsetAlternateControls() {
        currentPressedMap = pressed;
        currentReleasedMap = released;
    }

    /**
     * Sets the InputHandler to expect standard controls without the control key modifier.
     *
     * @author niamh
     */
    protected void saveGame() {
    }

    /**
     * Sets the InputHandler to expect standard controls without the control key modifier.
     *
     * @author niamh
     */
    protected void exitGame() {
    }

    /**
     * Sets the InputHandler to expect standard controls without the control key modifier.
     *
     * @author niamh
     */
    protected void skipToLevel1() {
    }

    /**
     * Sets the InputHandler to expect standard controls without the control key modifier.
     *
     * @author niamh
     */
    protected void skipToLevel2() {
    }

    /**
     * Unused keyTyped method inherited from KeyListener interface.
     *
     * @author niamh
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    /**
     * Retrieves a keyEvent on detecting a key push and then runs the relevant action in pressed.
     *
     * @author niamh
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        pressed.getOrDefault(keyEvent.getKeyCode(), () -> {}).run();
    }

    /**
     * Retrieves a keyEvent on detecting a key push and then runs the relevant action in released.
     *
     * @author niamh
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        released.getOrDefault(keyEvent.getKeyCode(), () -> {}).run();
    }

}
