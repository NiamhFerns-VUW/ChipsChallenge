package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.Recorder;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Render;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * The primary game management class for a game of chips challenge that is responsible for the lifetime and handling
 * of all major components.
 *
 * @author niamh
 */
public class GameHandler implements Observer {
    public static GameHandler instance;
    private final ObserverAdapter domain;
    private final Recorder recorder;
    private Viewport viewport;
    private final InputHandler input;

    /**
     * Construct a new GameHandler and make sure one and only one instance exists.
     *
     * @author niamh
     */
    private GameHandler() {
        // Create fields.
        domain = new ObserverAdapter(new Domain());
        recorder = new Recorder();

        input = new InputHandler(domain.get(), recorder);
        setBindings(input);

        try {
            SwingUtilities.invokeAndWait(() -> { viewport = new Viewport(input); });
        }
        catch (InvocationTargetException | InterruptedException e) { e.printStackTrace(); }

        // Start the game and game clock.
        start();
        if (instance != null) throw new IllegalStateException("GameHandler has already been instantiated. Use GameHandler.get() to retrieve the instance.");
    }

    /**
     * Retrieve the GameHandler instance from other classes.
     * @return the current instance of GameHandler.
     *
     * @author niamh
     */
    public static GameHandler get() {
        if (instance == null) instance = new GameHandler();
        return instance;
    }

    /**
     * Retrieve the input handler currently assigned to the GameHandler instance.
     * @return an instance of input currently tied to GameHandler
     *
     * @author niamh
     */
    protected InputHandler getInput() {
        return input;
    }
    protected Domain domain() {
        return domain.get();
    }
    protected Recorder recorder() { return recorder; }
    protected int chipsRemaining() { return  0; }


    @Override
    public void update() {
        if (GameClock.get().currentLevelTime() <= 0)
            onFail();
    }

    /**
     * Represents a simple adapter to translate domain into something registrable by Subject.
     *
     * @author niamh
     */
    class ObserverAdapter implements Observer {
        Domain d;
        protected Domain get() { return d; }
        protected ObserverAdapter(Domain d) { this.d = d; }
        @Override public void update() { domain.get().update(); }
    }

    /**
     * Set all the keybindings that will be used in this game of Chip's Challenge.
     * @param input the InputHandler currently tied to this GameHandler.
     *
     * @author niamh
     */
    private void setBindings(InputHandler input) {
        input.addBinding(KeyEvent.VK_UP,      input::mvUp,    () -> {});
        input.addBinding(KeyEvent.VK_DOWN,    input::mvDown,  () -> {});
        input.addBinding(KeyEvent.VK_LEFT,    input::mvLeft,  () -> {});
        input.addBinding(KeyEvent.VK_RIGHT,   input::mvRight, () -> {});
        input.addBinding(KeyEvent.VK_SPACE,   () -> {}, () -> {});
        input.addBinding(KeyEvent.VK_ESCAPE,  () -> {}, () -> {});

        input.addBinding(KeyEvent.VK_CONTROL, input::setAlternateControls, () -> {});
        input.addAlternateBinding(KeyEvent.VK_CONTROL, () -> {}, input::unsetAlternateControls);

        input.addAlternateBinding(KeyEvent.VK_R, input::resumeGame, () -> {});
        input.addAlternateBinding(KeyEvent.VK_X, input::exitGame, () -> {});
        input.addAlternateBinding(KeyEvent.VK_S, input::saveGame, () -> {});
        input.addAlternateBinding(KeyEvent.VK_1, input::skipToLevel1, () -> {});
        input.addAlternateBinding(KeyEvent.VK_2, input::skipToLevel2, () -> {});
    }

    /**
     * Start the game.
     *
     * @author niamh
     */
    public void start() {
        GameClock.get().register(viewport);
        viewport.setState(new StartScreen());
        viewport.repack();
        GameClock.get().start();
    }

    /**
     * Resets game cleanly and leaves it in a "stopped" state.
     *
     * @author niamh
     */
    public void reset() {
        GameClock.get().stop();
        GameClock.get().reset();
        GameClock.get().unregister(this);
        GameClock.get().unregister(viewport);
    }

    /**
     * Skips to a level from anywhere in the game. Can be used by other classes to set level for testing purposes.
     * @param str a string containing the level name.
     *
     * @author niamh
     */
    public void skipTo(String str) {
        switch(str.toLowerCase()) {
            case "level1":
                System.out.println("You are now at level one.");
                setGameState(new Level("Level One","level1", domain.get(), new Render()));
                break;


            case "level2":
                System.out.println("You are now at level two.");
                setGameState(new Level("Level Two", "level2", domain.get(), new Render()));
                break;
        }
    }

    /**
     * Internal functionality for changing the game to any arbitrary GameState.
     * @param state the state that the game will be switched to.
     *
     * @author niamh
     */
    protected void setGameState(GameState state) {
        GameClock.get().unregister(viewport);
        GameClock.get().unregister(domain);
        viewport.setState(state);

        if (state instanceof Level)
            setComponents((Level) state);

        GameClock.get().register(viewport);
        GameClock.get().register(this);
        GameClock.get().setLevelTime(90000);

        viewport.validate();
        viewport.repack();
    }

    /**
     * Changes the level to the next level in order based on the current state.
     *
     * @author niamh
     */
    protected void onLevelChange() {
        GameClock.get().unregister(this);
        GameClock.get().unregister(domain);
        GameClock.get().unregister(viewport);
        if (viewport.getGameState() instanceof Level) recorder.saveRecording();

        viewport.setState(viewport.getGameState().nextLevel());

        if (viewport.getGameState() instanceof Level) {
            setComponents((Level) viewport.getGameState());
        }


        GameClock.get().setLevelTime(90000);
        GameClock.get().register(this);
        GameClock.get().register(viewport);

        viewport.validate();
        viewport.repack();
    }

    protected  void onFail() {
        GameClock.get().unregister(this);
        setGameState(new StartScreen());
    }

    /**
     * Sets domain to the correct level and resets the recorder for use on a new level.
     * @param level the level that the game will be changing to.
     *
     * @author niamh
     */
    private void setComponents(Level level) {
            // Start the next level in domain.
            domain.get().startLevel(level.levelName(), this::onLevelChange, this::onFail);
            assert domain.get().ok();
            GameClock.get().register(domain);

            // Reset the recorder.
            recorder.reset();
            recorder.setLevel(level.levelName());
            recorder.start();

            // Register domain to the renderer instance.
            level.gameplayPanel().setUp(domain.get());
    }
}