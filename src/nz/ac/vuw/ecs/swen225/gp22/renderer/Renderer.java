package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {
    public Renderer() {

    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect(0,0, getWidth(), getHeight());
    }
}
