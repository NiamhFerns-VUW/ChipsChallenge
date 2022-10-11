package nz.ac.vuw.ecs.swen225.gp22.renderer;

import gameImages.Img;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Render extends JPanel {

    private Domain domain;
   /**
    * Default constructor for an instance of Render
    * */
    public Render() {

    }

    /**
     * Accepts the Domain instance
     *
     * @param d Domain instance used for the game
     * */
    public void setUp(Domain d){
        this.domain = d;
    }

    /**
     * Checks that the domain has been set.
     *
     * @return boolean
     * */
    public boolean domainSet(){
        return (domain != null);
    }


    /**
     * A method to find the cell that Chip is currently standing on. This method helps with being able
     * to figure out the cells that need to be drawn and the cells that do not.
     *
     * @return Array of two integers, the first being the x co-ordinate and the second is the y co-ordinate
     * */
    private ArrayList<Integer> findChip(Cell[][] cells){
        ArrayList<Integer> chipsCells = new ArrayList<>();
        for(int i=0; i < cells.length; i++){
            for(int j=0; j< cells[i].length; j++){
                for (Entity e : cells[i][j].getEntities()) {
                    if (e.getClass().equals(Chip.class)) {
                        chipsCells.add(i);
                        chipsCells.add(j);
                        return chipsCells;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This is the method that draws all the tiles, entities and characters
     *
     * @param g Graphics pannel to draw to
     * */
    public void paintComponent(Graphics g) {
        boolean cellsPresent = this.domain.getLevel().isPresent();
        Cell[][] cells;
        if(cellsPresent){
            cells = this.domain.getLevel().get().cells;

            int width = 9;
            int tileSize = getWidth()/ width;

            ArrayList<Integer> chipsCells = findChip(cells);

            int i=0;
            int j=0;
            for(int col=-4; col < 5; col++){
                for(int row=-4; row < 5; row++){
                    assert chipsCells != null;
                    if(col + chipsCells.get(0) < 0 || col + chipsCells.get(0) >= cells.length
                            || row + chipsCells.get(1) < 0 || row + chipsCells.get(1) >= cells.length ){
                        g.drawImage(Img.Freetile.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                    }
                    else {
                        Cell c = cells[col + chipsCells.get(0)][row + chipsCells.get(1)];

                        g.drawImage(c.getTileImage(), j * tileSize, i * tileSize, tileSize, tileSize, null);

//                        if(c.getEntities() != null) {
//                            ArrayList<Entity> entities = (ArrayList<Entity>) c.getEntities();
//                            for (int k = 0; k < entities.size(); k++) {
//                                if (entities.get(k).getClass().equals(Chip.class)) {
//                                    Info cellI = (Info) c.getStoredTile();
//                                    String info = cellI.infoText;
//                                    g.setColor(Color.yellow);
//                                    g.setFont(new Font("SansSerif", Font.PLAIN, 40));
//                                    g.drawString(info, 150, 150);
//                                }
//                            }
//                        }
                        if(c.getEntities() != null){
                            ArrayList<Entity> entities = (ArrayList<Entity>) c.getEntities();
                            for(int k=0; k<entities.size(); k++){
                                if(entities.get(k).getClass().equals(Chip.class)){
                                    g.drawImage(Img.Chip.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                                }
                                if(entities.get(k).getClass().equals(Key.class)) {
                                    Key key = (Key) entities.get(k);
                                    switch (key.keyColour) {
                                        case "Blue" -> g.drawImage(Img.Bluekey.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                                        case "Green" -> g.drawImage(Img.Greenkey.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                                        case "Yellow" -> g.drawImage(Img.Yellowkey.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                                        case "Red" -> g.drawImage(Img.Redkey.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                                    }
                                }
                                if(entities.get(k).getClass().equals(Treasure.class)){
                                    g.drawImage(Img.Treasure.image, j*tileSize, i*tileSize, tileSize, tileSize, null);
                                }
                            }
                        }
                        if(entities.get(k).getClass().equals(Treasure.class)){
                            g.drawImage(Img.Treasure.image, j * tileSize, i * tileSize, tileSize, tileSize, null);

                        }
                    }
                    j++;
                }
                j=0;
                i++;
            }
        }
    }
}
