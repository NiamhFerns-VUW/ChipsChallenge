package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameHandler extends JFrame implements Observer {
    private GameState state;
    private final InputHandler input;
    private JPanel gamePanel;
    private final JPanel timerPanel;

    Domain domain;

    protected void setBindings() {
        input.addBinding(KeyEvent.VK_UP,    input::mvUp,    () -> {});
        input.addBinding(KeyEvent.VK_DOWN,  input::mvDown,  () -> {});
        input.addBinding(KeyEvent.VK_LEFT,  input::mvLeft,  () -> {});
        input.addBinding(KeyEvent.VK_RIGHT, input::mvRight, () -> {});
    }

    public GameHandler() {
        assert SwingUtilities.isEventDispatchThread();

        // Create fields.
        timerPanel = new TimerPanel();
        domain     = new Domain();
        input      = new InputHandler(domain);

        setBindings();

        // Setting the window characteristics.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Chips Challenge");
        setLocationRelativeTo(null);
        setVisible(true);
        // add(timerPanel);
        addKeyListener(input);

        // Start the game and game clock.
        start();
        GameClock.get().start();

        // Set the level.
        state = new StartMenu("Start Menu");
        gamePanel = state.getPanel();
        add(gamePanel);
        pack();
    }

    public void start() {
        GameClock.get().register(this);
    }

    @Override
    public void update() {
        assert SwingUtilities.isEventDispatchThread();
        validate();
        gamePanel.repaint();
    }

    protected void setState(GameState state) {
        remove(gamePanel);
        this.state = state;
        gamePanel = state.getPanel();
        add(gamePanel);
    }
}