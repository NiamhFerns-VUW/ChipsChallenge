package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.GameSave;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Persistency;
import nz.ac.vuw.ecs.swen225.gp22.renderer.images.*;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Render extends JPanel {
    private Domain domain;
   /**
    * Default constructor for an instance of Render
    * */
    public Render() {

    }

    /**
     * Passes
     * */
    public void setUp(Domain d){
        this.domain = d;
    }

    public boolean domainSet(){
        return (domain != null);
    }

    public void paintComponent(Graphics g) {
        boolean cells2 = this.domain.getLevel().isPresent();
        Cell[][] cells;
        if(cells2){
            cells = this.domain.getLevel().get().cells;

        int tileSize = getHeight()/cells.length;

        for(int i=0; i < cells.length; i++){
            for(int j=0; j < cells[i].length; j++){
                Cell c = cells[i][j];

                if(c.getStoredTile().getClass().equals(FreeTile.class)){
                    g.drawImage(Img.Freetile.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                }
                else if(c.getStoredTile().getClass().equals(Wall.class)){
                    g.drawImage(Img.Walltile.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                }
                else if(c.getStoredTile().getClass().equals(Exit.class)){
                    g.drawImage(Img.Exittile.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                }
                else if(c.getStoredTile().getClass().equals(ExitLock.class)){
                    g.drawImage(Img.Exitlock.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                }
                else if(c.getStoredTile().getClass().equals(Info.class)){
                    g.drawImage(Img.Infotile.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                }
                else if(c.getStoredTile().getClass().equals(LockedDoor.class)){
                    LockedDoor ld = ((LockedDoor) c.getStoredTile());
                    switch (ld.lockColour) {
                        case "Blue" ->
                                g.drawImage(Img.BlueLockeddoor.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                        case "Green" ->
                                g.drawImage(Img.GreenLockeddoor.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                        case "Yellow" ->
                                g.drawImage(Img.YellowLockeddoor.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                        case "Red" ->
                                g.drawImage(Img.RedLockeddoor.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                    }
                }
                if(c.getEntities() != null){
                    ArrayList entities = (ArrayList) c.getEntities();
                    for(int k=0; k<c.getEntities().size(); k++){
                        if(entities.get(k).getClass().equals(Chip.class)){
                            g.drawImage(Img.Chip.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                        }
                        if(entities.get(k).getClass().equals(Key.class)) {
                            Key key = (Key) entities.get(k);
                            switch (key.keyColour) {
                                case "Blue" -> g.drawImage(Img.Bluekey.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                                case "Green" -> g.drawImage(Img.Greenkey.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                                case "Yellow" -> g.drawImage(Img.Yellowkey.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                                case "Red" -> g.drawImage(Img.Redkey.image, j * tileSize, i * tileSize, tileSize, tileSize, null);
                            }
                        }
                        if(entities.get(k).getClass().equals(Treasure.class)){
                            g.drawImage(Img.Treasure.image, j * tileSize, i * tileSize, tileSize, tileSize, null);

                        }
                    }
                }

            }
        }
        }
    }
}
