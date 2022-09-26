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
    private final JPanel gamePanel;
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
        gamePanel  = new GamePanel();
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
        add(timerPanel);
        add(gamePanel);
        addKeyListener(input);
        pack();

        // Start the game and game clock.
        start();
        GameClock.get().start();
    }

    public void start() {
        GameClock.get().register(this);
    }

    @Override
    public void update() {
        gamePanel.repaint();
        timerPanel.repaint();
    }
}

class TimerPanel extends JPanel {
    TimerPanel() {
        setPreferredSize(new Dimension(640, 100));
        setBackground(Color.GRAY);
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.blue);
        g.fillRect(20, 20, 10, 10);
    }
}

class GamePanel extends JPanel {
    GamePanel() {
        setPreferredSize(new Dimension(640, 480));
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, 40, 40);
    }
}