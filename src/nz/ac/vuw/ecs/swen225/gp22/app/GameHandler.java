package nz.ac.vuw.ecs.swen225.gp22.app;

import java.io.File;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Persistency;

public class GameHandler extends JFrame implements Observer {
    private GameState state;
    private final InputHandler input;
    private JPanel gamePanel;
    private final JPanel timerPanel;

    JMenuBar menuBar = new JMenuBar();

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

        // Layout


        add(timerPanel, BorderLayout.NORTH);
        addKeyListener(input);

        // Start the game and game clock.
        start();
        GameClock.get().start();

        // add menubar
        JMenu fileMenu = new JMenu("file");
        JMenuItem loadMenuItem = new JMenuItem("load");
        JMenuItem saveMenuItem = new JMenuItem("save");
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);

        loadMenuItem.addActionListener(al->{
            JFileChooser fileChooser = new JFileChooser("./saves/");
            fileChooser.showOpenDialog(this);
            File selectedFile = fileChooser.getSelectedFile();
            String s = Persistency.loadGame(selectedFile.getPath());
            System.out.println("returned: " + s);
        });
        saveMenuItem.addActionListener(al->{
            Persistency.saveGameSave("pass me domain/gameSave object","lksdjfklsdf");
        });

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);


        // Set the level.
        state = new StartMenu("Start Menu");
        gamePanel = state.getPanel();
        add(gamePanel, BorderLayout.CENTER);
        pack();
    }

    public void start() {
        GameClock.get().register(this);
    }

    @Override
    public void update() {
        assert SwingUtilities.isEventDispatchThread();
        validate();
        timerPanel.repaint();
        gamePanel.repaint();
    }

    protected void setState(GameState state) {
        remove(gamePanel);
        this.state = state;
        gamePanel = state.getPanel();
        add(gamePanel);
    }
}