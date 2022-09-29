package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class GameHandler implements Observer {
    private GameState state;

    private Viewport currentViewport;

    Domain domain;
    Viewport viewport;

    protected void setBindings(InputHandler input) {
        input.addBinding(KeyEvent.VK_UP,    input::mvUp,    () -> {});
        input.addBinding(KeyEvent.VK_DOWN,  input::mvDown,  () -> {});
        input.addBinding(KeyEvent.VK_LEFT,  input::mvLeft,  () -> {});
        input.addBinding(KeyEvent.VK_RIGHT, input::mvRight, () -> {});
    }

    public GameHandler() {
        // Create fields.
        domain = new Domain();

        var input = new InputHandler(domain);
        setBindings(input);

        viewport = new Viewport(input);

        // Start the game and game clock.
        start();
        GameClock.get().start();
    }

    public void start() {
        GameClock.get().register(this);
        GameClock.get().register(viewport);
        viewport.onStart.run();
    }

    @Override
    public void update() {
        // viewport.getGameState().action(domain).run();
    }
}