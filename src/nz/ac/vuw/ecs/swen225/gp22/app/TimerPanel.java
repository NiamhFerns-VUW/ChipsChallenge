package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;

class TimerPanel extends JPanel {
    TimerPanel() {
        setPreferredSize(new Dimension(640, 75));
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // Fill the background colour.
        g.fillRect(0,0, getWidth(), getHeight());
    }
}
