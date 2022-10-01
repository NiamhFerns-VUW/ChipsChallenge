package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.Recorder;

import java.awt.event.KeyEvent;

public class GameHandler {
    public static GameHandler instance;
    private final ObserverAdapter domain;
    private final Recorder recorder;
    private final Viewport viewport;
    private final InputHandler input;

    public GameHandler() {
        // Create fields.
        domain = new ObserverAdapter(new Domain());
        recorder = new Recorder();

        input = new InputHandler(domain.get(), recorder);
        setBindings(input);

        viewport = new Viewport(input);

        // Start the game and game clock.
        start();
        GameClock.get().start();
        if (instance == null) instance = this;
        else throw new IllegalStateException("GameHandler has already been assigned elsewhere.");
    }

    protected static GameHandler get() {
        return instance;
    }

    protected InputHandler getInput() {
        return input;
    }


    class ObserverAdapter implements Observer {
        Domain d;
        protected Domain get() { return d; }
        protected ObserverAdapter(Domain d) { this.d = d; }
        @Override public void update() { domain.get().update(); }
    }

    private void setBindings(InputHandler input) {
        input.addBinding(KeyEvent.VK_UP,    input::mvUp,    () -> {});
        input.addBinding(KeyEvent.VK_DOWN,  input::mvDown,  () -> {});
        input.addBinding(KeyEvent.VK_LEFT,  input::mvLeft,  () -> {});
        input.addBinding(KeyEvent.VK_RIGHT, input::mvRight, () -> {});
    }

    private void start() {
        GameClock.get().register(viewport);
        viewport.setState(new StartScreen());
    }


    public void skipTo(String str) {
        switch(str) {
            case "Level1":
                System.out.println("You are now at level one.");


            case "Level2":
                System.out.println("You are now at level two.");
        }
    }

    public void setGameState(GameState state) {
        GameClock.get().unregister(viewport);
        GameClock.get().unregister(domain);
        viewport.setState(state);

        if (state instanceof Level)
            setComponents((Level) state);

        GameClock.get().register(viewport);

        viewport.validate();
    }


    protected void onLevelChange() {
        GameClock.get().unregister(domain);
        GameClock.get().unregister(viewport);
        viewport.setState(viewport.getGameState().nextLevel());

        if (viewport.getGameState() instanceof Level) {
            recorder.saveRecording();
            setComponents((Level) viewport.getGameState());
        }

        GameClock.get().register(viewport);

        viewport.validate();
    }

    private void setComponents(Level level) {
            // Start the next level in domain.
            domain.get().startLevel(level.levelName());
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