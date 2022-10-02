package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;

class Viewport extends JFrame implements Observer {
    private GameState state;

    private List<JPanel> panels;


    public Viewport(GameHandler owner, InputHandler input) {
        assert SwingUtilities.isEventDispatchThread();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Chips Challenge");
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        addKeyListener(input);

        setSize(new Dimension(640, 480));
        panels = List.of();
    }

    @Override
    public void update() {
        panels.forEach(JPanel::repaint);
    }
    
    protected void setState(GameState state) {
        panels.forEach(this::remove);
        this.state = state;
        panels = state.panels();
        panels.forEach(this::add);
    }

    GameState getGameState() {
        return state;
    }
}