package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.Recorder;

import java.awt.event.KeyEvent;

public class GameHandler implements Observer {
    public static GameHandler instance;
    private final Domain domain;
    private final Recorder recorder;
    private final Viewport viewport;
    private final InputHandler input;

    public InputHandler getInputs() { return input; }
    private void setBindings(InputHandler input) {
        input.addBinding(KeyEvent.VK_UP,    input::mvUp,    () -> {});
        input.addBinding(KeyEvent.VK_DOWN,  input::mvDown,  () -> {});
        input.addBinding(KeyEvent.VK_LEFT,  input::mvLeft,  () -> {});
        input.addBinding(KeyEvent.VK_RIGHT, input::mvRight, () -> {});
    }

    public GameHandler() {
        // Create fields.
        domain = new Domain();
        recorder = new Recorder();

        input = new InputHandler(domain, recorder);
        setBindings(input);

        viewport = new Viewport(this, input);

        // Start the game and game clock.
        start();
        GameClock.get().start();
        if (instance == null) instance = this;
        else throw new Error("She's fucked mate. You have permission to scream and Neem.");
    }

    public static GameHandler get() {
        return instance;
    }

    public void skipTo(String str) {
        switch(str) {
            case "Level1":
                System.out.println("You are now at level one.");


            case "Level2":
                System.out.println("You are now at level two.");
        }
    }

    private void start() {
        GameClock.get().register(this);
        GameClock.get().register(viewport);
        viewport.setState(new StartScreen());
    }

    protected void onLevelChange() {
        GameClock.get().unregister(viewport);
        viewport.setState(viewport.getGameState().nextLevel());

        if (viewport.getGameState() instanceof Level)
            setComponents((Level) viewport.getGameState());

        GameClock.get().register(viewport);

        viewport.validate();
    }

    public void setGameState(GameState state) {
        GameClock.get().unregister(viewport);
        viewport.setState(state);

        if (state instanceof Level)
            setComponents((Level) state);

        GameClock.get().register(viewport);

        viewport.validate();
    }

    private void setComponents(Level level) {
            // Start the next level in domain.
            domain.startLevel(level.levelName());

            // Reset the recorder.
            recorder.reset();
            recorder.setLevel(level.levelName());
            recorder.start();

            // Register domain to the renderer instance.
            level.gameplayPanel().setUp(domain);
    }

    @Override
    public void update() {
        // if (domain.ok()) viewport.getGameState().action(domain).run();
    }
}