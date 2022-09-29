package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.Cell;
import nz.ac.vuw.ecs.swen225.gp22.domain.Domain;
import nz.ac.vuw.ecs.swen225.gp22.domain.FreeTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Wall;
import nz.ac.vuw.ecs.swen225.gp22.renderer.images.Img;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel {
    private Domain domain;
    public Render(Domain d) {
        this.domain = d;
    }

    public void paintComponent(Graphics g) {
        Cell[][] ar = {{
            new Cell(new FreeTile()),
                new Cell(new FreeTile()),
                new Cell(new FreeTile()),
                new Cell(new FreeTile()),
                new Cell(new FreeTile()),
                new Cell(new FreeTile()),
                new Cell(new FreeTile()),
                new Cell(new FreeTile()),
                new Cell(new FreeTile()),
                new Cell(new FreeTile()),
        },
            {
                new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
                    new Cell(new FreeTile()),
            }
        };

        for(int i=0; i < ar.length; i++){
            for(int j=0; j < ar[i].length; j++){
                Cell c = ar[i][j];
                if(c.getStoredTile().getClass().equals(FreeTile.class)){
                    g.drawImage(Img.Freetile.image, i*400, j*400, 400, 400, null);
                }
                else if(c.getStoredTile().getClass().equals(Wall.class)){
                    g.drawImage(Img.Walltile.image, i*40, j*40, 40, 40, null);
                }
            }
        }

        g.setColor(Color.RED);
        g.fillRect(0,0, getWidth(), getHeight());
    }
}
