package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;

record StartMenu(String levelName) implements GameState {
    @Override
    public JPanel getPanel() {
        JPanel p = new JPanel();
        return new BaseGameplayFrame() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.clearRect(0,0,640,480);

                g.setColor(Color.blue);
                g.fillRect(0,0,100,100);
            }
        };
    }
}
record LevelOne(String levelName) implements GameState {
    @Override
    public JPanel getPanel() {
        return new BaseGameplayFrame();
    }
}
record LevelTwo(String levelName) implements GameState {
    @Override
    public JPanel getPanel() {
        throw(new Error("Level two not implemented yet."));
    }
}
interface GameState {
    String levelName();
    JPanel getPanel();
}

class BaseGameplayFrame extends JPanel {
    BaseGameplayFrame() {
        setPreferredSize(new Dimension(640, 480));
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.clearRect(0,0,640,480);

        // My reference to your code will go here..
        // Renderer.renderMethod(g).
        // You have free rain to do whatever you want with this graphics component.
    }
}
